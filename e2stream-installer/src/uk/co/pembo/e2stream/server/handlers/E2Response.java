package uk.co.pembo.e2stream.server.handlers;

import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;

import uk.co.pembo.e2stream.server.ServerIP;
import uk.co.pembo.e2stream.server.Settings;
import uk.co.pembo.utils.FileUtils;
import uk.co.pembo.utils.JarFileExtractor;

public class E2Response
    implements HttpHandler
{

	private String fileName = null;
	public E2Response(String file)
	{
		//System.out.println("In E2Response for File: " + file);
		this.fileName = file;
	}
	
    public E2Response()
    {
    }

    public void handle(HttpExchange t)
        throws IOException
    {
        //System.out.println("Handle for: " + fileName);
        try
        {
        	byte[] file = FileUtils.loadFile(fileName);
            String s = new String(file);
        	response = s;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(response == null)
            response = "";
                
        Headers responseHeaders = t.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/xml");
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static String response = null;

}
