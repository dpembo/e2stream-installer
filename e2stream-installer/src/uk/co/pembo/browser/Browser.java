package uk.co.pembo.browser;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Browser
{

    public Browser()
    {
    }

    public static void openBrowser(String url)
    {
        Desktop desktop;
        if(!Desktop.isDesktopSupported())
        	throw new RuntimeException("Installer must be run in a desktop support environment");
        
        desktop = Desktop.getDesktop();
        try
        {
            desktop.browse(new URI(url));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }       
}
