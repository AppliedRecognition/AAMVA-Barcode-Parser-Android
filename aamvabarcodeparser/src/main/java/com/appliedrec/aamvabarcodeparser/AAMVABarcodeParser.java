package com.appliedrec.aamvabarcodeparser;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses information from barcodes encoded using AAMVA standard
 */
public class AAMVABarcodeParser {

    enum CardType {
        ID, DL
    }

    enum ValidChars {
        ALPHA, NUMERIC, SPECIAL
    }

    static class ElementParams {
        boolean fixed;
        int length;
        EnumSet<ValidChars> validChars;

        ElementParams(String string) throws Exception {
            Pattern pattern = Pattern.compile("(V|F)(\\d+)([ANS]+)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(string);
            if (!matcher.matches()) {
                throw new Exception();
            }
            fixed = matcher.group(1).equals("F");
            length = Integer.parseInt(matcher.group(2));
            this.validChars = EnumSet.noneOf(ValidChars.class);
            String validChars = matcher.group(3);
            if (validChars.contains("A")) {
                this.validChars.add(ValidChars.ALPHA);
            }
            if (validChars.contains("N")) {
                this.validChars.add(ValidChars.NUMERIC);
            }
            if (validChars.contains("S")) {
                this.validChars.add(ValidChars.SPECIAL);
            }
        }
    }

    static class DataElement {
        boolean mandatory;
        EnumSet<CardType> cardTypes;
        String id;
        String description;
        ElementParams params;

        DataElement(boolean mandatory, EnumSet<CardType> cardTypes, String id, String description, ElementParams params) {
            this.mandatory = mandatory;
            this.cardTypes = cardTypes;
            this.id = id;
            this.description = description;
            this.params = params;
        }

        boolean isValid(String value) {
            if (value.length() > params.length) {
                return false;
            }
            Matcher matcher;
            if (!params.validChars.contains(ValidChars.NUMERIC)) {
                Pattern numeric = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
                matcher = numeric.matcher(value);
                if (matcher.matches()) {
                    return false;
                }
            }
            if (!params.validChars.contains(ValidChars.ALPHA)) {
                Pattern alpha = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);
                matcher = alpha.matcher(value);
                if (matcher.matches()) {
                    return false;
                }
            }
            if (!params.validChars.contains(ValidChars.SPECIAL)) {
                Pattern special = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
                matcher = special.matcher(value);
                return !matcher.matches();
            }
            return true;
        }

        String formatValue(String value) {
            return value;
        }
    }

    static class EyeColourDataElement extends DataElement {

        EyeColourDataElement(boolean mandatory, EnumSet<CardType> cardTypes, String id, String description, ElementParams params) {
            super(mandatory, cardTypes, id, description, params);
        }

        @Override
        String formatValue(String value) {
            return AAMVABarcodeParser.getEyeColour(value);
        }
    }

    static class HairColourDataElement extends DataElement {

        HairColourDataElement(boolean mandatory, EnumSet<CardType> cardTypes, String id, String description, ElementParams params) {
            super(mandatory, cardTypes, id, description, params);
        }

        @Override
        String formatValue(String value) {
            return AAMVABarcodeParser.getHairColour(value);
        }
    }

    static class SexDataElement extends DataElement {

        SexDataElement(boolean mandatory, EnumSet<CardType> cardTypes, String id, String description, ElementParams params) {
            super(mandatory, cardTypes, id, description, params);
        }

        @Override
        String formatValue(String value) {
            return "1".equals(value) ? "Male" : ("2".equals(value) ? "Female" : value);
        }
    }

    @SuppressLint("SimpleDateFormat")
    static class DateDataElement extends DataElement {

        SimpleDateFormat dateParser = new SimpleDateFormat("MMddyyyy");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("d MMM yyyy");

        DateDataElement(boolean mandatory, EnumSet<CardType> cardTypes, String id, String description, ElementParams params) {
            super(mandatory, cardTypes, id, description, params);
        }

        @Override
        String formatValue(String value) {
            try {
                Date date = dateParser.parse(value);
                if (date == null) {
                    return value;
                }
                return dateFormatter.format(date);
            } catch (ParseException e) {
                return value;
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    static class AAMVASubfileParser {

        int version;
        byte separator;
        SimpleDateFormat dateParser = new SimpleDateFormat("MMddyyyy");

        AAMVASubfileParser(int version, byte elementSeparator) {
            this.version = version;
            this.separator = elementSeparator;
        }

        String[] splitField(String str) {
            if (str.length() < 3) {
                return null;
            }
            String val = StringUtils.strip(str.substring(3), "/\\\n\r ,");
            if (val.isEmpty()) {
                return null;
            }
            return new String[]{
                    str.substring(0,3),
                    val
            };
        }

        AAMVADocumentData parseFields(byte[] data) throws Exception {
            AAMVADocumentData documentData;
            switch (version) {
                case 8:
                    documentData = new AAMVAv8DocumentData();
                    break;
                case 7:
                    documentData = new AAMVAv7DocumentData();
                    break;
                case 6:
                    documentData = new AAMVAv6DocumentData();
                    break;
                case 5:
                    documentData = new AAMVAv5DocumentData();
                    break;
                case 4:
                    documentData = new AAMVAv4DocumentData();
                    break;
                case 3:
                    documentData = new AAMVAv3DocumentData();
                    break;
                case 2:
                    documentData = new AAMVAv2DocumentData();
                    break;
                case 1:
                    documentData = new AAMVAv1DocumentData();
                    break;
                default:
                    throw new Exception("Unsupported document version");
            }

            HashMap<String,String> fields = new HashMap<>();
            if (data.length < 5) {
                return documentData;
            }
            String type = new String(Arrays.copyOfRange(data, 0, 2), UTF8);
            if (!type.equals("DL") && !type.equals("ID")) {
                return documentData;
            }
            String separatorChar = new String(new byte[]{separator}, UTF8);
            String[] elementPairs = new String(Arrays.copyOfRange(data, 2, data.length), UTF8).split(separatorChar);

            for (String elementPair : elementPairs) {
                String[] keyVal = splitField(elementPair);
                if (keyVal != null) {
                    fields.put(keyVal[0], keyVal[1].trim());
                    if (keyVal[0].equals("DCG") && keyVal[1].trim().equals("CAN")) {
                        dateParser = new SimpleDateFormat("yyyyMMdd");
                    }
                }
            }
            for (DataElement element : documentData.elements) {
                if (element.cardTypes.contains(CardType.valueOf(type))) {
                    if (element instanceof DateDataElement) {
                        ((DateDataElement) element).dateParser = dateParser;
                    }
                    String val = fields.remove(element.id);
                    if (val != null) {
                        documentData.setValue(new DataField(element.description, val, element.formatValue(val)), element.id);
                        if (!element.isValid(val)) {
                            Log.d("AAMVABarcodeParser", "Invalid value for field "+element.id);
                        }
                    } else if (element.mandatory) {
                        Log.d("AAMVABarcodeParser", "Missing mandatory field "+element.id);
                    }
                }
            }
            return documentData;
        }
    }

    /**
     * Parse information from a barcode
     * @param data Raw barcode data
     * @return Parsed document data
     * @throws Exception If the parsing fails
     */
    public DocumentData parseData(byte[] data) throws Exception {
        if (data.length < 30) {
            throw new Exception("Unsupported barcode encoding");
        }
        byte complianceIndicator = data[0];
        if (complianceIndicator == 0x40) {
            // May be AAMVA
            byte elementSeparator = data[1];
            byte recordSeparator = data[2];
            byte segmentTerminator = data[3];
            byte[] fileType = Arrays.copyOfRange(data, 4, 9);
            byte[] iin = Arrays.copyOfRange(data, 9, 15);
            int aamvaVersionNumber = dataToInt(Arrays.copyOfRange(data, 15, 17));
            AAMVASubfileParser subfileParser = new AAMVASubfileParser(aamvaVersionNumber, elementSeparator);
            byte[] jurisdictionVersionNumber = Arrays.copyOfRange(data, 17, 19);
            int numberOfEntries = dataToInt(Arrays.copyOfRange(data, 19, 21));
            int index = 21;
            AAMVADocumentData documentData = null;
            for (int i=0; i<numberOfEntries; i++) {
                String subfileType = new String(Arrays.copyOfRange(data, index, index+2), UTF8);
                int offset = dataToInt(Arrays.copyOfRange(data, index+2, index+6));
                int length = dataToInt(Arrays.copyOfRange(data, index+6, index+10));
                int start = Math.min(offset, data.length);
                int end = Math.min(offset+length, data.length);
                if (numberOfEntries == 1 && offset == 0) {
                    start = data.length - length;
                    end = data.length;
                }
                AAMVADocumentData subData = subfileParser.parseFields(Arrays.copyOfRange(data, start, end));
                if (documentData == null) {
                    documentData = subData;
                } else {
                    documentData.appendFieldsFrom(subData);
                }
                index += 10;
            }
            if (documentData == null || documentData.isEmpty()) {
                throw new Exception("Empty document");
            }
            return documentData;
        } else if (data[0] == 0x25) {
            MagStripeDocumentData documentData = new MagStripeDocumentData();
            String track = new String(data, StandardCharsets.US_ASCII);
            String jurisdiction = track.substring(1, 3);
            documentData.setValue(new DataField("State/Province", jurisdiction, jurisdiction), "State/Province");
            track = track.substring(3);
            String city = getStringToDelimiter(track, "^", 13);
            documentData.setValue(new DataField("City", city, city), "City");
            track = track.substring(city.length());
            track = leftTrimString(track, "^");
            String name = getStringToDelimiter(track, "^", 35);
            String[] names = name.split("\\$");
            if (names.length > 2) {
                documentData.setValue(new DataField("Title", names[2], names[2].trim()), "Title");
            }
            if (names.length > 1) {
                documentData.setValue(new DataField("First name", names[1], StringUtils.strip(names[1], "/, ")), "First name");
            }
            if (names.length > 0) {
                documentData.setValue(new DataField("Last name", names[0], StringUtils.strip(names[0], "/, ")), "Last name");
            }
            track = track.substring(name.length());
            track = leftTrimString(track, "^");
            String address = getStringToDelimiter(track, "^", 77 - city.length() - name.length());
            address = getStringToDelimiter(address, "?", address.length());
            String[] addressFields = address.split("\\$");
            address = TextUtils.join("\n", addressFields);
            documentData.setValue(new DataField("Address", address, address), "Address");
            if (track.substring(0, 1).equals("?")) {
                track = track.substring(1);
            }
            int delimiterIndex = track.indexOf(";");
            if (delimiterIndex > -1) {
                track = track.substring(delimiterIndex+1);
                String iin = track.substring(0, 6);
                documentData.setValue(new DataField("IIN", iin, iin), "IIN");
                track = track.substring(6);
                String dlNo = getStringToDelimiter(track, "=", 13);
                track = track.substring(dlNo.length());
                track = leftTrimString(track, "=");
                String expiryYear = "20"+track.substring(0, 2);
                String expiryMonth = track.substring(2, 4);
                track = track.substring(4);
                String birthYear = track.substring(0, 4);
                String birthMonth = track.substring(4, 6);
                String birthDate = track.substring(6, 8);
                track = track.substring(8);
                String expiryDate = null;
                if (expiryMonth.equals("77")) {
                    expiryDate = "non-expiring";
                } else if (expiryMonth.equals("88")) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Integer.parseInt("20" + expiryYear) + 1, Integer.parseInt(birthMonth), 1);
                    expiryDate = Integer.toString(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                    expiryDate = expiryDate + "/" + birthMonth + "/" + expiryYear;
                } else if (expiryMonth.equals("99")) {
                    expiryDate = birthDate + "/" + birthMonth + "/" + expiryYear;
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Integer.parseInt("20" + expiryYear), Integer.parseInt(expiryMonth), 1);
                    expiryDate = Integer.toString(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                    expiryDate = expiryDate + "/" + expiryMonth + "/" + expiryYear;
                }
                documentData.setValue(new DataField("Date of expiry", expiryDate, expiryDate), "Date of expiry");
                documentData.setValue(new DataField("Date of birth", birthDate+birthMonth+birthYear, birthDate+"/"+birthMonth+"/"+birthYear), "Date of birth");
                if (track.length() > 0) {
                    String dlNoOverflow = getStringToDelimiter(track, "=", 5);
                    if (!dlNoOverflow.isEmpty()) {
                        dlNo += dlNoOverflow;
                    }
                }
                documentData.setValue(new DataField("DL/ID#", dlNo, dlNo), "DL/ID#");
                delimiterIndex = track.indexOf("%");
            }
            if (delimiterIndex > -1) {
                track = track.substring(delimiterIndex+1);
                String versionNumber = track.substring(0, 1);
                documentData.setValue(new DataField("Version #", versionNumber, versionNumber), "Version #");
                track = track.substring(1);
                String securityVersionNumber = track.substring(0, 1);
                documentData.setValue(new DataField("Security v. #", securityVersionNumber, securityVersionNumber), "Security v. #");
                track = track.substring(1);
                String postalCode = StringUtils.strip(track.substring(0, 11), "/, ");
                documentData.setValue(new DataField("Postal code", postalCode, postalCode), "Postal code");
                track = track.substring(11);
                String dlClass = track.substring(0, 2).trim();
                if (!dlClass.isEmpty()) {
                    documentData.setValue(new DataField("Class", dlClass, dlClass), "Class");
                }
                track = track.substring(2);
                String restrictions = track.substring(0, 10).trim();
                if (!restrictions.isEmpty()) {
                    documentData.setValue(new DataField("Restrictions", restrictions, restrictions), "Restrictions");
                }
                track = track.substring(10);
                String endorsements = track.substring(0, 4).trim();
                if (!endorsements.isEmpty()) {
                    documentData.setValue(new DataField("Endorsements", endorsements, endorsements), "Endorsements");
                }
                track = track.substring(4);
                String sex = track.substring(0, 1);
                documentData.setValue(new DataField("Sex", sex, sex), "Sex");
                track = track.substring(1);
                String height = track.substring(0, 3).trim();
                if (!height.isEmpty()) {
                    documentData.setValue(new DataField("Height", height, height), "Height");
                }
                track = track.substring(3);
                String weight = track.substring(0, 3).trim();
                if (!weight.isEmpty()) {
                    documentData.setValue(new DataField("Weight", weight, weight), "Weight");
                }
                track = track.substring(3);
                String hairColour = track.substring(0, 3).trim();
                if (!hairColour.isEmpty()) {
                    documentData.setValue(new DataField("Hair color", hairColour, getHairColour(hairColour)), "Hair color");
                }
                track = track.substring(3);
                String eyeColour = track.substring(0, 3).trim();
                if (!eyeColour.isEmpty()) {
                    documentData.setValue(new DataField("Eye color", eyeColour, getEyeColour(eyeColour)), "Eye color");
                }
            }
            return documentData;
        }
        throw new Exception("Nothing decoded");
    }

    private static String leftTrimString(String string, String toTrim) {
        if (string.startsWith(toTrim)) {
            string = string.substring(toTrim.length());
        }
        return string;
    }

    private static String getStringToDelimiter(String string, String delimiter, int maxLength) {
        int delimiterIndex = string.indexOf(delimiter);
        if (delimiterIndex == -1 || delimiterIndex > maxLength) {
            return string.substring(0, maxLength);
        }
        return string.substring(0, delimiterIndex);
    }

    private static byte calculateLRC(byte[] data) {
        byte lrc = 0;
        for (int i=0; i<=data.length; i++) {
            lrc ^= data[i];
        }
        return lrc;
    }

    private static String getEyeColour(String abbreviation) {
        if ("BLK".equals(abbreviation)) {
            return "Black";
        }
        if ("BLU".equals(abbreviation)) {
            return "Blue";
        }
        if ("BRO".equals(abbreviation)) {
            return "Brown";
        }
        if ("DIC".equals(abbreviation)) {
            return "Dichromatic";
        }
        if ("GRY".equals(abbreviation)) {
            return "Gray";
        }
        if ("GRN".equals(abbreviation)) {
            return "Green";
        }
        if ("HAZ".equals(abbreviation)) {
            return "Hazel";
        }
        if ("MAR".equals(abbreviation)) {
            return "Maroon";
        }
        if ("PNK".equals(abbreviation)) {
            return "Pink";
        }
        if ("UNK".equals(abbreviation)) {
            return "Unknown";
        }
        return abbreviation;
    }

    private static String getHairColour(String abbreviation) {
        if ("BAL".equals(abbreviation)) {
            return "Bald";
        }
        if ("BLK".equals(abbreviation)) {
            return "Black";
        }
        if ("BLN".equals(abbreviation)) {
            return "Blond";
        }
        if ("BRO".equals(abbreviation)) {
            return "Brown";
        }
        if ("GRY".equals(abbreviation)) {
            return "Gray";
        }
        if ("RED".equals(abbreviation)) {
            return "Red/Auburn";
        }
        if ("SDY".equals(abbreviation)) {
            return "Sandy";
        }
        if ("WHI".equals(abbreviation)) {
            return "White";
        }
        if ("UNK".equals(abbreviation)) {
            return "Unknown";
        }
        return abbreviation;
    }

    private static Charset UTF8 = StandardCharsets.UTF_8;

    private static int dataToInt(byte[] data) {
        String str = new String(data, UTF8);
        return Integer.parseInt(str);
    }
}
