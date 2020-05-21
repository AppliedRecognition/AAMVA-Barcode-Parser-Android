package com.appliedrec.aamvabarcodeparser;

class DataField {

    private final String description;
    private final String originalValue;
    private final String parsedValue;

    DataField(String description, String originalValue, String parsedValue) {
        this.description = description;
        this.originalValue = originalValue;
        this.parsedValue = parsedValue;
    }

    String getDescription() {
        return description;
    }

    String getOriginalValue() {
        return originalValue;
    }

    String getParsedValue() {
        return parsedValue;
    }
}
