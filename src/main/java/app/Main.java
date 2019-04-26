package app;

import app.huffman.Compressor;
import app.huffman.Decompressor;
import app.huffman.models.CompressionResult;
import app.huffman.services.Serializator;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        System.out.println("Program start");
        long startTime = System.nanoTime();

        String fileFullName = "big.txt";
        String compressionPath = "result.ser";

        try {
            File file = new File(Main.class.getResource(fileFullName).toURI());
            byte[] dataBytes = Files.readAllBytes(file.toPath());

            CompressionResult cr = Compressor.compress(dataBytes);

            Serializator.serialize(cr, compressionPath);
            CompressionResult compressedData = Serializator.deserialize(compressionPath);

            byte[] restoredData = Decompressor.decompress(compressedData);

            Files.write(Paths.get("r_" + fileFullName), restoredData);

            long endTime = System.nanoTime();
            System.out.println("Program finished succesfully in " + (endTime - startTime) / 1000000 + " milliseconds");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
