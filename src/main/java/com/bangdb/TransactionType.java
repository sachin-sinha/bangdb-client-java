package com.bangdb;

public enum TransactionType {

	DB_MULTIOPS_TRANSACTION_NONE,
	DB_OPTIMISTIC_TRANSACTION,
	DB_PESSIMISTIC_TRANSACTION;

	public static TransactionType fromInt(int e) {
		switch(e) {
			case 0:
				return DB_MULTIOPS_TRANSACTION_NONE;
			case 1:
				return DB_OPTIMISTIC_TRANSACTION;
			case 2:
				return DB_PESSIMISTIC_TRANSACTION;
		}
		return DB_MULTIOPS_TRANSACTION_NONE;
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
