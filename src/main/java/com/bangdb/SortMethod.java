package com.bangdb;

public enum SortMethod {
	LEXICOGRAPH,		//value = 1
	QUASI_LEXICOGRAPH,	//value = 2
	SORT_METHOD_INVALID;	//value = -1

	public static SortMethod fromInt(int e) {
		switch(e) {
			case 1:
				return LEXICOGRAPH;
			case 2:
				return QUASI_LEXICOGRAPH;
			case -1:
				return SORT_METHOD_INVALID;
		}
		return SORT_METHOD_INVALID;
	}

	public static int toInt(int ordinal)
	{
		switch(ordinal)
		{
			case 0:
				return 1;
			case 1:
				return 2;
			case 2:
				return -1;
		}
		return -1;
	}
}
