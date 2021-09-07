package com.bangdb;
public enum IndexType {
   HASH,	//depricated
   EXTHASH,
   BTREE,
   HEAP,	//not supported
   IndexType_INVALID;

	public static IndexType fromInt(int e) {
		switch(e) {
			case 0:
				return HASH;
			case 1:
				return EXTHASH;
			case 2:
				return BTREE;
			case 3:
				return HEAP;
			case 4:
				return IndexType_INVALID;
		}
		return IndexType_INVALID;
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
				return -1;
		}
		return -1;
	}
}
