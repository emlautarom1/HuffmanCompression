package app.huffman;

import app.huffman.models.CompressionResult;
import app.huffman.models.Node;

import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;

public class Compressor {
    private static final int BYTE_VARIATIONS = 256;

    public static CompressionResult compress(byte[] input) {
        // Build a frequency Map for each byte
        HashMap<Byte, Integer> frequencies = buildFrequencyMap(input);

        // Build Huffman trie
        Node trieRoot = buildHuffmanTrie(frequencies);

        // Build a lookup table
        HashMap<Byte, String> lookUpTable = new HashMap<>(BYTE_VARIATIONS);
        buildLookUpTable(lookUpTable, trieRoot, "");

        // Encode input with the Huffman lookup table
        BitSet outputBits = new BitSet();
        int writeIndex = 0;
        for (byte b : input) {
            String bits = lookUpTable.get(b);
            for (int i = 0; i < bits.length(); i++) {
                char bit = bits.charAt(i);
                if (bit == '1') {
                    outputBits.set(writeIndex);
                } // All bits are 0 by default
                writeIndex++;
            }
        }

        return new CompressionResult(
                writeIndex,
                outputBits,
                trieRoot
        );
    }

    private static HashMap<Byte, Integer> buildFrequencyMap(byte[] input) {
        HashMap<Byte, Integer> frecuencies = new LinkedHashMap<>(BYTE_VARIATIONS);
        for (byte byteData : input) {
            frecuencies.put(
                    byteData,
                    frecuencies.getOrDefault(byteData, 0) + 1
            );
        }
        return frecuencies;
    }

    private static Node buildHuffmanTrie(HashMap<Byte, Integer> frecuencies) {
        // Initialze a Priority Queue with singleton trees
        PriorityQueue<Node> pq = new PriorityQueue<>(frecuencies.size());
        for (byte data : frecuencies.keySet()) {
            pq.add(new Node(
                    data, frecuencies.get(data), null, null
            ));
        }

        // Merge 2 smallest trees until a single tree is left in the pq
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            assert (right != null) && (left != null);
            Node parent = new Node(
                    (byte) 0,
                    (left.getFrecuency() + right.getFrecuency()),
                    left,
                    right
            );
            pq.add(parent);
        }
        return pq.poll();
    }

    private static void buildLookUpTable(HashMap<Byte, String> table, Node node, String bits) {
        if (!node.isLeaf()) {
            buildLookUpTable(table, node.getLeft(), bits + '0');
            buildLookUpTable(table, node.getRight(), bits + '1');
        } else {
            table.put(node.getByteData(), bits);
        }
    }

}
