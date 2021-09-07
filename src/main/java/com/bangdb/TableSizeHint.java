package com.bangdb;

public enum TableSizeHint {

	TINY_TABLE_SIZE,
	SMALL_TABLE_SIZE,
	NORMAL_TABLE_SIZE,
	BIG_TABLE_SIZE;

	public static TableSizeHint fromInt(int e) {
		switch(e) {
			case 0:
				return TINY_TABLE_SIZE;
			case 1:
				return SMALL_TABLE_SIZE;
			case 2:
				return NORMAL_TABLE_SIZE;
			case 3:
				return BIG_TABLE_SIZE;
		}
		return NORMAL_TABLE_SIZE;
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
				return -1;
		}
		return -1;
	}
}
