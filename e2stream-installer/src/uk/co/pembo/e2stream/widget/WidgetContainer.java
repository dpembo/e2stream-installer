package uk.co.pembo.e2stream.widget;

import java.io.File;
import java.nio.file.*;

public class WidgetContainer
{

	public static byte file[];

    public WidgetContainer()
    {
    }

    public static byte[] getFile()
    {
        return file;
    }

    public static void setFile(byte infile[])
    {
    	
        file = infile;
    }

    public static void main(String args[])
        throws Exception
    {
        //File f = new File("E2stream.pkg");
    }

    public static void loadFile(String filename)
        throws Exception
    {
        java.nio.file.Path p = Paths.get(filename, new String[0]);
        file = Files.readAllBytes(p);
    }

    public static void writeFile(String filename)
        throws Exception
    {
        java.nio.file.Path p = Paths.get(filename, new String[0]);
        Files.write(p, file, new OpenOption[] {
            StandardOpenOption.CREATE
        });
    }

}
