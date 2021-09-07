package com.bangdb;

public enum BangDBTSDataGran {
	BANGDB_TS_DATA_GRAN_NONE,	// = 0,
	BANGDB_TS_DATA_GRAN_DAY,
	BANGDB_TS_DATA_GRAN_WEEK,
	BANGDB_TS_DATA_GRAN_MONTH,
	BANGDB_TS_DATA_GRAN_YEAR;

	public static BangDBTSDataGran fromInt(int e) {
		switch(e) {
			case 0:
				return BANGDB_TS_DATA_GRAN_NONE;
			case 1:
				return BANGDB_TS_DATA_GRAN_DAY;
			case 2:
				return BANGDB_TS_DATA_GRAN_WEEK;
			case 3:
				return BANGDB_TS_DATA_GRAN_MONTH;
			case 4:
				return BANGDB_TS_DATA_GRAN_YEAR;
		}
		return BANGDB_TS_DATA_GRAN_DAY;
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
				return 2;
			case 3:
				return 3;
			case 4:
				return 4;
		}
		return -1;
	}
}
