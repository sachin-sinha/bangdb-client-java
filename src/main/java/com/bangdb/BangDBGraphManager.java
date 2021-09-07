package com.bangdb;

public class BangDBGraphManager
{
	private native long get_gmgr(long ptdb);
	private native BangDBGraphTable getGraphTable(String tableName, boolean createTable, long ptgmgr) ;
	private native String listGraphs(long ptgmgr);
	private native void closeGraphTable(long gtbl, long ptgmgr);
	private native int closeGraphManager(boolean force, long ptgmgr);
	long ptgmgr;
	
	public BangDBGraphManager(BangDBDatabase bdb)
	{
		ptgmgr = get_gmgr(bdb.ptdb);
	}
	public BangDBGraphTable getGraphTable(String tableName, boolean createTable)
	{
		if(tableName == null)
			return null;
		return getGraphTable(tableName, createTable, ptgmgr);
	}
	public String listGraphs()
	{
		return listGraphs(ptgmgr);
	}
	public void closeGraphTable(BangDBGraphTable gtbl)
	{
		if(gtbl == null)
			return;
		closeGraphTable(gtbl.ptgtbl, ptgmgr);
	}
	public int closeGraphManager(boolean force)
	{
		return closeGraphManager(force, ptgmgr);
	}
}
