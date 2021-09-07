package com.bangdb;

public enum ScanOperator {
	GT,
	GTE,
	LT,
	LTE,
	EQ,
	NE;

	public static ScanOperator fromInt(int e) {
		switch(e) {
			case 0:
				return GT;
			case 1:
				return GTE;
			case 2:
				return LT;
			case 3:
				return LTE;
			case 4:
				return EQ;
			case 5:
				return NE;
		}
		return GT;
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
		return 0;
	} 
}
