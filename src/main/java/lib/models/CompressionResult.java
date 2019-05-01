package lib.models;

import java.io.*;
import java.util.BitSet;
import java.util.Objects;

public class CompressionResult implements Serializable {
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

    public int getByteCount() {
        return (int) Math.ceil(this.validBitCount / 8);
    }

    public byte[] toByteArray() throws Exception {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
        objectStream.writeObject(this);
        objectStream.flush();
        objectStream.close();
        return byteStream.toByteArray();
    }

    public static CompressionResult fromByteArray(byte[] source) throws Exception {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(source);
        ObjectInputStream objectStream = new ObjectInputStream(byteStream);
        return (CompressionResult) objectStream.readObject();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompressionResult that = (CompressionResult) o;
        return validBitCount == that.validBitCount &&
                Objects.equals(compressedData, that.compressedData) &&
                Objects.equals(trieRoot, that.trieRoot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(validBitCount, compressedData, trieRoot);
    }
}
