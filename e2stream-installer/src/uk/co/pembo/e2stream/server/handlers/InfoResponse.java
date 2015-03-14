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

public class InfoResponse
    implements HttpHandler
{

    public InfoResponse()
    {
    }

    public void handle(HttpExchange t)
        throws IOException
    {
        Path p = Paths.get("index.html", new String[0]);
        try
        {
            response = JarFileExtractor.getIndex();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(response == null)
            response = "";
        
        response = response.replaceFirst("#IPAddress#", ServerIP.getLocalHostLANAddress().getHostAddress());
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
