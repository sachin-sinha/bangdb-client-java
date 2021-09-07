package com.bangdb;

public class ScanFilter {
	public native long scanfilter_init();
	public native void scanf_reset(long pscanf);
	public native void set_filter(int sop, int eop, int lBy, int limit, int onlyKey, int reserved, long pscanf);
	public native void scanf_close(long pscanf);
	public ScanOperator skeyOp;
	public ScanOperator ekeyOp;
	public ScanLimitBy limitBy;
	public int limit;
	public int skipCount;
	public String streamFilter;
	public int onlyKey;
	public int reserved;
	public long pscanf;

	public ScanFilter()
	{
		pscanf = scanfilter_init();
		skeyOp = ScanOperator.GTE;
		ekeyOp = ScanOperator.LTE;
		limitBy = ScanLimitBy.LIMIT_RESULT_SIZE;
		limit = 2*1024*1024;
		skipCount = 0;
		onlyKey = 0;
		reserved = 0;
	}

	public void reset()
	{
		skeyOp = ScanOperator.GTE;
		ekeyOp = ScanOperator.LTE;
		limitBy = ScanLimitBy.LIMIT_RESULT_SIZE;
		limit = 2*1024*1024;
		skipCount = 0;
		onlyKey = 0;
		reserved = 0;
		scanf_reset(pscanf);
	}

	public void setFilter()
	{
		set_filter((int)skeyOp.ordinal(), (int)ekeyOp.ordinal(), (int)limitBy.ordinal(), limit, onlyKey, reserved, pscanf);
	}

	public synchronized void close() 
	{
		if(pscanf != 0) 
		{
			scanf_close(pscanf);
			pscanf = 0;
		}
	}
/*
	public void finalize() {
		close();
	}
*/
}
