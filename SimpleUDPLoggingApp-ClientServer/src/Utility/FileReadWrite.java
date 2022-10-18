package Utility;

import java.io.*;

public class FileReadWrite {

    public static String ReadTextFile(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        int i;
        StringBuilder sb = new StringBuilder();
        while ((i = fileReader.read()) != -1) {
            sb.append((char)i);
        }
        fileReader.close();
        return sb.toString();
    }

    public static void AppendTextFile(String path, String writingLine) throws IOException {
        try
        {
            FileWriter fw = new FileWriter(path,true); //the true will append the new data
            fw.write(writingLine + "\n");//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

}
