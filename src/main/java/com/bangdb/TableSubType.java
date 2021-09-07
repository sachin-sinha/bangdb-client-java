package com.bangdb;

public enum TableSubType {
	BANGDB_SW_TABLE,
	BANGDB_TOPK_TABLE,
	NON_ANALYTICAL_TABLE,
	INDEXBTREE_TABLE,
	DUPLINDEX_TABLE,
	BANGDB_SW_INVALID;

	public static TableSubType fromInt(int e) {
		switch(e) {
			case 0:
				return BANGDB_SW_TABLE;
			case 1:
				return BANGDB_TOPK_TABLE;
			case 2:
				return NON_ANALYTICAL_TABLE;
			case 3:
				return INDEXBTREE_TABLE;
			case 4:
				return DUPLINDEX_TABLE;
			case 5:
				return BANGDB_SW_INVALID;
		}
		return BANGDB_SW_INVALID;
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
				return -1;
		}
		return -1;
	}
}
