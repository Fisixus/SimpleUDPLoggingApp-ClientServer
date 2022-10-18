package JavaStream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

// Note: FileInputStream, ByteArrayInputStream, and DataInputStream are byte streams


public class ReadBinaryFile {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("output.bin");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileInputStream.readAllBytes());
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        int stringLength = dataInputStream.readInt();
        String name = new String(byteArrayInputStream.readNBytes(stringLength));
        int age = dataInputStream.readInt();
        double grade = dataInputStream.readDouble();
        dataInputStream.close();
        byteArrayInputStream.close();
        fileInputStream.close();
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Grade: " + grade);
    }
}
