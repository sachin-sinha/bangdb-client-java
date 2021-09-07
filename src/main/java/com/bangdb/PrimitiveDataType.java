package com.bangdb;

public enum PrimitiveDataType {
	PRIMITIVE_INT,
	PRIMITIVE_LONG,
	PRIMITIVE_STRING,
	PRIMITIVE_INVALID;

	public static PrimitiveDataType fromInt(int e) {
		switch(e) {
			case 0:
				return PRIMITIVE_INT;
			case 1:
				return PRIMITIVE_LONG;
			case 2:
				return PRIMITIVE_STRING;
			case 3:
				return PRIMITIVE_INVALID;
		}
		return PRIMITIVE_INVALID;
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
		}
		return 3;
	}
}
