package com.bangdb;

public class BangDBNotificationManager
{
	private native long get_nmgr(long ptdb);
	private native int register_notif(String notif, long ptnmgr);
	private native int deregister_notif(long notifid, short state, long ptnmgr);
	private native int put(long notifid, long pks, long orig_strmid, String notif, long ptnmgr);
	private native ResultSet scan_doc(long ptnmgr, long prev_rs, long pk_skey, long pk_ekey, String idx_filter_json, long psf);
	private native ResultSet scan_registered_notif(long ptsmgr, long pprev_rs, long k1, long k2, String idx_filter_json, long psf);
	private native int close_nmgr(int closetype, long ptnmgr);
	public long ptnmgr;
	
	public BangDBNotificationManager(BangDBEnv bdbenv)
	{
		if(bdbenv == null)
		{
			System.out.println("db env is null");
			return;
		}
		ptnmgr = get_nmgr(bdbenv.ptdbenv);
	}
	
	// notif is json String, notifid is previded by the user
	// {"notifid":12345,"name":"notif1","msg":"users msg","rule":"notification rule/condition","pri":1,"mailto":[], 
	// "endpoints":["http://192.168.1.3:10101/account"],"schemaid":1234,"notif_streamid":4321,"notif_stream_name":"sdf","freq":1,"tags":["a"],"rel_streams":["s1"]}
	public int registerNotification(String notif_meta)
	{
		if(notif_meta == null)
		{
			System.out.println("notif is null");
			return -1;
		}
		return register_notif(notif_meta, ptnmgr);
	}

	// state = 0 means pause, 1 means activate, -1 means delete completely
	public int deregisterNotification(long notifid, short state)
	{
		return deregister_notif(notifid, state, ptnmgr);
	}

	public int put(long notifid, long pks, long orig_strmid, String notif)
	{
		if(notif == null)
		{
			System.out.println("notif is null");
			return -1;
		}
		return put(notifid, pks, orig_strmid, notif, ptnmgr);
	}

	public ResultSet scanDoc(ResultSet prev_rs, long pk_skey, long pk_ekey, String idx_filter_json, ScanFilter sf)
	{
		if(sf == null)
			return null;
		return scan_doc(ptnmgr, prev_rs != null ? prev_rs.ptrs : 0, pk_skey, pk_ekey, idx_filter_json, sf.pscanf);
	}

	public ResultSet scanRegisteredNotif(ResultSet prev_rs, long k1, long k2, String idx_filter_json, ScanFilter sf)
	{
		if(sf == null)
			return null;
		return scan_registered_notif(ptnmgr, prev_rs != null ? prev_rs.ptrs : 0, k1, k2, idx_filter_json, sf.pscanf);
	}

	public int closeNotificationManager(CloseType closetype)
	{
		if(closetype == null)
			closetype = CloseType.DEFAULT_AT_CLIENT;
		return close_nmgr(closetype.ordinal(), ptnmgr);
	}
}
