package com.bangdb;

public enum BangDBDataAggrType {
	BANGDB_DATA_AGGR_TYPE_COUNT,	// = 0,
	BANGDB_DATA_AGGR_TYPE_SUM,
	BANGDB_DATA_AGGR_TYPE_AVG;

	public static BangDBDataAggrType fromInt(int e) {
		switch(e) {
			case 0:
				return BANGDB_DATA_AGGR_TYPE_COUNT;
			case 1:
				return BANGDB_DATA_AGGR_TYPE_SUM;
			case 2:
				return BANGDB_DATA_AGGR_TYPE_AVG;
		}
		return BANGDB_DATA_AGGR_TYPE_SUM;
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
		}
		return -1;
	}
}
