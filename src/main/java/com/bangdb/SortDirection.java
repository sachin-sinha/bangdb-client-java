package com.bangdb;

public enum SortDirection {
	SORT_ASCENDING,			//value = 3
	SORT_DESCENDING,		//value = 4
	SORT_DIRECTION_INVALID;		//value = -1

	public static SortDirection fromInt(int e) {
		switch(e) {
			case 3:
				return SORT_ASCENDING;
			case 4:
				return SORT_DESCENDING;
			case -1:
				return SORT_DIRECTION_INVALID;
		}
		return SORT_DIRECTION_INVALID;
	}

	public static int toInt(int ordinal)
	{
		switch(ordinal)
		{
			case 0:
				return 3;
			case 1:
				return 4;
			case 2:
				return -1;
		}
		return -1;
	}
}
