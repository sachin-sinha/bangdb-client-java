package com.bangdb;
public enum VersionType {
	BANGDB_DATA_VERSION_INVALID, 
	BANGDB_DATA_VERSION_VALID;

	public static VersionType fromInt(int e) {
		switch(e) {
			case 0:
				return BANGDB_DATA_VERSION_INVALID;
			case 1:
				return BANGDB_DATA_VERSION_VALID;
		}
		return BANGDB_DATA_VERSION_INVALID;
	}

	public static int toInt(int ordinal)
	{
		switch(ordinal)
		{
			case 0:
				return 0;
			case 1:
				return 1;
		}
		return 0;
	}
}
