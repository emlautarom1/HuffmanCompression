package lib.models;

import java.io.Serializable;
import java.util.Objects;

// Huffman trie node
public class Node implements Comparable<Node>, Serializable {
    private final byte byteData;
    private final int frecuency;
    private final Node left, right;

    public Node(byte byteData, int frecuency, Node left, Node right) {
        this.byteData = byteData;
        this.frecuency = frecuency;
        this.left = left;
        this.right = right;
    }

    public int getFrecuency() {
        return frecuency;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public boolean isLeaf() {
        return (left == null) && (right == null);
    }

    public byte getByteData() {
        return byteData;
    }

    @Override
    public int compareTo(Node o) {
        return this.frecuency - o.frecuency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return byteData == node.byteData &&
                frecuency == node.frecuency &&
                Objects.equals(left, node.left) &&
                Objects.equals(right, node.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(byteData, frecuency, left, right);
    }
}
