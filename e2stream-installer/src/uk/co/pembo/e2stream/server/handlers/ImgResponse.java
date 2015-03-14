package uk.co.pembo.e2stream.server.handlers;

import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;

import uk.co.pembo.e2stream.server.ServerIP;
import uk.co.pembo.e2stream.server.Settings;
import uk.co.pembo.utils.JarFileExtractor;

public class ImgResponse
    implements HttpHandler
{

	String fileName = null;
	
    public ImgResponse(String file)
    {
    	this.fileName = file;
    }

	public ImgResponse()
    {
    }

    public void handle(HttpExchange t)
        throws IOException
    {
        try
        {
            response = JarFileExtractor.getOtherFileAsByte(this.fileName);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
        Headers responseHeaders = t.getResponseHeaders();
        responseHeaders.set("Content-Type", "image/png");
        t.sendResponseHeaders(200, response.length);
        OutputStream os = t.getResponseBody();
        os.write(response);
        os.close();
    }

    private static byte[] response = null;

}
