package com.bangdb;

public class TableEnv {
	public native int[] set_tenv(long pttenv);
	
	PersistType db_type;
	IndexType idx_type;
	LogType table_log_type;
	TableType table_type;
	KeyType key_type;
	SortMethod sort_method;
	SortDirection sort_direction;
	TableSubType tbl_sub_type;
	PrimitiveDataType prim_data_type;
	VersionType version_type;
	TableSizeHint table_sz_hint;
	
	int key_sz;
	int log_sz_mb;
	int wal_enabled;
	int autocommit;
	int allow_duplicate;
	int allow_rev_idx;	//only applies to wide table
	int ttlsec;
	
	public void reset() 
	{
		db_type = PersistType.INMEM_PERSIST;
		idx_type = IndexType.BTREE;
		table_log_type = LogType.SHARED_LOG;
		table_type = TableType.NORMAL_TABLE;
		key_type = KeyType.NORMAL_KEY;
		sort_method = SortMethod.QUASI_LEXICOGRAPH;
		sort_direction = SortDirection.SORT_ASCENDING;
		tbl_sub_type = TableSubType.NON_ANALYTICAL_TABLE;
		prim_data_type = PrimitiveDataType.PRIMITIVE_INVALID;
		version_type = VersionType.BANGDB_DATA_VERSION_INVALID;
		table_sz_hint = TableSizeHint.NORMAL_TABLE_SIZE;
		
		key_sz = -1;
		log_sz_mb = -1;
		wal_enabled = 1;
		autocommit = 1;
		allow_duplicate = 0;
		allow_rev_idx = 0;
		ttlsec = 0;
		
		//System.out.println("key_type = " + key_type + "ordinal = " + key_type.ordinal());
	}
	
	public int[] get()
	{
		int[] x = new int[18];
		int i = 0;
		x[i++] = PersistType.toInt(db_type.ordinal());
		x[i++] = IndexType.toInt(idx_type.ordinal());
		x[i++] = LogType.toInt(table_log_type.ordinal());
		x[i++] = TableType.toInt(table_type.ordinal());
		x[i++] = KeyType.toInt(key_type.ordinal());
		x[i++] = SortMethod.toInt(sort_method.ordinal());
		x[i++] = SortDirection.toInt(sort_direction.ordinal());
		x[i++] = TableSubType.toInt(tbl_sub_type.ordinal());
		x[i++] = PrimitiveDataType.toInt(prim_data_type.ordinal());
		x[i++] = VersionType.toInt(version_type.ordinal());
		x[i++] = TableSizeHint.toInt(table_sz_hint.ordinal());
		x[i++] = key_sz;
		x[i++] = log_sz_mb;
		x[i++] = wal_enabled;
		x[i++] = autocommit;
		x[i++] = allow_duplicate;
		x[i++] = allow_rev_idx;
		x[i++] = ttlsec;
		
		return x;
	}
	
	public TableEnv() 
	{
			reset();
	}
	
	private void set(int[] tenvarr)
	{
		_set(tenvarr);
	}
	
	private void _set(int[] attr)
	{
		if(attr.length != 18)
		{
			System.out.println("tenv could not be set");
			return;
		}
		
		System.out.println("setting up tenv");
		
		db_type = PersistType.fromInt(attr[0]);
		idx_type = IndexType.fromInt(attr[1]);
		table_log_type = LogType.fromInt(attr[2]);
		table_type = TableType.fromInt(attr[3]);
		key_type = KeyType.fromInt(attr[4]);
		sort_method = SortMethod.fromInt(attr[5]);
		sort_direction = SortDirection.fromInt(attr[6]);
		tbl_sub_type = TableSubType.fromInt(attr[7]);
		prim_data_type = PrimitiveDataType.fromInt(attr[8]);
		version_type = VersionType.fromInt(attr[9]);
		table_sz_hint = TableSizeHint.fromInt(attr[10]);
		
		key_sz = attr[11];
		log_sz_mb = attr[12];
		wal_enabled = attr[13];
		autocommit = attr[14];
		allow_duplicate = attr[15];
		allow_rev_idx = attr[16];
		ttlsec = attr[17];
		System.out.println("key_type = "+key_type);
	}

	public PersistType getDbType() 
	{
		return db_type;
	}

	public void setDbType(PersistType db_type) 
	{
		this.db_type = db_type;
	}

	public IndexType getIdxType() 
	{
		return idx_type;
	}

	public void setIdxType(IndexType idx_type) 
	{
		this.idx_type = idx_type;
	}

	public LogType getTableLogType() 
	{
		return table_log_type;
	}

	public void setTableLogType(LogType table_log_type) 
	{
		this.table_log_type = table_log_type;
	}

	public TableType getTableType() 
	{
		return table_type;
	}

	public void setTableType(TableType table_type) 
	{
		this.table_type = table_type;
	}

	public KeyType getKeyType() 
	{
		return key_type;
	}

	public void setKeyType(KeyType key_type) 
	{
		this.key_type = key_type;
	}

	public SortMethod getSortMethod() 
	{
		return sort_method;
	}

	public void setSortMethod(SortMethod sort_method) 
	{
		this.sort_method = sort_method;
	}

	public SortDirection getSortDirection() 
	{
		return sort_direction;
	}

	public void setSortDirection(SortDirection sort_direction) 
	{
		this.sort_direction = sort_direction;
	}

	public TableSubType getTblSubType() 
	{
		return tbl_sub_type;
	}

	public void setTblSubType(TableSubType tbl_sub_type) 
	{
		this.tbl_sub_type = tbl_sub_type;
	}

	public PrimitiveDataType getPrimDataType() 
	{
		return prim_data_type;
	}

	public void setPrimDataType(PrimitiveDataType prim_data_type) 
	{
		this.prim_data_type = prim_data_type;
	}

	public VersionType getVersionType() 
	{
		return version_type;
	}

	public void setVersionType(VersionType version_type) 
	{
		this.version_type = version_type;
	}

	public TableSizeHint getTableSizeHint() 
	{
		return table_sz_hint;
	}

	public void setTableSizeHint(TableSizeHint table_sz_hint) 
	{
		this.table_sz_hint = table_sz_hint;
	}

	public int getKeySize() 
	{
		return key_sz;
	}

	public void setKeySize(int key_sz) 
	{
		this.key_sz = key_sz;
	}

	public int getLogSizeMb() 
	{
		return log_sz_mb;
	}

	public void setLogSizeMb(int log_sz_mb) 
	{
		this.log_sz_mb = log_sz_mb;
	}

	public int getWalEnabled() 
	{
		return wal_enabled;
	}

	public void setWalEnabled(int wal_enabled) 
	{
		this.wal_enabled = wal_enabled;
	}

	public int getAllowDuplicate() 
	{
		return allow_duplicate;
	}

	public void setAllowDuplicate(int allow_duplicate) 
	{
		this.allow_duplicate = allow_duplicate;
	}

	public int getAllowRevIdx() 
	{
		return allow_rev_idx;
	}

	public void setAllowRevIdx(int allow_rev_idx) 
	{
		this.allow_rev_idx = allow_rev_idx;
	}


	// deprecated apis
	public PersistType getDb_type() 
	{
		return db_type;
	}

	public void setDb_type(PersistType db_type) 
	{
		this.db_type = db_type;
	}

	public IndexType getIdx_type() 
	{
		return idx_type;
	}

	public void setIdx_type(IndexType idx_type) 
	{
		this.idx_type = idx_type;
	}

	public LogType getTable_log_type() 
	{
		return table_log_type;
	}

	public void setTable_log_type(LogType table_log_type) 
	{
		this.table_log_type = table_log_type;
	}

	public TableType getTable_type() 
	{
		return table_type;
	}

	public void setTable_type(TableType table_type) 
	{
		this.table_type = table_type;
	}

	public KeyType getKey_type() 
	{
		return key_type;
	}

	public void setKey_type(KeyType key_type) 
	{
		this.key_type = key_type;
	}

	public SortMethod getSort_method() 
	{
		return sort_method;
	}

	public void setSort_method(SortMethod sort_method) 
	{
		this.sort_method = sort_method;
	}

	public SortDirection getSort_direction() 
	{
		return sort_direction;
	}

	public void setSort_direction(SortDirection sort_direction) 
	{
		this.sort_direction = sort_direction;
	}

	public TableSubType getTbl_sub_type() 
	{
		return tbl_sub_type;
	}

	public void setTbl_sub_type(TableSubType tbl_sub_type) 
	{
		this.tbl_sub_type = tbl_sub_type;
	}

	public PrimitiveDataType getPrim_data_type() 
	{
		return prim_data_type;
	}

	public void setPrim_data_type(PrimitiveDataType prim_data_type) 
	{
		this.prim_data_type = prim_data_type;
	}

	public VersionType getVersion_type() 
	{
		return version_type;
	}

	public void setVersion_type(VersionType version_type) 
	{
		this.version_type = version_type;
	}

	public TableSizeHint getTable_sz_hint() 
	{
		return table_sz_hint;
	}

	public void setTable_sz_hint(TableSizeHint table_sz_hint) 
	{
		this.table_sz_hint = table_sz_hint;
	}

	public int getKey_sz() 
	{
		return key_sz;
	}

	public void setKey_sz(int key_sz) 
	{
		this.key_sz = key_sz;
	}

	public int getLog_sz_mb() 
	{
		return log_sz_mb;
	}

	public void setLog_sz_mb(int log_sz_mb) 
	{
		this.log_sz_mb = log_sz_mb;
	}

	public int getWal_enabled() 
	{
		return wal_enabled;
	}

	public void setWal_enabled(int wal_enabled) 
	{
		this.wal_enabled = wal_enabled;
	}

	public int getAutocommit() 
	{
		return autocommit;
	}

	public void setAutocommit(int autocommit) 
	{
		this.autocommit = autocommit;
	}

	public int getAllow_duplicate() 
	{
		return allow_duplicate;
	}

	public void setAllow_duplicate(int allow_duplicate) 
	{
		this.allow_duplicate = allow_duplicate;
	}

	public int getAllow_rev_idx() 
	{
		return allow_rev_idx;
	}

	public void setAllow_rev_idx(int allow_rev_idx) 
	{
		this.allow_rev_idx = allow_rev_idx;
	}

	public int getTtlsec() {
		return ttlsec;
	}

	public void setTtlsec(int ttlsec) 
	{
		this.ttlsec = ttlsec;
	}

	@Override
	public String toString() {
		return "TableEnv [db_type=" + db_type.ordinal() + ", idx_type=" + idx_type.ordinal() + ", table_log_type=" + table_log_type.ordinal()
				+ ", table_type=" + table_type.ordinal() + ", key_type=" + key_type.ordinal() + ", sort_method=" + sort_method.ordinal()
				+ ", sort_direction=" + sort_direction.ordinal() + ", tbl_sub_type=" + tbl_sub_type.ordinal() + ", prim_data_type="
				+ prim_data_type.ordinal() + ", version_type=" + version_type.ordinal() + ", table_sz_hint=" + table_sz_hint.ordinal() + ", key_sz="
				+ key_sz + ", log_sz_mb=" + log_sz_mb + ", wal_enabled=" + wal_enabled + ", autocommit=" + autocommit
				+ ", allow_duplicate=" + allow_duplicate + ", allow_rev_idx=" + allow_rev_idx + ", ttlsec=" + ttlsec
				+ "]";
	}
}
