package lib;

import huffman.lib.Compressor;
import huffman.lib.models.CompressionResult;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class CompressorTest {
    private byte[] dataBytes;
    private static final String fileName = "small.txt";

    @Before
    public void setUp() throws Exception {
        File file = new File(this.getClass().getResource(fileName).toURI());
        this.dataBytes = Files.readAllBytes(file.toPath());
    }

    @Test
    public void compress() {
        CompressionResult cr = Compressor.compress(dataBytes);
        assertTrue(
                "Compression reduced file size",
                cr.getByteCount() <= dataBytes.length
        );
    }

    @Test (expected = IllegalArgumentException.class)
    public void compressEmptyAndNullArray() {
        try {
            Compressor.compress(new byte[0]);
        } catch (IllegalArgumentException e) {
            Compressor.compress(null);
        }
    }
}