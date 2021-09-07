package com.bangdb;

public class ResultSet {

	private native byte[] GetNextKey(long ptrs);
	private native long GetNextKeyLong(long ptrs);
	private native byte[] GetNextVal(long ptrs);
	private native long GetNextValLong(long ptrs);
	private native boolean HasNext(long ptrs);
	private native void MoveNext(long ptrs);
	private native int Size(long ptrs);
	private native void Begin(long ptrs);
	private native void Clear(long ptrs);
	private native boolean MoreDataToCome(long ptrs);
	private native byte[] LastEvaluatedKey(long ptrs);
	private native long LastEvaluatedKeyLong(long ptrs);
	private native byte[] SearchValue(long ptrs, String key);
	private native byte[] SearchValue(long ptrs, byte[] key);
	private native int data_size(long ptrs);
	private native boolean is_key_str(long ptrs);
	private native boolean is_val_str(long ptrs);

/* */
	private native void addDoc(long rs, String orderBy, long ptrs);

	private native void add(long rs, short byval, long ptrs);

	private native void appendDoc(long rs, String orderBy, long ptrs);

	private native void append(long rs, long ptrs);

	private native void intersectDoc(long rs, String orderBy, long ptrs);

	private native void intersect(long rs, short byval, long ptrs);

	private native String getNextKeyStr(long ptrs);

	private native String getNextValStr(long ptrs);

	private native void beginReverse(long ptrs);
/* */

	public long ptrs;
	
	public ResultSet(long rs)
	{
		ptrs = rs;
	}

	
	public void addDoc(ResultSet rs, String orderBy) 
	{
		if(ptrs == 0 || rs == null || orderBy == null) 
			return;
		addDoc(rs.ptrs, orderBy, ptrs);
	}

	
	public void add(ResultSet rs, boolean byval) 
	{
		if(ptrs == 0 || rs == null) 
			return;
		short _bv = byval ? (short)1 : (short)0;
		add(rs.ptrs, _bv, ptrs);
	}

	
	public void appendDoc(ResultSet rs, String orderBy) 
	{
		if(ptrs == 0 || rs == null) 
			return;
		appendDoc(rs.ptrs, orderBy, ptrs);
	}

	
	public void append(ResultSet rs) 
	{
		if(ptrs == 0 || rs == null) 
			return;
		append(rs.ptrs, ptrs);
	}

	
	public void intersectDoc(ResultSet rs, String orderBy) 
	{
		if(ptrs == 0 || rs == null) 
			return;
		intersectDoc(rs.ptrs, orderBy, ptrs);
	}

	
	public void intersect(ResultSet rs, boolean byval) 
	{
		if(ptrs == 0 || rs == null) 
			return;
		short _bv = byval ? (short)1 : (short)0;
		intersect(rs.ptrs, _bv, ptrs);
	}

	
	public String getNextKeyStr() 
	{
		if(ptrs == 0) 
			return null;
		return getNextKeyStr(ptrs);
	}
	
	
	public String getNextValStr() 
	{
		if(ptrs == 0)
			return null;
		return getNextValStr(ptrs);
	}

	
	public void beginReverse() 
	{
		if(ptrs == 0) 
			return;
		beginReverse(ptrs);
	}

	
	public byte[] getNextKey()
	{
		if(ptrs == 0)
			return null;
		return GetNextKey(ptrs);
	}

	
	public long getNextKeyLong() 
	{
		if(ptrs == 0) 
			return -1;
		return GetNextKeyLong(ptrs);
	}

	
	public byte[] getNextVal() 
	{
		if(ptrs == 0) 
			return null;
		return GetNextVal(ptrs);
	}

	
	public long getNextValLong() 
	{
		if(ptrs == 0) 
			return -1;
		return GetNextValLong(ptrs);
	}

	
	public boolean hasNext() 
	{
		if(ptrs == 0) 
			return false;
		return HasNext(ptrs);
	}

	
	public void moveNext() 
	{
		if(ptrs == 0) 
			return;
		MoveNext(ptrs);
	}

	
	public int count() 
	{
		if(ptrs == 0) 
			return 0;
		return Size(ptrs);
	}

	
	public void begin() 
	{
		if(ptrs == 0) 
			return;
		Begin(ptrs);
	}
	
	public boolean isKeyStr()
	{
		if(ptrs == 0) 
			return false;
		return is_key_str(ptrs);
	}

	public boolean isValStr()
	{
		if(ptrs == 0) 
			return false;
		return is_val_str(ptrs);
	}
	
	public synchronized void clear() 
	{
		if(ptrs == 0)
			return;
		Clear(ptrs);
		ptrs = 0;
	}

	
	public boolean moreDataToCome() {
		if(ptrs == 0) return false;
		return MoreDataToCome(ptrs);
	}

	
	public byte[] lastEvaluatedKey() 
	{
		if(ptrs == 0) 
			return null;
		return	LastEvaluatedKey(ptrs);
	}

	
	public long lastEvaluatedKeyLong() 
	{
		if(ptrs == 0) 
			return -1;
		return LastEvaluatedKeyLong(ptrs);
	}

	
	public byte[] searchValue(String key) 
	{
		if(ptrs == 0) 
			return null;
		return SearchValue(ptrs, key);
	}

	
	public byte[] searchValue(byte[] key) 
	{
		if(ptrs == 0) 
			return null;
		return SearchValue(ptrs, key);
	}

	
	public int size() 
	{
		if(ptrs == 0) 
			return 0;
		return data_size(ptrs);
	}
/*
	
	public void finalize() {
		clear();
	}
*/
}
