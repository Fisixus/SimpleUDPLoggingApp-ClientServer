package JavaStream;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// Note: FileOutputStream, ByteArrayOutputStream, and DataOutputStream are byte streams

public class BinaryFileWriter {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("output.bin");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        String name = "John Smith";
        int age = 40;
        double grade = 3.75;
        dataOutputStream.writeInt(name.length());
        dataOutputStream.writeBytes(name);
        dataOutputStream.writeInt(age);
        dataOutputStream.writeDouble(grade);
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
        fileOutputStream.close();
    }
}
