package uk.co.pembo.e2stream.server;


public class Settings
{

	private static String instVers = "X.X";
	private static String appVers = instVers;

	public Settings()
    {
    }

    public static String getInstallerVersion()
    {
    	return "v" + instVers;
    }
    
    public static String getE2StreamVersion()
    {
    	return "v" + appVers;
    }
    public static String getAdditionalAppsDir()
    {
        return additionalAppsDir;
    }

    public static void setAdditionalAppsDir(String inadditionalAppsDir)
    {
        additionalAppsDir = inadditionalAppsDir;
    }

    public static String getLastError()
    {
        return lastError;
    }

    public static void setLastError(String inlastError)
    {
        lastError = inlastError;
    }

    public static String getIpAddress()
    {
        return ipAddress;
    }

    public static void setIpAddress(String inipAddress)
    {
        ipAddress = inipAddress;
    }

    public static String getFilename()
    {
        return filename;
    }

    public static void setFilename(String infilename)
    {
        filename = infilename;
    }

    public static String getDescrption()
    {
        return descrption;
    }

    public static void setDescrption(String indescrption)
    {
        descrption = indescrption;
    }

    public static String getId()
    {
        return id;
    }

    public static void setId(String inid)
    {
        id = inid;
    }

    public static String getTitle()
    {
        return title;
    }

    public static void setTitle(String intitle)
    {
        title = intitle;
    }

    private static String ipAddress = null;
    private static String filename = "E2Stream.zip";
    private static String descrption = "The Enigma2 Samsung SMART TV Streamer";
    private static String id = "e2Stream";
    private static String title = "E2Stream";
    private static String lastError = "";
    private static String additionalAppsDir = null;

}
