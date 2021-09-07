package com.bangdb;

public class BangDBMLHelper
{
	//native methods
	private native long get_mlhelper(String brs_ip, String brs_port, String pred_ip, String pred_port, String train_ip, String train_port, String conf_path, int isssl);
	private native int create_bucket(String bucket_info, long ptmlh);
	private native void set_bucket(String bucket_info, long ptmlh);
	private native long upload_file(String key, String path, short flag, long ptmlh);
	private native long upload_file_bucket(String bucketInfo, String key, String path, short flag, long ptmlh);
	private native long download_file(String bucketInfo, String key, String fname, String fpath, long ptmlh);
	private native byte[] get_object(String bucketInfo, String key, long ptmlh); 
	private native int train_model(String req, long ptmlh);
	private native String get_model_status(String req, long ptmlh);
	private native int set_model_status(String req, long ptmlh);
	private native int del_model(String req, long ptmlh);
	private native int del_train_request(String req, long ptmlh);
	private native String predict(String req, long ptmlh);
	private native int predict_async(String req, long ptmlh);
	private native int del_pred_request(String req, long ptmlh);
	private native String get_model_pred_status(String req, long ptmlh);
	private native int reinit_mdm(String req, long ptmlh);
	private native int is_brs_local(long ptmlh);
	private native void close_mlhelper(long ptmlh);
	private native ResultSet get_training_requests(String req, long ptmlh);
	private native String get_request_detail(String req, long ptmlh);
	private native String list_buckets(String req, long ptmlh);
	private native String list_all_buckets(String req, long ptmlh);
	private native String list_objects(String req, String skey, int listSizeMB, long ptmlh);
	private native long get_model_count(String req, long ptmlh);
	private native long count_buckets(long ptmlh);
	private native long count_objects(String bucket_info, long ptmlh);
	private native String count_objects_details(String bucket_info, long ptmlh);
	private native int count_slices(String bucket_info, String key, long ptmlh);
	private native int del_file(String bucket_info, String key, long ptmlh);
	private native int del_bucket(String bucket_info, long ptmlh);
	private native long upload_stream_data_for_train(String req, long ptmlh);
	
	private static boolean isActive = false;
	public long ptmlh = 0;
	private static BangDBMLHelper bmlh = null;
	
	private BangDBMLHelper(String[] train_pred_brs_info, String conf_path, int isssl)
	{
		isActive = true;
		ptmlh = get_mlhelper(train_pred_brs_info[0], train_pred_brs_info[1], train_pred_brs_info[2], train_pred_brs_info[3], train_pred_brs_info[4], train_pred_brs_info[5], conf_path, isssl);
		if(ptmlh == 0)
		{
			System.out.println("BangDBMLHelper.java : error in getting ml helper");	
			isActive = false;
		}
	}
	
	/*
		train_pred_brs_info contains port and ip for following in order
		overall length of array should be 6
		order - brs, pred, train
	*/
	public static synchronized BangDBMLHelper getInstance(String[] train_pred_brs_info)
	{
		if(train_pred_brs_info == null)
			return null;
		
		int infolen = train_pred_brs_info.length;
		if(infolen < 6)
		{
			System.out.println("BangDBMLHelper is already active or the passed string info is of less than size 6");
			return null;
		}
		if(bmlh != null && isActive)
			return bmlh;
		String conf_path = null;
		int isssl = 1;
		if(infolen >= 7)
			conf_path = train_pred_brs_info[6];
		if(infolen == 8)
			isssl = train_pred_brs_info[7].equalsIgnoreCase("no") ? 0 : 1;
		 bmlh = new BangDBMLHelper(train_pred_brs_info, conf_path, isssl);
		return bmlh;	
	}

	public String toString()
	{
		return "active = " +isActive+ " ptmlh = " + ptmlh;
	}

	/*
		bucket_info = {\"bucket_name\":\"ml_bucket_info\", \"access_key\":\"brs_access_key\", \"secret_key\":\"brs_secret_key\", \"ttl\":ttl}
	*/
	public int createBucket(String bucket_info)
	{
		if(bucket_info == null)
			return -1;
		return create_bucket(bucket_info, ptmlh);
	}

	/*
		{\"bucket_name\":\"ml_bucket_info\", \"access_key\":\"brs_access_key\", \"secret_key\":\"brs_secret_key\"}
	*/
	public void setBucket(String bucket_info)
	{
		if(bucket_info == null)
		{
			System.out.println("bucket_info is null");
			return;
		}
		set_bucket(bucket_info, ptmlh);
	}

	/*
		arguments are self explantory
	*/
	public long uploadFile(String key, String path, InsertOptions flag)
	{
		if(key == null || path == null)
			return -1;
		return upload_file(key, path, (short)flag.ordinal(), ptmlh);
	}

	public long uploadFile(String bucketInfo, String key, String path, InsertOptions flag)
	{
		if(bucketInfo == null || key == null || path == null)
			return -1;
		return upload_file_bucket(bucketInfo, key, path, (short)flag.ordinal(), ptmlh);
	}

	/*
		depends on what to train.
		pls see wiki [ BangDB-Core at github ]
	*/
	public int trainModel(String req)
	{
		if(req == null)
			return -1;
		return train_model(req, ptmlh);
	}

	/*
		req = {schema-name:, stream-name:, duration_sec:, file_name:, bucket_name:, model_name:}
	*/	
	public long uploadStreamDataForTrain(String req)
	{
		if(req == null)
			return -1;
		return upload_stream_data_for_train(req, ptmlh);
	}

	/*
		req = {\"account_id\":\"ACCOUNTKEY\", \"model_name\" :\"my_model1\"}
	*/
	public String getModelStatus(String req)
	{
		if(req == null)
			return null;
		return get_model_status(req, ptmlh);
	}
	public int setModelStatus(String req)
	{
		if(req == null)
			return -1;
		return set_model_status(req, ptmlh);
	}

	/*
		req = {\"account_id\":\"ACCOUNTKEY\", \"model_name\" :\"my_model1\"}
	*/
	public int delModel(String req)
	{
		if(req == null)
			return -1;
		return del_model(req, ptmlh);
	}

	/*
		req = {\"account_id\":\"ACCOUNTKEY\", \"model_name\" :\"my_model1\"}
	*/
	public int delTrainRequest(String req)
	{
		if(req == null)
			return -1;
		return del_train_request(req, ptmlh);
	}

	/*
		depends on what to train.
		pls see wiki [ BangDB-Core at github ]
	*/
	public String predict(String req)
	{
		if(req == null)
			return null;
		return predict(req, ptmlh);
	}

	/*
		async cal for predict
		use it for pred in files only
	*/
	public int predict_async(String req)
	{
		if(req == null)
			return -1;
		return predict_async(req, ptmlh);
	}

	public String getModelPredStatus(String req)
	{
		if(req == null)
			return null;
		return get_model_pred_status(req, ptmlh);
	}

	public int delPredRequest(String req)
	{
		if(req == null)
			return -1;
		return del_pred_request(req, ptmlh);
	}

	/*
		req = {\"account_id\":\"accid\", \"levk\":\"lk\"}
	*/
	public ResultSet getTrainRequests(String req)
	{
		if(req == null)
			return null;
		return get_training_requests(req, ptmlh);
	}

	/*
		req = {\"account_id\":\"ACCOUNTKEY\", \"model_name\" :\"my_model1\"}
	*/
	public String getRequestDetail(String req)
	{
		if(req == null)
			return null;
		return get_request_detail(req, ptmlh);
	}

	/*
		req = {"access_key":, "secret_key":}
	*/
	public String listBuckets(String req)
	{
		if(req == null)
			return null;
		return list_buckets(req, ptmlh);
	}

	public String listAllBuckets(String req)
	{
		if(req == null)
			return null;
		return list_all_buckets(req, ptmlh);
	}

	/*
		req = {\"bucket_name\":\"ml_bucket_info\", \"access_key\":\"brs_access_key\", \"secret_key\":\"brs_secret_key\"}
		basically bucketInfo
	*/
	public String listObjects(String req, String skey, int listSizeMB)
	{
		if(req == null || skey == null)
			return null;
		return list_objects(req, skey, listSizeMB, ptmlh);
	}

	/*
		req = {\"account_id\":\"accid\"}
	*/
	public long getModelCount(String req)
	{
		if(req == null)
			return -1;
		return get_model_count(req, ptmlh);
	}

	/*
		req = {"bucket_info", "brs_ip", "brs_port"}
	*/
	public int reinitMDM(String req)
	{
		if(req == null)
			return -1;
		return reinit_mdm(req, ptmlh);
	}

	public boolean isBRSLocal()
	{
		return is_brs_local(ptmlh) == 1 ? true : false;
	}

	/*
		bucketInfo = {\"bucket_name\":\"ml_bucket_info\", \"access_key\":\"brs_access_key\", \"secret_key\":\"brs_secret_key\"}
		key is file key and fname is name of the file which will be stored at fpath
	*/
	public long downloadFile(String bucketInfo, String key, String fname, String fpath)
	{
		if(bucketInfo == null || key == null || fname == null || fpath == null)
			return -1;
		return download_file(bucketInfo, key, fname, fpath, ptmlh);
	}																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																					

	/*
		bucketInfo = {\"bucket_name\":\"ml_bucket_info\", \"access_key\":\"brs_access_key\", \"secret_key\":\"brs_secret_key\"}
	*/
	public byte[] getObject(String bucketInfo, String key)
	{
		if(bucketInfo == null || key == null)
			return null;
		return get_object(bucketInfo, key, ptmlh);
	}

	public long countBuckets()
	{
		return count_buckets(ptmlh);
	}

	/*
		bucketInfo = {\"bucket_name\":\"ml_bucket_info\", \"access_key\":\"brs_access_key\", \"secret_key\":\"brs_secret_key\"}
	*/
	public long countObjects(String bucket_info)
	{
		if(bucket_info == null)
			return -1;
		return count_objects(bucket_info, ptmlh);
	}

	/*
		bucketInfo = {\"bucket_name\":\"ml_bucket_info\", \"access_key\":\"brs_access_key\", \"secret_key\":\"brs_secret_key\"}
	*/
	public String countObjectsDetails(String bucket_info)
	{
		if(bucket_info == null)
			return null;
		return count_objects_details(bucket_info, ptmlh);
	}

	/*
		bucketInfo = {\"bucket_name\":\"ml_bucket_info\", \"access_key\":\"brs_access_key\", \"secret_key\":\"brs_secret_key\"}
	*/
	public int countSlices(String bucket_info, String key)
	{
		if(bucket_info == null || key == null)
			return -1;
		return count_slices(bucket_info, key, ptmlh);
	}

	/*
		bucketInfo = {\"bucket_name\":\"ml_bucket_info\", \"access_key\":\"brs_access_key\", \"secret_key\":\"brs_secret_key\"}
	*/
	public int delFile(String bucket_info, String key)
	{
		if(bucket_info == null || key == null)
			return -1;
		return del_file(bucket_info, key, ptmlh);
	}

	/*
		bucketInfo = {\"bucket_name\":\"ml_bucket_info\", \"access_key\":\"brs_access_key\", \"secret_key\":\"brs_secret_key\"}
	*/
	public int delBucket(String bucket_info)
	{
		if(bucket_info == null)
			return -1;
		return del_bucket(bucket_info, ptmlh);
	}
	
	public synchronized void closeMLHelper()
	{
		if(!isActive || ptmlh == 0 || bmlh == null)
			return;
		System.out.println("---------closing ml helper");
		close_mlhelper(ptmlh);
		ptmlh = 0;
		bmlh = null;
		isActive = false;
		return;
	}
}
