package com.bangdb;
//import java.util.Map;
//import java.util.HashMap;

public class BangDBEnv {

	//native methods
	public native long getBangDBEnv(String host, String port, String clconfigPath, String user, String pwd, short init_sock_conn, short tls);
	public native BangDBDatabase getBangDBDatabase(long ptdbenv, String dbName, String dbPath, String logPath, String configPath, String archivePath, String host, String port, String user, String pwd, int tranType, boolean justv);
	public native DBParam verifyDatabase(long ptdbenv, String dbName);
	public native void closedatabase(short dbclose, long ptdb, long ptdbenv);
	public native void closeDBEnv(long ptdbenv, int forceclose);
/*
 	public native void setPskIdentity_internal(long ptdbenv, long pskIdentity);
	public native short getIsAdmin(long ptdbenv);
	public native void setPskKey_internal(long ptdbenv, String pskKey);
	public native int  resetSockConnPool_internal(long ptdbenv);

	private static Map<String, BangDBEnv> mapdbenv = new HashMap<String, BangDBEnv>();
	private static String default_key = "DEFAULT_KEY";
	private String hostName;
	private String portName;
	private String dbConfigPathName;
	private String clientConfigPathName;
	private String userName;
	private String password;
	private boolean isActive = false;
*/
	private BangDBDatabase db = null;
	private String hostName;
	private String portName;
	public long ptdbenv = 0;

	public BangDBEnv(DBParam dbp)
	{
		if(dbp == null)
		{
			System.out.println("BangDBEnv object param is null");
		}
		else
		{
			hostName = dbp.get_host();
			portName = dbp.get_port();
			short init_sock = (short)(dbp.get_init_sock_conns() == true ? 1 : 0);
			short istls = (short)(dbp.get_is_tls_conn() == true ? 1 : 0);
			ptdbenv = getBangDBEnv(dbp.get_host(), dbp.get_port(), dbp.getClientConfigPath(), dbp.get_userid(), dbp.get_pwd(), init_sock, istls);
		}
	}

	public DBParam verifyDatabase(String dbName)
	{
		if(dbName == null || ptdbenv == 0) 
			return null;
		DBParam dbp = verifyDatabase(ptdbenv, dbName);
		return dbp;
	}
	public synchronized void closeDatabase(CloseType dbclose) 
	{
		if(db == null || ptdbenv == 0) 
			return;
		if(dbclose == null)
			dbclose = CloseType.DEFAULT_AT_CLIENT;
		closedatabase((short) dbclose.ordinal(), db.ptdb, ptdbenv);
		db.ptdb = 0;
		db = null;
	}

	public String getHost() 
	{
		if(ptdbenv == 0)
			return null;
		return hostName;
	}

	public String getPort() 
	{
		if(ptdbenv == 0)
			return null;
		return portName;
	}

	public synchronized void close() 
	{
		if(ptdbenv == 0) 
		{
			System.out.println("dbenv is null");
			return;
		}
		closeDBEnv(ptdbenv, 0);
		//String k = hostName + portName;
		//mapdbenv.remove(k);
		ptdbenv = 0;
		//isActive = false;
	}

	public synchronized void close(boolean forceclose) 
	{
		if(ptdbenv == 0) 
		{
			System.out.println("dbenv is null");
			return;
		}
		closeDBEnv(ptdbenv, forceclose ? 1 : 0);
		//String k = hostName + portName;
		//mapdbenv.remove(k);
		ptdbenv = 0;
		//isActive = false;
	}

        public synchronized BangDBDatabase openDatabase(String dbName, DBParam dbparam)
        {
                if(db == null)
                {
                        if(dbName == null || dbparam == null)
                                return null;
                        BangDBDatabase tempDb = getBangDBDatabase(ptdbenv, dbName, dbparam.getDBPath(), dbparam.getLogPath(), dbparam.getDBConfigPath(), dbparam.get_archivePath(), dbparam.get_host(), dbparam.get_port(), dbparam.get_userid(), dbparam.get_pwd(), dbparam.getTransactionType().ordinal(), dbparam.get_just_verify());
                        //System.out.println("opening database " + tempDb.ptdb);
                        if (tempDb.ptdb != 0)
                        {
                                db = tempDb;
                        }
                }
                return db;
        }

/*
	public static BangDBDatabase getBangDB(String dbname, BangDBEnv env, DBParam dbp)
	{
		if(env == null)
		{
			System.out.println("BangDBEnv object param is null");
			return null;
		}

		if(env.db != null)
		{
			return env.db;
		}

		BangDBEnv _env = getInstance(dbp);
		if(_env == null)
		{
			System.out.println("BangDBEnv could not be created");
			return null;
		}
		env.mapdbenv = _env.mapdbenv;
		env.default_key = _env.default_key;
		env.hostName = _env.hostName;
		env.portName = _env.portName;
		env.dbConfigPathName = _env.dbConfigPathName;
		env.clientConfigPathName = _env.clientConfigPathName;
		env.userName = _env.userName;
		env.password = _env.password;
		env.isActive = _env.isActive;
		env.ptdbenv = _env.ptdbenv;

		BangDBDatabase tempdb = env.openDatabase(dbname, dbp);
	
		if (tempdb.ptdb != 0) 
		{
			env.db = tempdb;
		}
		return env.db;
	}

	private BangDBEnv() 
	{
		isActive = true;
		db = null;
		hostName = portName = dbConfigPathName = clientConfigPathName = userName = password = null;
		ptdbenv = getBangDBEnv(hostName, portName, clientConfigPathName, userName, password, (short)1, (short)0);
	}

	private BangDBEnv(String host, String port, String clientConfigPath, String user, String pwd, boolean init_sock_conn, boolean tls) 
	{
		isActive = true;
		db = null;
		hostName = host;
		portName = port;
		clientConfigPathName = clientConfigPath;
		userName = user;
		password = pwd;
		ptdbenv = getBangDBEnv(hostName, portName, clientConfigPathName, userName, password, (short)(init_sock_conn?1:0), (short)(tls?1:0));
	}

	public static BangDBEnv getInstance(DBParam dbp)
	{
		if(dbp == null)
		{
			System.out.println("dbparam is null");
			return null;
		}
	   	return getInstance(dbp.get_host(), dbp.get_port(), dbp.getClientConfigPath(), dbp.get_userid(), dbp.get_pwd(), dbp.get_init_sock_conns(), dbp.get_is_tls_conn());
	}
	
	public static synchronized BangDBEnv getInstance() 
	{
		if(mapdbenv.containsKey(default_key))
			return mapdbenv.get(default_key);
		BangDBEnv obj = new BangDBEnv();
		if (obj.ptdbenv != 0) 
		{
			mapdbenv.put(default_key, obj);
			return obj;
		}
		return null;
	}
	

	private static synchronized BangDBEnv getInstance(String host, String port, String configPath, String user, String pwd, boolean init_sock_conn, boolean tls) 
	{
		String k = (host == null ? "0":host) + (port == null ? "0":port);
		if(mapdbenv.containsKey(k))
			return mapdbenv.get(k);
		BangDBEnv obj = new BangDBEnv(host, port, configPath, user, pwd, init_sock_conn, tls);
		if (obj.ptdbenv != 0) 
		{
			mapdbenv.put(k, obj);
			return obj;
		}
		return null;
	}

	public synchronized BangDBDatabase openDatabase(String dbName, DBParam dbparam) 
	{
		if(db == null) 
		{
			if(dbName == null || dbparam == null)
				return null;
			BangDBDatabase tempDb = getBangDBDatabase(ptdbenv, dbName, dbparam.getDBPath(), dbparam.getLogPath(), dbparam.getDBConfigPath(), dbparam.get_archivePath(), dbparam.get_host(), dbparam.get_port(), dbparam.get_userid(), dbparam.get_pwd(), dbparam.getTransactionType().ordinal(), dbparam.get_just_verify());
			//System.out.println("opening database " + tempDb.ptdb);
			if (tempDb.ptdb != 0) 
			{
				db = tempDb;
			}
		}
		return db;
	}
	

	public BangDBDatabase getDatabaseRef() 
	{
		return db;
	}

	public boolean isAdmin() 
	{
		return (getIsAdmin(ptdbenv) != 0);
	}


	public void  setPskIdentity(long pskIdentity) 
	{
		if (ptdbenv != 0) setPskIdentity_internal(ptdbenv, pskIdentity);
	}

	public void  setPskKey(String pskKey) 
	{
		if(pskKey == null)
		{
			System.out.println("pskKey is null");
			return;
		}
		if (ptdbenv != 0) setPskKey_internal(ptdbenv, pskKey);
	}

	public int   resetSockConnPool() 
	{
		if (ptdbenv != 0) return resetSockConnPool_internal(ptdbenv);
		return -1;
	}

	public synchronized void close(String host, String port) 
	{
		if(!isActive || ptdbenv == 0) 
		{
			System.out.println("dbenv is not active or the ptdbenv is null");
			return;
		}
		closeDBEnv(ptdbenv);
		String k = (host == null ? "0":host) + (port == null ? "0":port);
		mapdbenv.remove(k);
		ptdbenv = 0;
		isActive = false;
	}

	public void finalize() {
		close();
	}
*/
}
