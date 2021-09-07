package com.bangdb;

public enum KeyType {
	NORMAL_KEY,		//value = 1, opaque (void*) as key
	COMPOSITE_KEY,		//value = 3, always treated as opaque even if it is of long:void* or long:long
	NORMAL_KEY_LONG,	//value = 10, long as key
	KEY_TYPE_INVALID;	//value = -1

	public static KeyType fromInt(int e) {
		switch(e) {
			case 1:
				return NORMAL_KEY;
			case 3:
				return COMPOSITE_KEY;
			case 10:
				return NORMAL_KEY_LONG;
		}
		return KEY_TYPE_INVALID;
	}

	public static int toInt(int ordinal)
	{
		switch(ordinal)
		{
			case 0:
				return 1;
			case 1:
				return 3;
			case 2:
				return 10;
			case 3:
				return -1;
		}
		return -1;
	}
}
