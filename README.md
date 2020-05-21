# AAMVA Barcode Parser

Parse barcodes on North American ID cards encoded using [AAMVA standard](https://www.aamva.org/DL-ID-Card-Design-Standard/).
    
## Usage
```java
/**
 * @param barcodeData Barcode data scanned from the back of a North American ID card
 * @return Parsed document data
 */
DocumentData parseBarcodeData(byte[] barcodeData) throws Exception {
    AAMVABarcodeParser parser = new AAMVABarcodeParser();
    DocumentData documentData = parser.parseData(barcodeData);
    String firstName = documentData.getFirstName();
    String lastName = documentData.getLastName();
    if (firstName != null && lastName != null) {
        System.out.println("Parsed ID card belonging to "+firstName+" "+lastName);
    }
    return documentData;
}
```

## [Reference documentation](https://appliedrecognition.github.io/AAMVA-Barcode-Parser-Android/)
