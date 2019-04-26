package app.huffman;

import app.huffman.models.CompressionResult;
import app.huffman.models.Node;
import app.huffman.services.Util;

import java.util.ArrayList;
import java.util.List;

public class Decompressor {

    public static byte[] decompress(CompressionResult compressedData) {
        List<Byte> outputBytes = new ArrayList<>();
        int readIndex = 0;
        while (readIndex < compressedData.getValidBitCount()) {
            Node current = compressedData.getTrieRoot();
            while (!current.isLeaf()) {
                boolean bit = compressedData.getCompressedBit(readIndex);
                if (bit) {
                    current = current.getRight();
                } else {
                    current = current.getLeft();
                }
                readIndex++;
            }
            outputBytes.add(current.getByteData());
        }

        return Util.toByteArray(outputBytes);
    }
}
