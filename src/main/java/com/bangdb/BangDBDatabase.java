package com.bangdb;

public class BangDBDatabase {
	
	private native long getInstance(String dbName, long ptdbenv, String dbPath, String logPath, String configPath, String archivePath, String host, String port, String user, String pwd, int tranType);
	private native BangDBTable get_table(long ptdb, String tableName, int[] tenvarr, int openType);
	private native void begin_transaction(long ptdb, long ptxn);
	private native long commit_transaction(long ptdb, long ptxn);
	private native void abort_transaction(long ptdb, long ptxn);
	private native int dump_data(long ptdb);
	private native int drop_table(long ptdb, long ptbl);
	private native int does_table_exist(long ptdb, String tblName, long tblId);
	//private native int verify_table(long ptdb, String tblName, long ptenv);
	private native int get_num_tables(long ptdb, int flag);
	private native String get_table_list(long ptdb);
	private native String get_stats(long ptdb, int verbose);
	private native String get_table_stats(long ptdb, String tblName, int verbose);
	private native String describe_database(long ptdb);
	private native DBParam get_param(long ptdb);
	private native String get_name(long ptdb);
	private native int get_table_type(long ptdb, String tblName, long tblId);
	private native TableEnv get_table_env(long ptdb, String tblName, long tblId);
	private native long get_table_id(long ptdb, String tblName);
	//private native BangDBTable get_table_ref(long ptdb, String tblName, long tblId);
	private native String add_udf(long ptdb, String udf_json);
	private native String del_udf(long ptdb, String udf_name);
	private native String exec_udf(long ptdb, String req_json);
	private native void closedatabase(long ptdb, int flag);
	
	public long ptdb = 0;
	/*
	private BangDBDatabase(String dbName, DBParam dbparam)
	{
		ptdb = getInstance(dbName, dbparam.getDBPath(), dbparam.getLogPath(), dbparam.getConfigPath(), dbparam.get_archivePath(), dbparam.get_host(), dbparam.get_port(), dbparam.get_userid(), dbparam.get_pwd(), dbparam.getTransactionType().ordinal());
	}

	private BangDBDatabase()
	{
		ptdb = 0;
	}
	*/

	public BangDBDatabase(String dbName, BangDBEnv env, DBParam dbparam)
	{
		if(env == null || dbparam == null || dbName == null)
		{
			System.out.println("BangDBEnv or DBParam or dbName is null");
		}
		else
		{
			ptdb = getInstance(dbName, env.ptdbenv, dbparam.getDBPath(), dbparam.getLogPath(), dbparam.getDBConfigPath(), dbparam.get_archivePath(), dbparam.get_host(), dbparam.get_port(), dbparam.get_userid(), dbparam.get_pwd(), dbparam.getTransactionType().ordinal());
		}
	}

	public BangDBTable getTable(String tableName, TableEnv tenv, OpenType openType)
	{
		if(tableName == null || tenv == null || openType == null)
			return null;
		return get_table(ptdb, tableName, tenv.get(), openType.ordinal());
	}
	
	public void beginTransaction(Transaction txn)
	{
		if(txn == null)
		{
			System.out.println("txn is null");
			return;
		}
		begin_transaction(ptdb, txn.pttxn);
	}
	public long commitTransaction(Transaction txn)
	{
		if(txn == null)
		{
			System.out.println("txn is null");
			return -1;
		}
		return commit_transaction(ptdb, txn.pttxn);
	}
	public void abortTransaction(Transaction txn)
	{
		if(txn == null)
		{
			System.out.println("txn is null");
			return;
		}
		abort_transaction(ptdb, txn.pttxn);
	}
	
	public int dumpData()
	{
		return dump_data(ptdb);
	}
	public int dropTable(BangDBTable tbl)
	{
		if(tbl == null)
			return -1;
		return drop_table(ptdb, tbl.pttbl);
	}

	public boolean doesTableExist(String tblName, long tblId)
	{
		if(tblName == null)
			return false;
		return does_table_exist(ptdb, tblName, tblId) > 0 ? true : false;
	}
	/*
	public boolean verifyTable(String tblName, TableEnv tenv)
	{
		return verify_table(ptdb, tblName, tenv.pttenv) > 0 ? true : false;
	}
	*/
	// flag = 1 for open, 2 for closed, 3 for all
	public int getNumTables(int flag) 
	{
		return get_num_tables(ptdb, flag);
	}
	public String getTableList()
	{
		return get_table_list(ptdb);
	}
	public String getStats(boolean verbose)
	{
		return get_stats(ptdb, verbose ? 1 : 0);
	}
	public String getTableStats(String tblName, boolean verbose)
	{
		if(tblName == null)
			return null;
		return get_table_stats(ptdb, tblName, verbose ? 1 : 0);
	}

	public String describeDatabase()
	{
		return describe_database(ptdb);
	}

	public DBParam getParam()
	{
		return get_param(ptdb);
	}
	public String getName()
	{
		return get_name(ptdb);
	}

	public TableType getTableType(String tblName, long tblId)
	{
		if(tblName == null)
			return null;
		return TableType.fromInt(get_table_type(ptdb, tblName, tblId));
	}
	public TableEnv getTableEnv(String tblName, long tblId)
	{
		if(tblName == null)
			return null;
		TableEnv tev = get_table_env(ptdb, tblName, tblId);
		return tev;
	}

	public long getTableId(String tblName)
	{
		if(tblName == null)
			return -1;
		return get_table_id(ptdb, tblName);
	}
	/*
	public BangDBTable getTableRef(String tblName, long tblId)
	{
		return get_table_ref(ptdb, tblName, tblId);
	}
	*/

	// {"name":"mylib", "base_class":"baseclass", "create_func":"maker","type":1, "src":"src_loc", "srctype":1, "bucket_info":{}}
	public String add_udf(String udf_json)
	{
		if(udf_json == null)
			return null;
		return add_udf(ptdb, udf_json);
	}
	public String del_udf(String udf_name)
	{
		if(udf_name == null)
			return null;
		return del_udf(ptdb, udf_name);
	}
	// {"name":, "id", "argv":["a", 1, 0.3], "exp-type"}
	// returns {"ret-val":"abc"}
	public String exec_udf(String req_json)
	{
		if(req_json == null)
			return null;
		return exec_udf(ptdb, req_json);
	}
/*	
    private void closeDatabase(CloseType dbclose) 
    {
		if(dbclose == null)
			dbclose = CloseType.DEFAULT_AT_CLIENT;
		closedatabase(ptdb, dbclose.ordinal());		
    }
*/
}
