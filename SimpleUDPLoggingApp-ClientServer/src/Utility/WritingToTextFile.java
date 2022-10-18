package JavaStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

// Note: FileWriter, StringReader, and StringWriter are all character streams

public class WritingToTextFile {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("output.txt");
        StringWriter stringWriter = new StringWriter();
        stringWriter.write("Testing\nLine 2 \nLine 3");
        stringWriter.write("Line 4");
        stringWriter.write("\nByebye");
        stringWriter.close();

        StringReader stringReader = new StringReader(stringWriter.toString());
        int i;
        while ((i = stringReader.read()) != -1) {
            fileWriter.write((char) i);
        }
        stringReader.close();
        fileWriter.close();
    }
}
