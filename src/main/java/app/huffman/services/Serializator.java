package app.huffman.services;

import app.huffman.models.CompressionResult;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializator {
    public static void serialize(CompressionResult toSerialize, String path) throws Exception{
        FileOutputStream fileOut = new FileOutputStream(path);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(toSerialize);
        objectOut.close();
        fileOut.close();
    }

    public static CompressionResult deserialize(String path) throws Exception{
        CompressionResult object;
        FileInputStream fileIn = new FileInputStream(path);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        object = (CompressionResult) objectIn.readObject();
        objectIn.close();
        fileIn.close();
        if (object == null) {
            throw new Exception("Failed to deserialize: object is null");
        }
        return object;
    }
}
