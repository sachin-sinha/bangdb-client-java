package com.bangdb;

public class BangDBCommon {

	private static native void bangdbLogger(String str);
	private static native void bangdb_lasterror();
	private static native void bangdb_printerror(int err);
	private static native long getTimeStamp_MicroSec();
	private static native long getTimeStamp_MilliSec();
	private static native long get_hash_string_64(String s, int len);
	private static native String geohash_endcode(double lat, double lon, int precision);
	private static native int summarize_data(String cfg, boolean is_cfg_file, String ifile, String ofile, boolean doprint);

	public static void bangdb_logger(String str) 
	{
		if(str != null)
			bangdbLogger(str);
	}
	public static void bangdb_last_error() 
	{
		bangdb_lasterror();
	}
	public static void bangdb_print_error(int err) 
	{
		bangdb_printerror(err);
	}
	public static long getTimeStampMicroSec() 
	{
		return getTimeStamp_MicroSec();
	}
	public static long getTimeStampMilliSec() 
	{
		return getTimeStamp_MilliSec();
	}
	public static long getHashStr64(String s)
	{
		return s != null ? get_hash_string_64(s, s.length()) : -1;
	}
	public static String geohashEncode(double lat, double lon, int precision)
	{
		return geohash_endcode(lat, lon, precision);
	}
	public static int summarizeData(String cfg, boolean is_cfg_file, String ifile, String ofile, boolean doprint)
	{
		return summarize_data(cfg, is_cfg_file, ifile, ofile, doprint);
	}
}
