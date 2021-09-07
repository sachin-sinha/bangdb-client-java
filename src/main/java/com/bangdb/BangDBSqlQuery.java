package com.bangdb;

public class BangDBSqlQuery
{
	private native long get_bsql(long ptenv);
	private native long parse_sql(long ptsql, String query);
	private native ResultSet run_sql(long ptsql, long qid, long ptrs);
	private native void clear_query(long ptsql, long qid);
	private native String get_query_metatadata(long ptsql, long qid);
	private native void close_bangdb_sql_query(long ptsql);
	public long ptsql;
	
	public BangDBSqlQuery(BangDBEnv env)
	{
		if(env == null)
		{
			System.out.println("db env is null");
			return;
		}
		ptsql = get_bsql(env.ptdbenv);
	}

	public long parse(String query)
	{
		if(query == null)
			return -1;
		return parse_sql(ptsql, query);
	}

	public ResultSet run(long qid, ResultSet prev_rs)
	{
		return run_sql(ptsql, qid, prev_rs != null ? prev_rs.ptrs : 0);
	}

	public void clear(long qid)
	{
		clear_query(ptsql, qid);
	}

	public String getQueryMetadata(long qid)
	{
		return get_query_metatadata(ptsql, qid);
	}

	public void close()
	{
		close_bangdb_sql_query(ptsql);
		ptsql = 0;
	}
}
