package uk.co.pembo.utils;

public class Logger 

{

	private static int noLines = 10;
	private static String lines[] = new String[noLines]; 

	private static int onLine = 0;

	public static String getLines()
	{
		String returnText = "";
		for(int i=0;i<lines.length;i++)
		{
			returnText = returnText + lines[i] + "\n";
		}
		return returnText;
	}
	
	private static void addToLines(String message)
	{
		if(onLine==10)
		{
			//shuffle down array
			//and put in 9
			for(int i=0;i<lines.length-1;i++)
			{
				lines[i] = lines[i+1];
			}
			lines[9] = message;
		}
		else
		{
			lines[onLine]=message;
			onLine++;
		}
				
	}
	public static void logInfo(String message)
	{
		System.out.println(message);
		addToLines(message);
	}
	
	public static void logError(String message)
	{
		System.err.println("[ERROR] " + message);
		addToLines("[ERROR] " + message);
	}

	public static void main(String[] args)
	{
		logInfo("1");
		logInfo("2");
		logInfo("3");
		logInfo("4");
		logInfo("5");
		logInfo("6");
		logInfo("7");
		logInfo("8");
		logInfo("9");
		logInfo("10");
		System.out.println("--------------");
		System.out.println(getLines());
	
		logInfo("11");
		logInfo("12");
		System.out.println("--------------");
			
		System.out.println(getLines());
	}
}
