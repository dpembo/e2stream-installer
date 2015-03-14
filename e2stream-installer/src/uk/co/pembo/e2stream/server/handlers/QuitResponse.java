package uk.co.pembo.e2stream.server.handlers;

import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import uk.co.pembo.e2stream.server.ServerIP;
import uk.co.pembo.e2stream.server.Settings;
import uk.co.pembo.utils.JarFileExtractor;

public class QuitResponse
    implements HttpHandler
{

    public QuitResponse()
    {
    }

    public void handle(HttpExchange t)
        throws IOException
    {
    	Path p = Paths.get("exit.html", new String[0]);
        try
        {
            response = JarFileExtractor.getExit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(response == null)
            response = "";
        
        //response = response.replaceFirst("#APPVERS#", Settings.getE2StreamVersion());
        
    	Headers responseHeaders = t.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/html");
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.flush();
        os.close();
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Installer closing");
        System.exit(0);
    }

    private static String response = null;

}
