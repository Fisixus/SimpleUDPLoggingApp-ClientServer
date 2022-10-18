package JavaStream;

import java.io.FileReader;
import java.io.IOException;

// Note: FileReader is a character stream

public class ReadTextFile {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("readme.txt");
        int i;
        while ((i = fileReader.read()) != -1) {
            System.out.println((char) i);
        }
        fileReader.close();
    }
}
