package com.bangdb;

public enum BangDBTSDataType {
	BANGDB_TS_DATA_TYPE_STRING, 	//= 5,
	BANGDB_TS_DATA_TYPE_LONG, 	//= 9,
	BANGDB_TS_DATA_TYPE_DOUBLE;	// = 11

	public static BangDBTSDataType fromInt(int e) {
		switch(e) {
			case 5:
				return BANGDB_TS_DATA_TYPE_STRING;
			case 9:
				return BANGDB_TS_DATA_TYPE_LONG;
			case 11:
				return BANGDB_TS_DATA_TYPE_DOUBLE;
		}
		return BANGDB_TS_DATA_TYPE_LONG;
	}

	public static int toInt(int ordinal)
	{
		switch(ordinal)
		{
			case 0:
				return 5;
			case 1:
				return 9;
			case 2:
				return 11;
		}
		return -1;
	}
}
