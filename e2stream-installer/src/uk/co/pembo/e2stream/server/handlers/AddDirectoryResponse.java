package uk.co.pembo.e2stream.server.handlers;

import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import uk.co.pembo.e2stream.server.AlternateDirectory;
import uk.co.pembo.e2stream.server.ServerIP;
import uk.co.pembo.e2stream.server.Settings;
import uk.co.pembo.utils.JarFileExtractor;
import uk.co.pembo.utils.Logger;

public class AddDirectoryResponse
    implements HttpHandler
{

    public AddDirectoryResponse()
    {
    }

    public void handle(HttpExchange t)
        throws IOException
    {
    	//System.out.println("handling an alt dir request");
        try
        {
            response = JarFileExtractor.getOtherFile("altdirectory.html");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(response == null)response = "";
        
        //System.out.println(t.getRequestURI());
        
        String url = t .getRequestURI().toASCIIString();
        //System.out.println("URL IS: " + url);
        if(url.indexOf("altdir=")>0)
        {
        	String dir = url.substring(url.indexOf("altdir=") + 7);
        	dir = URLDecoder.decode(dir,"UTF-8");
        	Logger.logInfo("Additional Apps Directory Changed to: [" + dir + "]");
        	Settings.setAdditionalAppsDir(dir);
        }
        
        
        if(Settings.getAdditionalAppsDir()!=null && Settings.getAdditionalAppsDir().length()>0)
        {
	        //System.out.println("Response is: " + response);
	        //System.out.println("ALT DIR is : " + Settings.getAdditionalAppsDir());
        	response = response.replace("#DIR#", Settings.getAdditionalAppsDir());
	        try {
				AlternateDirectory.loadFilesInDirectory(Settings.getAdditionalAppsDir());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Logger.logError("ERROR: Unable to process files in additional directory");
				throw new RuntimeException(e);
			}
	        //Now get the apps list
	        List<String> al = AlternateDirectory.getFilenames();
	        String filelist = "";
	        for(int i=0;i<al.size();i++)
	        {
	        	filelist += "<li>";
	        	filelist += al.get(i);
	        	filelist += "</li>";
	        }
	        response = response.replaceFirst("#APPSLIST#", filelist);
	    }
        else
        {
        	response = response.replaceFirst("#DIR#", "");
        	response = response.replaceFirst("#APPSLIST#", "None Found");	
        }
        response = response.replaceFirst("#APPVERS#", Settings.getE2StreamVersion());
        
        
        
        Headers responseHeaders = t.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/html");
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static String response = null;

}
