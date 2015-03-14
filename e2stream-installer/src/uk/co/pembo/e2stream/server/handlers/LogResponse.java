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
import uk.co.pembo.utils.Logger;

public class LogResponse
    implements HttpHandler
{

    public LogResponse()
    {
    }

    public void handle(HttpExchange t)
        throws IOException
    {
    	//Logger.logInfo("LOG RESPONSE");
        //Path p = Paths.get("index.html", new String[0]);
        response = "<pre>" + Logger.getLines() + "</pre>";
        if(response == null)
            response = "";
        
        
        Headers responseHeaders = t.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/html");
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static String response = null;

}
