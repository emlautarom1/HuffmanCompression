package lib.models;

import huffman.lib.Compressor;
import huffman.lib.models.CompressionResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CompressionResultTest {
    private final byte[] originalBytes = {50, 50, 50, 50, 50};

    @Test
    public void toAndFromByteArray() {
        CompressionResult originalCompression = Compressor.compress(originalBytes);
        try {
            byte[] toByteArray = originalCompression.toByteArray();
            CompressionResult restoredCompression = CompressionResult.fromByteArray(toByteArray);
            assertEquals(originalCompression, restoredCompression);
        } catch (Exception e) {
            fail("Exception caught: " + e.getMessage());
        }
    }

}