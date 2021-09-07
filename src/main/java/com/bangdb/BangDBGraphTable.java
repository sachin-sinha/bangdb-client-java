package com.bangdb;

public class BangDBGraphTable
{
	//private native long getTable(String tblName, long ptgtbl);
	private native long addNode(String node, long ptgtbl);
	private native long addTriple(String triple, long ptgtbl);
	private native String runQuery(String query, long ptgtbl);
	public long ptgtbl;

	public BangDBGraphTable(long _ptgtbl)
	{
		ptgtbl = _ptgtbl;
	}
	public long addNode(String node)
	{
		if(node == null)
			return -1;
		return addNode(node, ptgtbl);
	}
	public long addTriple(String triple)
	{
		if(triple == null)
			return -1;
		return addTriple(triple, ptgtbl);
	}
	public String runQuery(String query)
	{
		if(query == null)
			return null;
		return runQuery(query, ptgtbl);
	}
}
