package com.bangdb;

public enum TableType {
	/* index and data files with opaque (void*) key */
	NORMAL_TABLE,
	WIDE_TABLE,
	/* no data file for following tables */
	INDEX_TABLE,			//opaque(void*) as key and dataoff, datlen is of actual value in the data file store in main table, no data file
	PRIMITIVE_TABLE_INT,		//int as key and int as val, stored in index file only, no data file
	PRIMITIVE_TABLE_LONG,		//long as key and long as val, stored in index file only, no data file
	PRIMITIVE_TABLE_STRING,		//opaque(void*) as key and data stored in the index only, no data file
	LARGE_TABLE,
	BANGDB_TABLE_INVALID;

	public static TableType fromInt(int e) {
		switch(e) {
			case 0:
				return NORMAL_TABLE;
			case 1:
				return WIDE_TABLE;
			case 2:
				return INDEX_TABLE;
			case 3:
				return PRIMITIVE_TABLE_INT;
			case 4:
				return PRIMITIVE_TABLE_LONG;
			case 5:
				return PRIMITIVE_TABLE_STRING;
			case 6:
				return LARGE_TABLE;
			case 7:
				return BANGDB_TABLE_INVALID;
		}
		return BANGDB_TABLE_INVALID;
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
			case 5:
				return 5;
			case 6:
				return 6;
			case 7:
				return -1;
		}
		return -1;
	}

}
