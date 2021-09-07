package com.bangdb;

public class BangDBDataParser
{
	private native long getparser();
	private native int init(String cfg, boolean isFile, long ptdparser);	
	private native int parseFile(String iFilePath, String oFilePath, long ptdparser);
	private native String parseLine(String iLine, long ptdparser);
	private native int parseStream(String schemaName, String streamName, int duration_sec, int lag_sec, String ofilePath, long ptdparser);
	private native void printStats(boolean pretty, long ptdparser);
	private native void clear(long ptdparser);
	public long ptdparser;

	public BangDBDataParser()
	{
		ptdparser = getparser();
	}
	public int init(String cfg, boolean isFile)
	{
		if(cfg == null)
			return -1;
		return init(cfg, isFile, ptdparser);
	}
	public int parseFile(String iFilePath, String oFilePath)
	{
		if(iFilePath == null || oFilePath == null)
			return -1;
		return parseFile(iFilePath, oFilePath, ptdparser);
	}
	public String parseLine(String iLine)
	{
		if(iLine == null)
			return null;
		return parseLine(iLine, ptdparser);
	}
	public int parseStream(String schemaName, String streamName, int duration_sec, int lag_sec, String ofilePath)
	{
		if(schemaName == null || streamName == null || ofilePath == null)
			return -1;
		return parseStream(schemaName, streamName, duration_sec, lag_sec, ofilePath, ptdparser);
	}
	public void printStats(boolean pretty)
	{
		printStats(pretty, ptdparser);
	}
	public void clear()
	{
		clear(ptdparser);
		ptdparser = 0;
	}
}
