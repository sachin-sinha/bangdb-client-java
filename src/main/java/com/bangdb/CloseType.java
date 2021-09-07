package com.bangdb;

public enum CloseType {
	DEFAULT_AT_CLIENT,
	CONSERVATIVE_AT_SERVER,
	OPTIMISTIC_AT_SERVER,
	CLEANCLOSE_AT_SERVER,
	SIMPLECLOSE_AT_SERVER,
	DEFAULT_AT_SERVER;

	public static CloseType fromInt(int ptype)
	{
		switch(ptype)
		{
			case 0:
				return DEFAULT_AT_CLIENT;
			case 1:
				return CONSERVATIVE_AT_SERVER;
			case 2:
				return OPTIMISTIC_AT_SERVER;
			case 3:
				return CLEANCLOSE_AT_SERVER;
			case 4:
				return SIMPLECLOSE_AT_SERVER;
			case 5:
				return DEFAULT_AT_SERVER;
		}
		return DEFAULT_AT_SERVER;
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
		}
		return 5;
	}
}
