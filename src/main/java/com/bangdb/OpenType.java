package com.bangdb;

public enum OpenType {
	OPENCREATE,
	TRUNCOPEN,
	JUSTOPEN;

	public static OpenType fromInt(int e) {
		switch(e) {
			case 0:
				return OPENCREATE;
			case 1:
				return TRUNCOPEN;
			case 2:
				return JUSTOPEN;
		}
		return OPENCREATE;
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
		return 0;
	}
}
