package app.huffman.models;

import java.util.BitSet;

public class CompressionResult {
    private final int validBitCount;
    private final BitSet compressedData;
    private final Node trieRoot;

    public CompressionResult(int validBitCount, BitSet compressedData, Node trieRoot) {
        this.validBitCount = validBitCount;
        this.compressedData = compressedData;
        this.trieRoot = trieRoot;
    }

    public int getValidBitCount() {
        return validBitCount;
    }

    public boolean getCompressedBit(int index) {
        return this.compressedData.get(index);
    }

    public Node getTrieRoot() {
        return trieRoot;
    }
}
