package com.bangdb;

public class DBParam {

	String _host=null;
	String _port=null;
	String _userid=null;
	String _pwd=null;
	String _clconfigPath=null;
	String _dbconfigPath=null;
	String _dbPath=null;
	String _logPath=null;
	String _archivePath=null;
	TransactionType _tranType=null;
	boolean _init_sock_conns=true;
	boolean _is_tls_conn=false;
	boolean _just_verify = false;

	public boolean get_just_verify()
	{
		return _just_verify;
	}

	public void set_just_verify(boolean just_verify)
	{
		_just_verify = just_verify;
	}
	
	public boolean get_init_sock_conns() 
	{
		return _init_sock_conns;
	}
	public void set_init_sock_conns(boolean _init_sock_conns) 
	{
		this._init_sock_conns = _init_sock_conns;
	}
	public boolean get_is_tls_conn() 
	{
		return _is_tls_conn;
	}
	public void set_is_tls_conn(boolean _is_tls_conn) 
	{
		this._is_tls_conn = _is_tls_conn;
	}
	public String get_host() 
	{
		return _host;
	}
	public void set_host(String _host) 
	{
		this._host = _host;
	}
	public String get_port() 
	{
		return _port;
	}
	public void set_port(String _port) 
	{
		this._port = _port;
	}
	public String get_userid() 
	{
		return _userid;
	}
	public void set_userid(String _userid) 
	{
		this._userid = _userid;
	}
	public String get_pwd() 
	{
		return _pwd;
	}
	public void set_pwd(String _pwd) 
	{
		this._pwd = _pwd;
	}
	public String get_archivePath() 
	{
		return _archivePath;
	}
	public void set_archivePath(String _archivePath) 
	{
		this._archivePath = _archivePath;
	}
	public void setDBPath(String dbPath)
	{
		_dbPath = dbPath;
	}
	public String getDBPath()
	{
		return _dbPath;
	}
	public void setLogPath(String logPath)
	{
		_logPath = logPath;
	}
	public String getLogPath()
	{
		return _logPath;
	}
	public void setDBConfigPath(String dbconfigPath)
	{
		_dbconfigPath = dbconfigPath;
	}
	public void setClientConfigPath(String clconfigPath)
	{
		_clconfigPath = clconfigPath;
	}
	public String getDBConfigPath()
	{
		return _dbconfigPath;
	}
	public String getClientConfigPath()
	{
		return _clconfigPath;
	}
	public void setTransactionType(TransactionType tranType)
	{
		_tranType = tranType;
	}
	public TransactionType getTransactionType()
	{
		return _tranType;
	}
	public void set(String[] args)
	{
		if(args.length != 12)
		{
			System.out.println("set args has length not equal to 12 [ "+args.length +"]");
			return;
		}
		int i = 0;
		_host = args[i++];
		_port = args[i++];
		_userid = args[i++];
		_pwd = args[i++];
		_clconfigPath = args[i++];
		_dbconfigPath = args[i++];
		_dbPath = args[i++];
		_logPath = args[i++];
		_archivePath = args[i++];
		_tranType = TransactionType.fromInt(Integer.parseInt(args[i++]));
		_is_tls_conn = Integer.parseInt(args[i++]) == 1 ? true : false;
		_init_sock_conns = Integer.parseInt(args[i]) == 1 ? true : false;
	}
	@Override
	public String toString() 
	{
		return "DBParam [_host=" + _host + ", _port=" + _port + ", _userid=" + _userid + ", _pwd=" + _pwd
				+ ", _dbconfigPath=" + _dbconfigPath + ", _clconfigPath =" + _clconfigPath + ", _dbPath=" + _dbPath + ", _logPath=" + _logPath + ", _archivePath="
				+ _archivePath + ", _tranType=" + _tranType + ", _is_tls_conn=" + _is_tls_conn +", _init_sock_conns=" + _init_sock_conns + ", _just_verify=" + _just_verify +"]";
	}
	
}
