// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 18/11/2014 20:45:21
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   JarFileExtractor.java

package uk.co.pembo.utils;

import java.io.*;
import uk.co.pembo.e2stream.assets.ListAssets;
import uk.co.pembo.e2stream.widget.WidgetContainer;

public class JarFileExtractor
{

    public JarFileExtractor()
    {
    }

    public static byte[] retreiveBinaryFileFromJar(String resourceName, Object resource)
        throws Exception
    {
        WidgetContainer self = new WidgetContainer();
        boolean found = false;
        if(resourceName != null)
        {
            InputStream is = self.getClass().getResourceAsStream(resourceName);
            if(is == null)
                throw new Exception((new StringBuilder("Resource ")).append(resourceName).append(" was not found.").toString());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte buffer[] = new byte[1024];
            int bytesRead;
            while((bytesRead = is.read(buffer)) != -1) 
                baos.write(buffer, 0, bytesRead);
            baos.flush();
            br.close();
            is.close();
            found = true;
            return baos.toByteArray();
        } else
        {
            found = false;
            return null;
        }
    }

    public static byte[] getWidget()
        throws Exception
    {
        Object assets = new ListAssets();
        return retreiveBinaryFileFromJar(pckFile, assets);
    }

    public static byte[] getOtherFileAsByte(String filename) throws Exception
    {
    	Object assets = new ListAssets();
        return retreiveBinaryFileFromJar("/" + filename, assets);
    }
    
    public static String getOtherFile(String filename) throws Exception
    {
    	Object assets = new ListAssets();
        String res = new String(retreiveBinaryFileFromJar("/" + filename, assets));
    	return res;
    }

    public static String getAbout()
            throws Exception
        {
            Object assets = new ListAssets();
            return printByteArray(retreiveBinaryFileFromJar(aboutFile, assets));
        }
    
    public static String getIndex()
        throws Exception
    {
        Object assets = new ListAssets();
        return new String(retreiveBinaryFileFromJar(idxFile, assets));
    }

    public static String getExit()
            throws Exception
        {
            Object assets = new ListAssets();
            return new String(retreiveBinaryFileFromJar(exitFile, assets));
        }
    
    public static String getHelp()
        throws Exception
    {
        Object assets = new ListAssets();
        return new String(retreiveBinaryFileFromJar(hlpFile, assets));
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

    public static void main(String args[])
        throws Exception
    {
        JarFileExtractor self = new JarFileExtractor();
        byte b[] = getWidget();
        System.out.println(b.length);
    }

    public static String pckFile = "/e2stream.pkg";
    public static String idxFile = "/index.html";
    public static String hlpFile = "/error.html";
    public static String exitFile = "/exit.html";
    public static String aboutFile = "/about.html";

}
