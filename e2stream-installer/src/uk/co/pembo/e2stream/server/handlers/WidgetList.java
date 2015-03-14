package uk.co.pembo.e2stream.server.handlers;

import com.sun.net.httpserver.*;

import java.io.*;
import java.util.List;

import uk.co.pembo.e2stream.server.AlternateDirectory;
import uk.co.pembo.e2stream.server.Settings;
import uk.co.pembo.e2stream.widget.WidgetContainer;
import uk.co.pembo.utils.Logger;

public class WidgetList
    implements HttpHandler
{

    public WidgetList()
    {
    }

    private static String getWidget(String id, String title, int length, String desc, String filename)
    {
        String widgetXml = (new StringBuilder("<widget id=\"")).append(id).append("\">").append("<title>").append(title).append("</title>").append("<compression size=\"").append(length).append("\" type=\"zip\"/>").append("<description>").append(desc).append("</description>").append("<download>http://").append(Settings.getIpAddress()).append("/Widget/").append(filename).append("</download>").append("</widget>").toString();
        return widgetXml;
    }

    private static String getE2StreamWidget()
    {
        return getWidget(Settings.getId(), Settings.getTitle(), WidgetContainer.getFile().length, Settings.getDescrption(), Settings.getFilename());
    }

    public static String getOtherWidgetsXml()
    {
        if(Settings.getAdditionalAppsDir() == null)
            return "";
        String widgetXml = "";
        try
        {
            for(int i = 0; i < AlternateDirectory.getFilenames().size(); i++)
            {
                String filename = (String)AlternateDirectory.getFilenames().get(i);
                if(filename != null && filename.toUpperCase().endsWith(".ZIP"))
                {
                    Logger.logInfo((new StringBuilder("Loading External Package [")).append(filename).append("]").toString());
                    int pos = filename.indexOf(".");
                    String id = filename.substring(0, pos);
                    int size = ((byte[])AlternateDirectory.getFiles().get(i)).length;
                    widgetXml = (new StringBuilder(String.valueOf(widgetXml))).append(getWidget(id, id, size, id, filename)).toString();
                }
            }

        }
        catch(Exception e)
        {
            Logger.logError("Unable to access specified additional directory");
            e.printStackTrace();
            return "";
        }
        return widgetXml;
    }

    public void handle(HttpExchange t)
        throws IOException
    {
        Logger.logInfo("Received List request from TV");
        String response = xmlResponseStart;
        response = (new StringBuilder(String.valueOf(response))).append(getE2StreamWidget()).toString();
        response = (new StringBuilder(String.valueOf(response))).append(getOtherWidgetsXml()).toString();
        response = (new StringBuilder(String.valueOf(response))).append(xmlResposneEnd).toString();
        Headers responseHeaders = t.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/xml");
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static String xmlResponseStart = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><rsp stat=\"ok\"><list>";
    private static String xmlResposneEnd = "</list></rsp>";

}
