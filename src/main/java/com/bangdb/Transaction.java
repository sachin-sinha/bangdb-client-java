package com.bangdb;

public class Transaction
{
	private native int is_active(long pttxn);
	private native void close(long pttxn);
	public long pttxn;

	public Transaction() 
	{
		pttxn = 0;
	}
	
	public boolean isActive() 
	{
		if(pttxn == 0) 
			return false;
		int x = is_active(pttxn); 
		return x == 0 ? false : true;
	}

	public synchronized void close() 
	{
		if(pttxn != 0) {
			close(pttxn);
			pttxn = 0;
		}
	}
	/*
	public void finalize() {
		close();
	}
	*/
}
