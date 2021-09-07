package com.bangdb;

public enum BangDBJsonDataType {
		NULLTYPE, // = 0,
		FALSETYPE, // = 1,
		TRUETYPE, // = 2,
		OBJECTTYPE, // = 3,
		ARRAYTYPE, // = 4,
		STRINGTYPE, // = 5,
		NUMBERTYPE, // = 6,
		INTTYPE, // = 7,
		UINTTYPE, // = 8,
		LONGTYPE, // = 9,
		ULONGTYPE, // = 10,
		DOUBLETYPE, // = 11,
		BOOLTYPE, // = 12,
		INVALIDTYPE; // = 13;
	
	public static BangDBJsonDataType fromInt(int e) {
		switch(e) {
			case 0:
				return NULLTYPE;
			case 1:
				return FALSETYPE;
			case 2:
				return TRUETYPE;
			case 3:
				return OBJECTTYPE;
			case 4:
				return ARRAYTYPE;
			case 5:
				return STRINGTYPE;
			case 6:
				return NUMBERTYPE;
			case 7:
				return INTTYPE;
			case 8:
				return UINTTYPE;
			case 9:
				return LONGTYPE;
			case 10:
				return ULONGTYPE;
			case 11:
				return DOUBLETYPE;
			case 12:
				return BOOLTYPE;
			case 13:
				return INVALIDTYPE;
		}
		return INVALIDTYPE;
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
				return 7;
			case 8:
				return 8;
			case 9:
				return 9;
			case 10:
				return 10;
			case 11:
				return 11;
			case 12:
				return 12;
			case 13:
				return 13;
		}
		return -1;
	}
}
