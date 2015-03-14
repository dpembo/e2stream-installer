package uk.co.pembo.e2stream.server.handlers;

import com.sun.net.httpserver.*;

import java.io.*;
import java.util.List;

import uk.co.pembo.e2stream.server.AlternateDirectory;
import uk.co.pembo.e2stream.server.Settings;
import uk.co.pembo.utils.Logger;

public class OtherWidgetDownload
    implements HttpHandler
{

    public OtherWidgetDownload()
    {
    }

    public void handle(HttpExchange t)
        throws IOException
    {
        int index = ((Integer)t.getAttribute("idx")).intValue();
        String filename = (String)AlternateDirectory.getFilenames().get(index);
        Logger.logInfo((new StringBuilder("TV Downloading Other Package - Index [")).append(index).append("] filename [").append(filename).append("]").toString());
        byte file[] = (byte[])AlternateDirectory.getFiles().get(index);
        String response = xmlResponse;
        Headers responseHeaders = t.getResponseHeaders();
        responseHeaders.set("Content-type", "application/octet-stream");
        responseHeaders.set("Content-disposition", (new StringBuilder("filename=")).append(filename).toString());
        t.sendResponseHeaders(200, file.length);
        OutputStream os = t.getResponseBody();
        os.write(file);
        os.close();
        Logger.logInfo("Download Complete");
    }

    private static String xmlResponse = (new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><rsp stat=\"ok\"><list><widget id=\"")).append(Settings.getId()).append("\">").append("<title>").append(Settings.getTitle()).append("</title>").append("<compression size=\"[SIZE]\" type=\"zip\"/>").append("<description>").append(Settings.getDescrption()).append("</description>").append("<download>http://").append(Settings.getIpAddress()).append("/Widget/").append(Settings.getFilename()).append("</download>").append("</widget>").append("</list>").append("</rsp>").toString();

}
