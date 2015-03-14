package uk.co.pembo.utils;

import java.nio.file.*;

public class FileUtils
{

    public FileUtils()
    {
    }

    public static byte[] loadFile(String filename)
        throws Exception
    {
        java.nio.file.Path p = Paths.get(filename, new String[0]);
        byte file[] = Files.readAllBytes(p);
        return file;
    }

    public static void writeFile(String filename, byte file[])
        throws Exception
    {
        java.nio.file.Path p = Paths.get(filename, new String[0]);
        Files.write(p, file, new OpenOption[] {
            StandardOpenOption.CREATE
        });
    }
    
    public static String printByteArray(byte b[])
    {
        String str = "";
        for(int i = 0; i < b.length; i++)
        {
            char c = (char)b[i];
            str = (new StringBuilder(String.valueOf(str))).append(c).toString();
        }

        return str;
    }


}
