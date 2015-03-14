package uk.co.pembo.e2stream.server.handlers;

import com.sun.net.httpserver.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;

public class ErrorResponse
    implements HttpHandler
{

    public ErrorResponse()
    {
    }

    public void handle(HttpExchange t)
        throws IOException
    {
        java.nio.file.Path p = Paths.get("error.html", new String[0]);
        Headers responseHeaders = t.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/html");
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static String response = null;

}
