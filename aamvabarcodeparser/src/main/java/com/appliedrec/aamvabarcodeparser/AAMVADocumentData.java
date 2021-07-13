package com.appliedrec.aamvabarcodeparser;

/**
 * Base class for barcodes containing information encoded in AAMVA standard
 */
public abstract class AAMVADocumentData extends DocumentData {

    final AAMVABarcodeParser.DataElement[] elements;

    AAMVADocumentData(AAMVABarcodeParser.DataElement[] elements) {
        this.elements = elements;
    }

    @Override
    public String getFirstName() {
        return getValueForKey("DAC");
    }

    @Override
    public String getLastName() {
        return getValueForKey("DCS");
    }

    @Override
    public String getAddress() {
        String city = getValueForKey("DAI");
        String state = getValueForKey("DAJ");
        String cityAndState = null;
        if (city != null && !city.isEmpty()) {
            cityAndState = city;
        }
        if (state != null && !state.isEmpty()) {
            if (cityAndState != null) {
                cityAndState += ", "+state;
            } else {
                cityAndState = state;
            }
        }
        String[] addressArray = new String[]{getValueForKey("DAG"),getValueForKey("DAH"),cityAndState,getValueForKey("DAK")};
        StringBuilder stringBuilder = new StringBuilder();
        for (String val : addressArray) {
            if (val != null && !val.isEmpty()) {
                stringBuilder.append(val).append("\n");
            }
        }
        return stringBuilder.toString().trim();
    }

    @Override
    public String getDateOfBirth() {
        return getValueForKey("DBB");
    }

    @Override
    public String getDateOfExpiry() {
        return getValueForKey("DBA");
    }

    @Override
    public String getDateOfIssue() {
        return getValueForKey("DBD");
    }

    @Override
    public String getDocumentNumber() {
        return getValueForKey("DAQ");
    }

    @Override
    public String getSex() {
        return getValueForKey("DBC");
    }
}
