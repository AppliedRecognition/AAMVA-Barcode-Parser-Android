package com.appliedrec.aamvabarcodeparser;

import java.util.EnumSet;

/**
 * Represents information encoded using the AAMVA version 2 standard
 */
public class AAMVAv2DocumentData extends AAMVADocumentData {

    AAMVAv2DocumentData() throws Exception {
        super(new AAMVABarcodeParser.DataElement[]{
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DCA", "Jurisdiction-specific vehicle class", new AAMVABarcodeParser.ElementParams("V4ANS")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DCB", "Jurisdiction-specific restriction codes", new AAMVABarcodeParser.ElementParams("V10ANS")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DCD", "Jurisdiction-specific endorsement codes", new AAMVABarcodeParser.ElementParams("V5ANS")),
                new AAMVABarcodeParser.DateDataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DBA", "Document Expiration Date", new AAMVABarcodeParser.ElementParams("F8N")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DCS", "Customer Family Name", new AAMVABarcodeParser.ElementParams("V40ANS")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DCT", "Customer Given Names", new AAMVABarcodeParser.ElementParams("V80ANS")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DCU", "Name Suffix", new AAMVABarcodeParser.ElementParams("V5ANS")),
                new AAMVABarcodeParser.DateDataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DBD", "Document Issue Date", new AAMVABarcodeParser.ElementParams("F8N")),
                new AAMVABarcodeParser.DateDataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DBB", "Date of Birth", new AAMVABarcodeParser.ElementParams("F8N")),
                new AAMVABarcodeParser.SexDataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DBC", "Physical Description – Sex", new AAMVABarcodeParser.ElementParams("F1N")),
                new AAMVABarcodeParser.EyeColourDataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DAY", "Physical Description – Eye Color", new AAMVABarcodeParser.ElementParams("F3A")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DAU", "Physical Description – Height", new AAMVABarcodeParser.ElementParams("F6AN")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DCE", "Physical Description – Weight Range", new AAMVABarcodeParser.ElementParams("F1N")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DAG", "Address – Street 1", new AAMVABarcodeParser.ElementParams("V35ANS")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DAI", "Address – City", new AAMVABarcodeParser.ElementParams("V20ANS")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DAJ", "Address – Jurisdiction Code", new AAMVABarcodeParser.ElementParams("F2A")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DAK", "Address – Postal Code", new AAMVABarcodeParser.ElementParams("F11AN")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DAQ", "Customer ID Number", new AAMVABarcodeParser.ElementParams("V25ANS")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DCF", "Document Discriminator", new AAMVABarcodeParser.ElementParams("V25ANS")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DCG", "Country Identification", new AAMVABarcodeParser.ElementParams("F3A")),
                new AAMVABarcodeParser.DataElement(true, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DCH", "Federal Commercial Vehicle Codes", new AAMVABarcodeParser.ElementParams("F4AN")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DAH", "Address – Street 2", new AAMVABarcodeParser.ElementParams("V35ANS")),
                new AAMVABarcodeParser.HairColourDataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DAZ", "Hair color", new AAMVABarcodeParser.ElementParams("V12A")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DCI", "Place of birth", new AAMVABarcodeParser.ElementParams("V33A")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DCJ", "Audit information", new AAMVABarcodeParser.ElementParams("V25ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DCK", "Inventory control number", new AAMVABarcodeParser.ElementParams("V25ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DBN", "Alias / AKA Family Name", new AAMVABarcodeParser.ElementParams("V35ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL, AAMVABarcodeParser.CardType.ID), "DCL", "Race / ethnicity", new AAMVABarcodeParser.ElementParams("F2A")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DCM", "Standard vehicle classification", new AAMVABarcodeParser.ElementParams("F4AN")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DCN", "Standard endorsement code", new AAMVABarcodeParser.ElementParams("F5AN")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DCO", "Standard restriction code", new AAMVABarcodeParser.ElementParams("F10AN")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DCP", "Jurisdiction-specific vehicle classification description", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DCQ", "Jurisdiction-specific endorsement code description", new AAMVABarcodeParser.ElementParams("V50ANS")),
                new AAMVABarcodeParser.DataElement(false, EnumSet.of(AAMVABarcodeParser.CardType.DL), "DCR", "Jurisdiction-specific restriction code description", new AAMVABarcodeParser.ElementParams("V50ANS"))
        });
    }

    @Override
    public String getFirstName() {
        return getValueForKey("DCT");
    }
}
