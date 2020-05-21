package com.appliedrec.aamvabarcodeparser;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AAMVABarcodeParserTests {

    @Test
    public void testBarcode1() throws Exception {
        DocumentData documentData = documentDataFromResource("1");
        assertEquals("ROBERTO N", documentData.getFirstName());
        assertEquals("BRONSTON", documentData.getLastName());
    }

    @Test
    public void testBarcode2() throws Exception {
        DocumentData documentData = documentDataFromResource("2");
        assertEquals("MICHAEL", documentData.getFirstName());
        assertEquals("SAMPLE", documentData.getLastName());
    }

    @Test
    public void testBarcode3() throws Exception {
        DocumentData documentData = documentDataFromResource("3");
        assertEquals("STAN CONSTANTINE", documentData.getFirstName());
        assertEquals("OGRADY", documentData.getLastName());
    }

    @Test
    public void testBarcode4() throws Exception {
        DocumentData documentData = documentDataFromResource("4");
        assertEquals("ABDULAH,M", documentData.getFirstName());
        assertEquals("ABBOTT", documentData.getLastName());
    }

    private byte[] dataFromResource(String number) throws Exception {
        try (InputStream inputStream = InstrumentationRegistry.getInstrumentation().getContext().getAssets().open("barcode_data/"+number+".txt")) {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                int read;
                byte[] buffer = new byte[256];
                while ((read = inputStream.read(buffer, 0, buffer.length)) > 0) {
                    outputStream.write(buffer, 0, read);
                }
                outputStream.flush();
                return outputStream.toByteArray();
            }
        }
    }

    private DocumentData documentDataFromResource(String number) throws Exception {
        byte[] data = dataFromResource(number);
        AAMVABarcodeParser parser = new AAMVABarcodeParser();
        return parser.parseData(data);
    }
}
