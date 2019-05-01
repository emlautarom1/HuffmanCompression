package lib;

import huffman.lib.Compressor;
import huffman.lib.Decompressor;
import huffman.lib.models.CompressionResult;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class E2ETest {
    private byte[] dataBytes;
    private static final String fileName = "small.txt";

    @Before
    public void setUp() throws Exception {
        File file = new File(this.getClass().getResource(fileName).toURI());
        this.dataBytes = Files.readAllBytes(file.toPath());
    }

    @Test
    public void compressAndDecompress() {
        CompressionResult cr = Compressor.compress(dataBytes);
        byte[] restoredData = Decompressor.decompress(cr);
        assertArrayEquals(dataBytes, restoredData);
    }
}
