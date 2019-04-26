package huffman;

import huffman.lib.Compressor;
import huffman.lib.Decompressor;
import huffman.lib.models.CompressionResult;
import huffman.lib.services.Serializator;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Huffman {

    public static long compress(String inputPath, String outputPath) throws Exception {
        long startTime = System.nanoTime();
        File file = new File(inputPath);
        byte[] dataBytes = Files.readAllBytes(file.toPath());

        CompressionResult cr = Compressor.compress(dataBytes);

        Serializator.serialize(cr, outputPath);
        return (System.nanoTime() - startTime);
    }

    public static long decompress(String inputPath, String outputPath) throws Exception {
        long startTime = System.nanoTime();

        CompressionResult compressedData = Serializator.deserialize(inputPath);
        byte[] restoredData = Decompressor.decompress(compressedData);
        Files.write(Paths.get(outputPath), restoredData);

        return (System.nanoTime() - startTime);
    }
}
