package com.bangdb;

public class BangDBTable {
	private native int close_table(long pttbl, int close_type, int force);
	private native int add_index(long pttbl, String idxName, int[] tenv);
	private native int add_index_str(long pttbl, String idxName, int idx_size, int allowDuplicates);
	private native int add_index_num(long pttbl, String idxName, int allowDuplicates);
	private native int drop_index(long pttbl, String idxName);
	private native int has_index(long pttbl, String idxName);
	private native int dump_data(long pttbl);
	private native String get_name(long pttbl);
	private native String get_table_dir(long pttbl);
	private native int get_index_type(long pttbl);
	private native String get_stats(long pttbl, int force);
	private native int put_file(long pttbl, String key, String file_path, int iop);
	private native long get_file(long pttbl, String key, String fname, String fpath);
	private native long put_large_data(long pttbl, String key, byte[] val, int iop);
	private native byte[] get_large_data(long pttbl, String key);
	private native String list_large_data_keys(long pttbl, String skey, int list_size_mb);
	private native int count_slice_large_data(long pttbl, String key);
	private native long count_large_data(long pttbl);
	private native int del_large_data(long pttbl, String key);
	private native long put(long pttbl, String key, byte[] val, int iop, long ptxn);
	private native long put(long pttbl, long key, byte[] val, int iop, long ptxn);
	private native long put(long pttbl, String key, String val, int iop, long ptxn);
	private native long put(long pttbl, long key, String val, int iop, long ptxn);
	private native ResultSet scan_data(long pttbl, long prev_rs, String pk_skey, String pk_ekey, long psf, long ptxn);
	private native ResultSet scan_data(long pttbl, long prev_rs, long pk_skey, long pk_ekey, long psf, long ptxn);
	private native ResultSet scan_data(long pttbl, long prev_rs, byte[] pk_skey, byte[] pk_ekey, long psf, long ptxn);
	private native long put_text(long pttbl, String str, String k, int iop);
	private native long put_text(long pttbl, String str, long k, int iop);
	private native ResultSet scan_text(long pttbl, String[] wlist, int nfilters, int intersect);
	private native long put_doc(long pttbl, String doc, String pk, String rev_idx_fields_json, int iop);
	private native long put_doc(long pttbl, String doc, byte[] pk, String rev_idx_fields_json, int iop);
	private native long put_doc(long pttbl, String doc, long pk, String rev_idx_fields_json, int iop);
	private native ResultSet scan_doc(long pttbl, long prev_rs, String pk_skey, String pk_ekey, String idx_filter_json, long psf);
	private native ResultSet scan_doc(long pttbl, long prev_rs, long pk_skey, long pk_ekey, String idx_filter_json, long psf);
	private native ResultSet scan_doc(long pttbl, long prev_rs, byte[] pk_skey, byte[] pk_ekey, String idx_filter_json, long psf);
	private native byte[] get(long pttbl, String key, long ptxn);
	private native byte[] get(long pttbl, long key, long ptxn);
	private native long del(long pttbl, String key, long ptxn);
	private native long del(long pttbl, long key, long ptxn);
	private native long count(long pttbl, String pk_skey, String pk_ekey, String idx_filter_json, long psf);
	private native long count(long pttbl, long pk_skey, long pk_ekey, String idx_filter_json, long psf);
	private native long exp_count(long pttbl, String skey, String ekey);
	private native long exp_count(long pttbl, long skey, long ekey);
	private native long count(long pttbl);
	private native void set_auto_commit(long pttbl, int flag);
	private native int get_table_type(long pttbl);

	
	public long pttbl;
	public long pttenv;
	
	public BangDBTable()
	{
		
	}
	
	public int closeTable(CloseType closeType, boolean force)
	{
		if(closeType == null)
			closeType = CloseType.DEFAULT_AT_CLIENT;
		int rv = close_table(pttbl, closeType.ordinal(), force?1:0);
		pttbl = 0;
		return rv;
	}

	public int addIndex(String idxName, TableEnv tenv)
	{
		if(idxName == null || tenv == null)
			return -1;
		return add_index(pttbl, idxName, tenv.get());
	}
	
	public int addIndex_str(String idxName, int idx_size, boolean allowDuplicates)
	{
		if(idxName == null)
			return -1;
		return add_index_str(pttbl, idxName, idx_size, allowDuplicates ? 1 : 0);
	}

	public int addIndex_num(String idxName, boolean allowDuplicates)
	{
		if(idxName == null)
			return -1;
		return add_index_num(pttbl, idxName, allowDuplicates ? 1 : 0);
	}

	public int dropIndex(String idxName)
	{
		if(idxName == null)
			return -1;
		return drop_index(pttbl, idxName);
	}

	public boolean hasIndex(String idxName)
	{
		if(idxName == null)
			return false;
		return has_index(pttbl, idxName) > 0 ? true : false;
	}

	public int dumpData()
	{
		return dump_data(pttbl);
	}

	public String getName()
	{
		return get_name(pttbl);
	}

	public String getTableDir()
	{
		return get_table_dir(pttbl);
	}

	public IndexType getIndexType()
	{
		return IndexType.fromInt(get_index_type(pttbl));
	}

	public String getStats(boolean verbose)
	{
		return get_stats(pttbl, verbose ? 1 : 0);
	}

	/* connection or CRUD API */
	// for files - supported only for LARGE_TABLE
	public long putFile(String key, String file_path, InsertOptions iop)
	{
		if(key == null || file_path == null)
			return -1;
		if(iop == null)
			iop = InsertOptions.INSERT_UNIQUE;
		return put_file(pttbl, key, file_path, iop.ordinal());
	}

	public long getFile(String key, String fname, String fpath)
	{
		if(key == null || fname == null || fpath == null)
			return -1;
		return get_file(pttbl, key, fname, fpath);
	}

	public long putLargeData(String key, byte[] val, InsertOptions iop)
	{
		if(key == null || val == null)
			return -1;
		if(iop == null)
			iop = InsertOptions.INSERT_UNIQUE;
		return put_large_data(pttbl, key, val, iop.ordinal());
	}

	public byte[] getLargeData(String key)
	{
		if(key == null)
			return null;
		return get_large_data(pttbl, key);
	}

	public String listLargeDataKeys(String skey, int list_size_mb)
	{
		if(skey == null)
			return null;
		return list_large_data_keys(pttbl, skey, list_size_mb);
	}

	public int countSliceLargeData(String key)
	{
		if(key == null)
			return -1;
		return count_slice_large_data(pttbl, key);
	}

	public long countLargeData()
	{
		return count_large_data(pttbl);
	}

	public int delLargeData(String key)
	{
		if(key == null)
			return -1;
		return del_large_data(pttbl, key);
	}

	// for opaque data
	public long put(String key, byte[] val, InsertOptions flag, Transaction txn)
	{
		if(key == null || val == null)
			return -1;
		if(flag == null)
			flag = InsertOptions.INSERT_UNIQUE;
		return put(pttbl, key, val, flag.ordinal(), txn  != null ? txn.pttxn : 0);
	}

	public long put(long key, byte[] val, InsertOptions flag, Transaction txn)
	{
		if(val == null)
			return -1;
		if(flag == null)
			flag = InsertOptions.INSERT_UNIQUE;
		return put(pttbl, key, val, flag.ordinal(), txn  != null ? txn.pttxn : 0);
	}

	public long put(String key, String val, InsertOptions flag, Transaction txn)
	{
		if(key == null || val == null)
			return -1;
		if(flag == null)
			flag = InsertOptions.INSERT_UNIQUE;
		return put(pttbl, key, val, flag.ordinal(), txn  != null ? txn.pttxn : 0);
	}

	public long put(long key, String val, InsertOptions flag, Transaction txn)
	{
		if(val == null)
			return -1;
		if(flag == null)
			flag = InsertOptions.INSERT_UNIQUE;
		return put(pttbl, key, val, flag.ordinal(), txn  != null ? txn.pttxn : 0);
	}

	public ResultSet scan(ResultSet prev_rs, String pk_skey, String pk_ekey, ScanFilter sf, Transaction txn)
	{
		if(sf == null)
			return null;
		return scan_data(pttbl, prev_rs != null ? prev_rs.ptrs : 0, pk_skey, pk_ekey, sf.pscanf, txn  != null ? txn.pttxn : 0);
	}
	
	public ResultSet scan(ResultSet prev_rs, long pk_skey, long pk_ekey, ScanFilter sf, Transaction txn)
	{
		if(sf == null)
			return null;
		return scan_data(pttbl, prev_rs != null ? prev_rs.ptrs : 0, pk_skey, pk_ekey, sf.pscanf, txn  != null ? txn.pttxn : 0);
	}
	
	public ResultSet scan(ResultSet prev_rs, byte[] pk_skey, byte[] pk_ekey, ScanFilter sf, Transaction txn)
	{
		if(sf == null)
			return null;
		return scan_data(pttbl, prev_rs != null ? prev_rs.ptrs : 0, pk_skey, pk_ekey, sf.pscanf, txn  != null ? txn.pttxn : 0);
	}

	// for text data
	// reverse indexes the data (str)
	// FDT key, if null then timestamp
	public long putText(String str, String k, InsertOptions flag)
	{
		if(str == null)
			return -1;
		return put_text(pttbl, str, k, flag.ordinal());
	}
	
	public long putText(String str, long k, InsertOptions flag)
	{
		if(str == null)
			return -1;
		return put_text(pttbl, str, k, flag.ordinal());
	}

	public ResultSet scanText(String[] wlist, boolean intersect)
	{
		if(wlist == null)
			return null;
		return scan_text(pttbl, wlist, wlist.length, intersect ? 1 : 0);
	}

	// for json - doc data
	// put doc and update all indexes sec or text
	// rev_idx_fields_json = {\"_rev_idx_all\":0, \"_rev_idx_key_list\":[\"name\", \"city\"]}
	// name and city are keys in the doc
	// pk could be null as well
	public long putDoc(String doc, String pk, String rev_idx_fields_json, InsertOptions flag)
	{
		if(doc == null)
			return -1;
		if(flag == null)
			flag = InsertOptions.INSERT_UNIQUE;
		return put_doc(pttbl, doc, pk, rev_idx_fields_json, flag.ordinal());
	}
	
	public long putDoc(String doc, byte[] pk, String rev_idx_fields_json, InsertOptions flag)
	{
		if(doc == null)
			return -1;
		if(flag == null)
			flag = InsertOptions.INSERT_UNIQUE;
		return put_doc(pttbl, doc, pk, rev_idx_fields_json, flag.ordinal());
	}
	
	public long putDoc(String doc, long pk, String rev_idx_fields_json, InsertOptions flag)
	{
		if(doc == null)
			return -1;
		if(flag == null)
			flag = InsertOptions.INSERT_UNIQUE;
		return put_doc(pttbl, doc, pk, rev_idx_fields_json, flag.ordinal());
	}

	// recursive call, only accepts AND as join operator
	// idx_filter_json = "{\"query\":[{\"key\":\"firstname\", \"cmp_op\":3, \"val\":\"sachin\"},{\"joinop\":0},{\"match_words\":\"generalize companyA\"}], \"qtype\":1}";
	// idx_filter_json = "{\"query\":[{\"key\":\"firstname\", \"cmp_op\":3, \"val\":\"$lastname\"}], \"qtype\":2}";  -- note: check the qtype 2 and $lastname as val, it means
	// for the doc, match first name and lastname using cmp_op, and
	// may use dataQuery type to create the filter json
	public ResultSet scanDoc(ResultSet prev_rs, String pk_skey, String pk_ekey, String idx_filter_json, ScanFilter sf)
	{
		if(sf == null)
			return null;
		return scan_doc(pttbl, prev_rs != null ? prev_rs.ptrs : 0, pk_skey, pk_ekey, idx_filter_json, sf.pscanf);
	}
	
	public ResultSet scanDoc(ResultSet prev_rs, long pk_skey, long pk_ekey, String idx_filter_json, ScanFilter sf)
	{
		if(sf == null)
			return null;
		return scan_doc(pttbl, prev_rs != null ? prev_rs.ptrs : 0, pk_skey, pk_ekey, idx_filter_json, sf.pscanf);
	}
	
	public ResultSet scanDoc(ResultSet prev_rs, byte[] pk_skey, byte[] pk_ekey, String idx_filter_json, ScanFilter sf)
	{
		if(sf == null)
			return null;
		return scan_doc(pttbl, prev_rs != null ? prev_rs.ptrs : 0, pk_skey, pk_ekey, idx_filter_json, sf.pscanf);
	}

	public byte[] get(String key, Transaction txn)
	{
		if(key == null)
			return null;
		return get(pttbl, key, txn  != null ? txn.pttxn : 0);
	}
	
	public byte[] get(long key, Transaction txn)
	{
		return get(pttbl, key, txn  != null ? txn.pttxn : 0);
	}

	public long del(String key, Transaction txn)
	{
		if(key == null)
			return -1;
		return del(pttbl, key, txn  != null ? txn.pttxn : 0);
	}
	
	public long del(long key, Transaction txn)
	{
		return del(pttbl, key, txn  != null ? txn.pttxn : 0);
	}

	public long count(String pk_skey, String pk_ekey, String idx_filter_json, ScanFilter sf)
	{
		if(sf == null)
			return -1;
		return count(pttbl, pk_skey, pk_ekey, idx_filter_json, sf.pscanf);
	}
	
	public long count(long pk_skey, long pk_ekey, String idx_filter_json, ScanFilter sf)
	{
		if(sf == null)
			return -1;
		return count(pttbl, pk_skey, pk_ekey, idx_filter_json, sf.pscanf);
	}

	public long expCount(String skey, String ekey)
	{
		return exp_count(pttbl, skey, ekey);
	}
	
	public long expCount(long skey, long ekey)
	{
		return exp_count(pttbl, skey, ekey);
	}

	public long count()
	{
		return count(pttbl);
	}

	public void setAutoCommit(boolean flag)
	{
		set_auto_commit(pttbl, flag ?  1: 0);
	}

	public boolean isSameAs(BangDBTable tbl)
	{
		return tbl != null ? (this.pttbl == tbl.pttbl) : false;
	}

	public TableType getTableType()
	{
		return TableType.fromInt(get_table_type(pttbl));
	}
}
