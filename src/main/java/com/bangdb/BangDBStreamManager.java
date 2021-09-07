package com.bangdb;

public class BangDBStreamManager {
	private native long get_bsm(long pbdb);
	
	private native String register_schema(long ptsmgr, String schema_json);

	private native String deregister_schema(long ptsmgr, String schema_name, int cleanclose);

	private native String add_streams(long ptsmgr, long schemaid, String streams);

	private native String delete_streams(long ptsmgr, long schemaid, String streams);

	private native int get_schema_ddl_state(long ptsmgr, long schemaid);

	private native long get_schemaid(long ptsmgr, String schema_name, int check_valid);

	private native long get_streamid(long ptsmgr, String schema_name, String stream_name, int check_valid);

	private native String set_stream_state(long ptsmgr, String schema_name, String stream_name, short state);

	private native int get_stream_state(long ptsmgr, String schema_name, String stream_name);

	/************************* CRUD API for streams ************************/

	private native String put_stream(long ptsmgr, long schemaid, long streamid, String doc);

	private native String put_stream(long ptsmgr, long schemaid, long streamid, long k, String v);

	private native ResultSet scan_doc(long ptsmgr, long schemaid, long streamid, long pprev_rs, long k1, long k2, String idx_filter_json, long psf);

	private native ResultSet scan_proc_doc(long ptsmgr, long schemaid, long streamid, String attr_names_json, long pprev_rs, long psf);

	private native ResultSet scan_registered_notif(long ptsmgr, long pprev_rs, long k1, long k2, String idx_filter_json, long psf);

	private native long count_proc(long ptsmgr, long schemaid, long streamid, String attr_names_json, long psf);

	private native ResultSet scan_usage(long ptsmgr, long pprev_rs, long fromts, long tots, int rollup, long pfs);

	private native String get_gpby_name(long ptsmgr, long schemaid, long streamid, String gpby_attr_list);
	
	private native long get_gpby_id(long ptsmgr, long schemaid, long streamid, String gpby_attr_list);

	private native long count(long ptsmgr, long schemaid, long streamid);

	private native long count_filter(long ptsmgr, long schemaid, long streamid, long sk, long ek, String filter_json, long psf);

	private native String add_udfs(long ptsmgr, long schema_id, String udfs);

	private native String del_udfs(long ptsmgr, long schema_id, String udfs);

	private native String compile_udf(long ptsmgr, String code);

	private native String get_udf_list(long ptsmgr);

	private native String get_schema_list(long ptsmgr);

	private native String get_schema_str(long ptsmgr, String schemaName, short from_meta);

	private native String get_schema_dep_graph(long ptsmgr, long schema_id, int bfs);

	private native String get_stream_dep_graph(long ptsmgr, long schema_id, long stream_id, int only_dep);

	private native void reset_ml_helper(long ptsmgr, long pbmlh);

	private native void close_bangdb_stream_manager(long ptsmgr, int force);
	
	public long ptsmgr;
	
	public BangDBStreamManager(BangDBEnv bdbenv)
	{
		if(bdbenv == null)
		{
			System.out.println("db env is null");
			return;
		}
		ptsmgr = get_bsm(bdbenv.ptdbenv);
	}
	
	public String registerSchema(String schema_json)
	{
		if(schema_json == null)
			return null;
		return register_schema(ptsmgr, schema_json);
	}

	public String deregisterSchema(String schema_name)
	{
		if(schema_name == null)
			return null;
		return deregister_schema(ptsmgr, schema_name, 1);
	}

	public String deregisterSchema(String schema_name, boolean cleanclose)
	{
		if(schema_name == null)
			return null;
		return deregister_schema(ptsmgr, schema_name, cleanclose ? 1 : 0);
	}

	public String addStreams(long schemaid, String streams)
	{
		if(streams == null)
			return null;
		return add_streams(ptsmgr, schemaid, streams);
	}

	public String deleteStreams(long schemaid, String streams)
	{
		if(streams == null)
			return null;
		return delete_streams(ptsmgr, schemaid, streams);
	}

	public int getSchemaDDLState(long schemaid)
	{
		return get_schema_ddl_state(ptsmgr, schemaid);
	}

	public long getSchemaid(String schema_name, boolean check_valid)
	{
		if(schema_name == null)
			return -1;
		return get_schemaid(ptsmgr, schema_name, check_valid ? 1 : 0);
	}

	public long getStreamid(String schema_name, String stream_name, boolean check_valid)
	{
		if(schema_name == null || stream_name == null)
			return -1;
		return get_streamid(ptsmgr, schema_name, stream_name, check_valid ? 1 : 0);
	}

	public String setStreamState(String schema_name, String stream_name, short state)
	{
		if(schema_name == null || stream_name == null)
			return null;
		return set_stream_state(ptsmgr, schema_name, stream_name, state);
	}

	public int getStreamState(String schema_name, String stream_name)
	{
		if(schema_name == null || stream_name == null)
			return -1;
		return get_stream_state(ptsmgr, schema_name, stream_name);
	}

	/************************* CRUD API for streams ************************/

	public String put(long schemaid, long streamid, String doc)
	{
		if(doc == null)
			return null;
		return put_stream(ptsmgr, schemaid, streamid, doc);
	}

	public String put(long schemaid, long streamid, long k, String v)
	{
		if(v == null)
			return null;
		return put_stream(ptsmgr, schemaid, streamid, k, v);
	}

	public ResultSet scanDoc(long schemaid, long streamid, ResultSet prev_rs, long k1, long k2, String idx_filter_json, ScanFilter sf)
	{
		if(sf == null)
			return null;
		return scan_doc(ptsmgr, schemaid, streamid, prev_rs != null ? prev_rs.ptrs : 0, k1, k2, idx_filter_json, sf.pscanf);
	}

	public ResultSet scanProcDoc(long schemaid, long streamid, String attr_names_json, ResultSet prev_rs, ScanFilter sf)
	{
		if(attr_names_json == null || sf == null)
			return null;
		return scan_proc_doc(ptsmgr, schemaid, streamid, attr_names_json, prev_rs != null ? prev_rs.ptrs : 0, sf.pscanf);
	}

	public ResultSet scanRegisteredNotif(ResultSet prev_rs, long k1, long k2, String idx_filter_json, ScanFilter sf)
	{
		if(sf == null)
			return null;
		return scan_registered_notif(ptsmgr, prev_rs != null ? prev_rs.ptrs : 0, k1, k2, idx_filter_json, sf.pscanf);
	}

	public ResultSet scanUsage(ResultSet prev_rs, long fromts, long tots, int rollup, ScanFilter sf)
	{
		if(sf == null)
			return null;
		return scan_usage(ptsmgr, prev_rs != null ? prev_rs.ptrs : 0, fromts, tots, rollup, sf.pscanf);
	}

	public long countProc(long schemaid, long streamid, String attr_names_json, ScanFilter sf)
	{
		if(attr_names_json == null || sf == null)
			return -1;
		return count_proc(ptsmgr, schemaid, streamid, attr_names_json, sf.pscanf);
	}

	public String getGpbyName(long schemaid, long streamid, String gpby_attr_list)
	{
		if(gpby_attr_list == null)
			return null;
		return get_gpby_name(ptsmgr, schemaid, streamid, gpby_attr_list);
	}
	
	public long getGpbyId(long schemaid, long streamid, String gpby_attr_list)
	{
		if(gpby_attr_list == null)
			return -1;
		return get_gpby_id(ptsmgr, schemaid, streamid, gpby_attr_list);
	}

	public long count(long schemaid, long streamid)
	{
		return count(ptsmgr, schemaid, streamid);
	}

	public long count(long schemaid, long streamid, long psk, long pek, String filter_json, ScanFilter sf)
	{
		if(sf == null)
			return -1;
		return count_filter(ptsmgr, schemaid, streamid, psk, pek, filter_json, sf.pscanf);
	}

	public String addUdfs(long schema_id, String udfs)
	{
		if(udfs == null)
			return null;
		return add_udfs(ptsmgr, schema_id, udfs);
	}

	public String delUdfs(long schema_id, String udfs)
	{
		if(udfs == null)
			return null;
		return del_udfs(ptsmgr, schema_id, udfs);
	}

	public String compileUdf(String code)
	{
		if(code == null)
			return null;
		return compile_udf(ptsmgr, code);
	}

	public String getUDFList()
	{
		return get_udf_list(ptsmgr);
	}

	public String getSchemaList()
	{
		return get_schema_list(ptsmgr);
	}

	public String getSchemaDepGraph(long schema_id, boolean bfs)
	{
		return get_schema_dep_graph(ptsmgr, schema_id, bfs ? 1 : 0);
	}

	// from_meta = 0 means from memory, else 1 means, get from the meta store
	public String getSchemaStr(String schemaName, short from_meta)
	{
		if(schemaName == null)
			return null;
		return get_schema_str(ptsmgr, schemaName, from_meta);
	}

	public String getStreamDepGraph(long schema_id, long stream_id, boolean only_dep)
	{
		return get_stream_dep_graph(ptsmgr, schema_id, stream_id, only_dep ? 1 : 0);
	}

	public void resetMlHelper(BangDBMLHelper bmlh)
	{
		if(bmlh != null)
			reset_ml_helper(ptsmgr, bmlh.ptmlh);
	}

	public synchronized void closeBangdbStreamManager(CloseType closetype)
	{
		if(closetype == null)
			closetype = CloseType.DEFAULT_AT_CLIENT;
		close_bangdb_stream_manager(ptsmgr, closetype.ordinal());
	}
}
