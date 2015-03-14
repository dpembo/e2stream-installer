package uk.co.pembo.e2stream.server.handlers;

import com.sun.net.httpserver.*;

import java.io.*;

import uk.co.pembo.e2stream.server.Settings;
import uk.co.pembo.e2stream.widget.WidgetContainer;
import uk.co.pembo.utils.Logger;

public class WidgetDownload
    implements HttpHandler
{

    public WidgetDownload()
    {
    }

    public void handle(HttpExchange t)
        throws IOException
    {
        Logger.logInfo("TV Downloading E2Stream...");
        String response = xmlResponse;
        Headers responseHeaders = t.getResponseHeaders();
        responseHeaders.set("Content-type", "application/octet-stream");
        responseHeaders.set("Content-disposition", (new StringBuilder("filename=")).append(Settings.getFilename()).toString());
        t.sendResponseHeaders(200, WidgetContainer.getFile().length);
        OutputStream os = t.getResponseBody();
        os.write(WidgetContainer.getFile());
        os.close();
        Logger.logInfo("Download Complete");
    }

    private static String xmlResponse = (new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><rsp stat=\"ok\"><list><widget id=\"")).append(Settings.getId()).append("\">").append("<title>").append(Settings.getTitle()).append("</title>").append("<compression size=\"[SIZE]\" type=\"zip\"/>").append("<description>").append(Settings.getDescrption()).append("</description>").append("<download>http://").append(Settings.getIpAddress()).append("/Widget/").append(Settings.getFilename()).append("</download>").append("</widget>").append("</list>").append("</rsp>").toString();

}
