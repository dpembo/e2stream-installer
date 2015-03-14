package uk.co.pembo.e2stream.server;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import uk.co.pembo.utils.FileUtils;

public class AlternateDirectory
{

    public AlternateDirectory()
    {
    }

    public static List getFiles()
    {
        return files;
    }

    public static void setFiles(List files)
    {
        files = files;
    }

    public static List getFilenames()
    {
        return filenames;
    }

    public static void setFilenames(List filenames)
    {
        filenames = filenames;
    }
    
    public static void loadFilesInDirectory(String dir)
        throws Exception
    {
        files = new ArrayList();
        filenames = new ArrayList();
        File filelist[] = (new File(dir)).listFiles();
        if(files == null)
            return;
        File afile[];
        int j = (afile = filelist).length;
        for(int i = 0; i < j; i++)
        {
            File file = afile[i];
            if(file.isFile() && checkZipFileIsApp(file.getAbsolutePath()))
            {
                filenames.add(file.getName());
                byte loadedFile[] = FileUtils.loadFile(file.getAbsolutePath());
                files.add(loadedFile);
            }
        }

    }
    
    public static boolean checkZipFileIsApp(String zipname) throws Exception
    {
    	boolean valid = false;
    	if(!zipname.toLowerCase().endsWith(".zip"))return false;
    	ZipFile zipFile = new ZipFile(zipname);
    	Enumeration zipEntries = zipFile.entries();
    	String fname;
    	while (zipEntries.hasMoreElements()) {
    	    fname = ((ZipEntry)zipEntries.nextElement()).getName();
    	    if(fname.toLowerCase().equals("config.xml"))valid=true;
    	}
    	return valid;
    }

    public static void main(String args[])
    {
        System.out.println("ALT DIRs");
        try
        {
            loadFilesInDirectory("D:\\altdir\\");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static List files = null;
    public static List filenames = null;

}
