package com.bangdb;

public enum ScanLimitBy {
	LIMIT_RESULT_SIZE,
	LIMIT_RESULT_ROW,
	LIMIT_INVALID;

	public static ScanLimitBy fromInt(int e) {
		switch(e) {
			case 0:
				return LIMIT_RESULT_SIZE;
			case 1:
				return LIMIT_RESULT_ROW;
		}
		return LIMIT_INVALID;
	}

	public static int toInt(int ordinal)
	{
		switch(ordinal)
		{
			case 0:
				return 0;
			case 1:
				return 1;
		}
		return -1;
	}	
}
