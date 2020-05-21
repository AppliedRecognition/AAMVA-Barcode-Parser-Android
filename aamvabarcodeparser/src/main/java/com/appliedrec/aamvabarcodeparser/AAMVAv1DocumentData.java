package com.appliedrec.aamvabarcodeparser;

import java.util.EnumSet;

/**
 * Represents information encoded using the AAMVA version 1 standard
 */
public class AAMVAv1DocumentData extends AAMVADocumentData {
    AAMVAv1DocumentData() throws Exception {
        super(new AAMVABarcodeParser.DataElement[]{
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAA", "Driver License Name", new AAMVABarcodeParser.ElementParams("V150ANS")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAG", "Driver Mailing Street Address 1", new AAMVABarcodeParser.ElementParams("V150ANS")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAI", "Driver Mailing City", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAJ", "Driver Mailing Jurisdiction Code", new AAMVABarcodeParser.ElementParams("V5ANS")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAK", "Driver Mailing Postal Code", new AAMVABarcodeParser.ElementParams("V11ANS")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAQ", "Driver License/ID Number", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAR", "Driver License Classification Code", new AAMVABarcodeParser.ElementParams("F4AN")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAS", "Driver License Restriction Code", new AAMVABarcodeParser.ElementParams("F10AN")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAT", "Driver License Endorsements Code", new AAMVABarcodeParser.ElementParams("F5AN")),
                new AAMVABarcodeParser.DateDataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBA", "Driver License Expiration Date", new AAMVABarcodeParser.ElementParams("F8N")),
                new AAMVABarcodeParser.DateDataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBB", "Date of Birth", new AAMVABarcodeParser.ElementParams("F8N")),
                new AAMVABarcodeParser.SexDataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBC", "Driver Sex", new AAMVABarcodeParser.ElementParams("F1N")),
                new AAMVABarcodeParser.DateDataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBD", "Driver License or ID Document Issue Date", new AAMVABarcodeParser.ElementParams("F8N")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAU", "Height (FT/IN)", new AAMVABarcodeParser.ElementParams("V10ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAW", "Weight (LBS)", new AAMVABarcodeParser.ElementParams("V10ANS")),
                new AAMVABarcodeParser.EyeColourDataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAY", "Eye Color", new AAMVABarcodeParser.ElementParams("F3A")),
                new AAMVABarcodeParser.HairColourDataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAZ", "Hair Color", new AAMVABarcodeParser.ElementParams("V5ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBK", "Social Security Number", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "PAA", "Driver Permit Classification Code", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "PAB", "Driver Permit Expiration Date", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "PAC", "Permit Identifier", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "PAD", "Driver Permit Issue Date", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "PAE", "Driver Permit Restriction Code", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "PAF", "Driver Permit Endorsement Code", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAB", "Driver Last Name", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAC", "Driver First Name", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAD", "Driver Middle Name or Initial Driver Name Suffix", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAF", "Driver Name Prefix", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAH", "Driver Mailing Street Address 2", new AAMVABarcodeParser.ElementParams("V100ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAL", "Driver Residence Street Address 1", new AAMVABarcodeParser.ElementParams("V100ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAM", "Driver Residence Street Address 2", new AAMVABarcodeParser.ElementParams("V100ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAN", "Driver Residence City", new AAMVABarcodeParser.ElementParams("V100ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAO", "Driver Residence Jurisdiction Code", new AAMVABarcodeParser.ElementParams("V100ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAP", "Driver Residence Postal Code", new AAMVABarcodeParser.ElementParams("V11ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAV", "Height (CM)", new AAMVABarcodeParser.ElementParams("V10ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DAX", "Weight (KG)", new AAMVABarcodeParser.ElementParams("V10ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBE", "Issue Timestamp", new AAMVABarcodeParser.ElementParams("V100ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBF", "Number of Duplicates", new AAMVABarcodeParser.ElementParams("V5N")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBG", "Medical Indicator/Codes", new AAMVABarcodeParser.ElementParams("V20ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBH", "Organ Donor", new AAMVABarcodeParser.ElementParams("V20ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBI", "Non-Resident Indicator", new AAMVABarcodeParser.ElementParams("V20ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBJ", "Unique Customer Identifier", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DateDataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBL", "Driver \"AKA\" Date Of Birth", new AAMVABarcodeParser.ElementParams("F8N")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBM", "Driver \"AKA\" Social Security Number", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBN", "Driver \"AKA\" Name", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBO", "Driver \"AKA\" Last Name", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBP", "Driver \"AKA\" First Name", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBQ", "Driver \"AKA\" Middle Name", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBR", "Driver \"AKA\" Suffix", new AAMVABarcodeParser.ElementParams("V5ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DBS", "Driver \"AKA\" Prefix", new AAMVABarcodeParser.ElementParams("V5ANS"))
        });
    }

    @Override
    public String getLastName() {
        return getValueForKey("DAB");
    }
}
