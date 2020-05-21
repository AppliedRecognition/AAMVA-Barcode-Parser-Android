package com.appliedrec.aamvabarcodeparser;

/**
 * Represents information encoded in a magnetic stripe
 */
public class MagStripeDocumentData extends DocumentData {

    @Override
    public String getFirstName() {
        return getValueForKey("First name");
    }

    @Override
    public String getLastName() {
        return getValueForKey("Last name");
    }

    @Override
    public String getAddress() {
        return getValueForKey("Address");
    }

    @Override
    public String getDateOfBirth() {
        return getValueForKey("Date of birth");
    }

    @Override
    public String getDateOfExpiry() {
        return getValueForKey("Date of expiry");
    }

    @Override
    public String getDateOfIssue() {
        return null;
    }

    @Override
    public String getDocumentNumber() {
        return getValueForKey("DL/ID#");
    }

    @Override
    public String getSex() {
        return getValueForKey("Sex");
    }
}
