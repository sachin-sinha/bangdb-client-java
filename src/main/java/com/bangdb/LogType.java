package com.bangdb;

public enum LogType {

	SHARED_LOG, 
	PRIVATE_LOG, 
	LogType_INVALID;

	public static LogType fromInt(int e) {
		switch(e) {
			case 0:
				return SHARED_LOG;
			case 1:
				return PRIVATE_LOG;
			case 2:
				return LogType_INVALID;
		}
		return LogType_INVALID;
	}

	public static int toInt(int ordinal)
	{
		switch(ordinal)
		{
			case 0:
				return 0;
			case 1:
				return 1;
			case 2:
				return -1;
		}
		return -1;
	}
}
