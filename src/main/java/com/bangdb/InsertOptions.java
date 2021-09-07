package com.bangdb;

public enum InsertOptions {

	INSERT_UNIQUE,		//if non-existing then insert else return
	UPDATE_EXISTING,	//if existing then update else return
	INSERT_UPDATE,		//insert if non-existing else update
	DELETE_EXISTING,	//delete if existing
	UPDATE_EXISTING_INPLACE, //only for inplace update
	INSERT_UPDATE_INPLACE; //only for inplace update
	
	public static InsertOptions fromInt(int e) {
		switch(e) {
			case 0:
				return INSERT_UNIQUE;
			case 1:
				return UPDATE_EXISTING;
			case 2:
				return INSERT_UPDATE;
			case 3:
				return DELETE_EXISTING;
			case 4:
				return UPDATE_EXISTING_INPLACE;
			case 5:
				return INSERT_UPDATE_INPLACE;
		}
		return INSERT_UNIQUE;
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
