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

public class StringBasedResponse
    implements HttpHandler
{

	String fileName = null;
	private static String response = null;

    public StringBasedResponse()
    {
    	this.fileName = "style.css";
    }

    public StringBasedResponse(String file)
    {
    	this.fileName = file;
    }

    
    public void handle(HttpExchange t)
            throws IOException
    {
            try
            {
                response = JarFileExtractor.getOtherFile(fileName);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            if(response == null)
                response = "";
            
            Headers responseHeaders = t.getResponseHeaders();
            
	    if(fileName.toUpperCase().endsWith(".JS"))
	    {
              responseHeaders.set("Content-Type", "application/javascript");
            }
	    else
	    {
	       responseHeaders.set("Content-Type", "text/css");
	    }
	    
	    t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
    }


    
}
