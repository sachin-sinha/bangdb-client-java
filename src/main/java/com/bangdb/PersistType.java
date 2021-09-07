package com.bangdb;
public enum PersistType {
    INMEM_ONLY,               //one proc, only RAM based, cache enabled (no overflow to disk, ideally overflow to other RAM)
    INMEM_PERSIST,            //one proc, disked backed, cache enabled (over flow to disk)
    PERSIST_ONLY,             //many procs one db file, cache disabled
    INVALID_PERSIST_TYPE;

	public static PersistType fromInt(int ptype)
	{
		switch(ptype)
		{
			case 0:
				return INMEM_ONLY;
			case 1:
				return INMEM_PERSIST;
			case 2:
				return PERSIST_ONLY;
			case 3:
				return INVALID_PERSIST_TYPE;
		}
		return INVALID_PERSIST_TYPE;
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
				return -1;
		}
		return -1;
	}
}

