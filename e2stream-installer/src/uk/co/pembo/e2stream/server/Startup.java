package uk.co.pembo.e2stream.server;

import com.sun.net.httpserver.*;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

import uk.co.pembo.browser.Browser;
import uk.co.pembo.e2stream.server.handlers.AboutResponse;
import uk.co.pembo.e2stream.server.handlers.AddDirectoryResponse;
import uk.co.pembo.e2stream.server.handlers.LogResponse;
import uk.co.pembo.e2stream.server.handlers.StringBasedResponse;
import uk.co.pembo.e2stream.server.handlers.E2Response;
import uk.co.pembo.e2stream.server.handlers.ImgResponse;
import uk.co.pembo.e2stream.server.handlers.InfoResponse;
import uk.co.pembo.e2stream.server.handlers.OtherWidgetDownload;
import uk.co.pembo.e2stream.server.handlers.QuitResponse;
import uk.co.pembo.e2stream.server.handlers.WidgetDownload;
import uk.co.pembo.e2stream.server.handlers.WidgetList;
import uk.co.pembo.e2stream.widget.WidgetContainer;
import uk.co.pembo.utils.JarFileExtractor;
import uk.co.pembo.utils.Logger;

public class Startup
{


    public Startup()
    {
    }

    public static void setConstants() throws Exception
    {
        InetAddress IP = ServerIP.getLocalHostLANAddress();
        Settings.setIpAddress(IP.getHostAddress());
    }

    public static void main(String args[]) throws Exception
    {
        if(args != null && args.length > 0 && args[0] != null)
            Settings.setAdditionalAppsDir(args[0]);
        setConstants();
        printStartupText();
        if(Settings.getAdditionalAppsDir() != null)
        {
            Logger.logInfo((new StringBuilder("Additional Apps Directory Provided: [")).append(Settings.getAdditionalAppsDir()).append("]").toString());
            AlternateDirectory.loadFilesInDirectory(Settings.getAdditionalAppsDir());
        }
        Logger.logInfo("Waiting for connection from TV");
        Startup self = new Startup();
        WidgetContainer.setFile(JarFileExtractor.getWidget());
        byte c[] = WidgetContainer.getFile();
        WidgetContainer.setFile(c);
        HttpServer server = null;
        try
        {
            server = HttpServer.create(new InetSocketAddress(80), 0);
            
            //TV Urls
            server.createContext("/widgetlist.xml", new WidgetList());
            server.createContext((new StringBuilder("/Widget/")).append(Settings.getFilename()).toString(), new WidgetDownload());
            
            //Main Installer URLs
            server.createContext("/index.html", new InfoResponse());
            //server.createContext("/", new InfoResponse());
            server.createContext("/index.htm", new InfoResponse());
            server.createContext("/index.php", new InfoResponse());
            
            //Installer Config URLs
            server.createContext("/adddirectory.html", new AddDirectoryResponse());
            server.createContext("/exit.html", new QuitResponse());
            server.createContext("/about.html", new AboutResponse());
            
            //E2 Simulator URLs
            server.createContext("/web/getallservices", new E2Response("C:/temp/e2logs/getallServices.xml"));
            server.createContext("/web/getlocations", new E2Response("C:/temp/e2logs/getlocations.xml"));
            server.createContext("/web/movielist", new E2Response("C:/temp/e2logs/movielist.xml"));

            server.createContext("/about.html", new AboutResponse());
            server.createContext("/style.css", new StringBasedResponse("style.css"));
            server.createContext("/jquerymin.css", new StringBasedResponse("jquerymin.css"));
            server.createContext("/dropdown.css", new StringBasedResponse("dropdown.css"));
            server.createContext("/logo.png", new ImgResponse("logo.png"));
            
            server.createContext("/jquery.js", new ImgResponse("jquery.js"));
            server.createContext("/jquery-ui.js", new ImgResponse("jquery-ui.js"));
            server.createContext("/dropdown.js", new ImgResponse("dropdown.js"));
            
            server.createContext("/logs.html", new LogResponse());
            
            
            if(Settings.getAdditionalAppsDir() != null)
            {
                List filenames = AlternateDirectory.getFilenames();
                for(int i = 0; i < filenames.size(); i++)
                {
                    HttpContext ctx = server.createContext((new StringBuilder("/Widget/")).append((String)filenames.get(i)).toString(), new OtherWidgetDownload());
                    ctx.getAttributes().put("idx", Integer.valueOf(i));
                }

            }
            
            server.setExecutor(null);
            server.start();
            Browser.openBrowser("http://localhost/index.html");
        }
        catch(BindException e)
        {
            Settings.setLastError(e.toString());
            fatalError("Unable to bind server to port 80, please close down any webservers or other server running on Port 80 and start this installer again");
            System.in.read();
        }
        catch(Exception e)
        {
            fatalError("An unkown error occurred.  Please see the information below and report this at http://www.pembo.co.uk/bugs");
            e.printStackTrace();
            System.in.read();
        }
    }

    private static void printStartupText()
        throws UnknownHostException
    {
    	Logger.logInfo("______________________________________________________________");
    	Logger.logInfo(" ");
    	Logger.logInfo("E2Stream Installer ");
    	Logger.logInfo("______________________________________________________________");
    	Logger.logInfo(" ");
    	Logger.logInfo("Installer version: " + Settings.getInstallerVersion());
    	Logger.logInfo("E2Stream version : " + Settings.getE2StreamVersion());
    	Logger.logInfo("(C)2014 Pembo.co.uk");
    	Logger.logInfo("http://www.pembo.co.uk/e2stream/");
    	Logger.logInfo("______________________________________________________________");
    	Logger.logInfo(" ");
    	Logger.logInfo("Please configure your samsung SMART TV as documented here:");
    	Logger.logInfo("* http://www.pembo.co.uk/e2stream/");
    	Logger.logInfo("Using the following IP Address on your TV:");
    	Logger.logInfo((new StringBuilder("* ")).append(ServerIP.getLocalHostLANAddress().getHostAddress()).toString());
    	Logger.logInfo("______________________________________________________________");
    }
    
    private static void fatalError(String message)
    {
    	Logger.logError(message);
    }
}
