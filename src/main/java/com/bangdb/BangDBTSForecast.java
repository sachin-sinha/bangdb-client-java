package com.bangdb;

import org.json.JSONException;
import org.json.JSONObject;

public class BangDBTSForecast
{
	private native int forecast_data(long ptbmlh, String base_path, String schema_name, String model_name, int gran, int aggr_type, int lag, int offt, String tsfields1, String tsfields2, String tsfields3, int pos1, int pos2, int pos3, int dtypes1, int dtypes2, int dtypes3, int dim, String ifile, String inp_fmt, boolean ignore_aggr);
	String basePath = "/tmp";
	String schemaName = null;
	String modelName = null;
	int gran = 1;
	int aggr = 1;
	int lag = 3;
	int offt = 0;
	boolean rollup = true;
	String[] fields = null;
	int[] pos = null;	
	int[] dtype = null;
	int dim = 0;
	BangDBMLHelper bmlh = null;

	public BangDBTSForecast(BangDBMLHelper mlhelper)
	{
		bmlh = mlhelper;
	}
	public int trainForecastModel(String iFile, String fileType)
	{
		if(bmlh == null || schemaName == null || modelName == null || iFile == null || fileType == null || fields == null || pos == null || dtype == null)
		{
			System.out.println("required params can't be null");
			return -1;
		}
		
		return dim == 2 ? forecast_data(bmlh.ptmlh, basePath, schemaName, modelName, gran, aggr, lag, offt, fields[0], fields[1], null, pos[0], pos[1], 0, dtype[0], dtype[1], 0, dim, iFile, fileType, !rollup) :
			forecast_data(bmlh.ptmlh, basePath, schemaName, modelName, gran, aggr, lag, offt, fields[0], fields[1], fields[2], pos[0], pos[1], pos[2], dtype[0], dtype[1], dtype[2], dim, iFile, fileType, !rollup);
	}
	public String forecast(String data, String fileType, boolean isFile)
	{
		if(bmlh == null || data == null || fileType == null || schemaName == null || modelName == null)
			return null;
		JSONObject obj = new JSONObject();
		try {
			obj.put("schema-name", schemaName);
			obj.put("model_name", modelName);
			obj.put("algo_type", "SVM");
			obj.put("expected_format", "SVM");
			obj.put("input_format", fileType);
			obj.put("data_type", isFile ? 1 : 2);
			obj.put("attr_type", 3);
			obj.put("data", data);			
		} catch (JSONException e) {
			System.out.println("JSON Exception occured\n");
			e.printStackTrace();
		}

		String req = obj.toString();
		return bmlh.predict(req);
	}
	public void setFieldInfo(String[] fields, int[] pos, BangDBTSDataType[] dType)
	{
		if(fields == null || pos == null || dType == null)
			return;
		if((fields.length != pos.length) || (fields.length != dType.length) || (dType.length != pos.length))
			return;
		dim = fields.length;
		if(dim < 2 || dim > 3)
			return;

		dtype = new int[dim];
		for(int i = 0; i < dim; i++)
			dtype[i] = (int)BangDBTSDataType.toInt(dType[i].ordinal());
		this.fields = fields;
		this.pos = pos;
	}
	public void setBasePath(String basePath)
	{
		this.basePath = basePath;
	}
	public void setSchemaName(String schemaName)
	{
		this.schemaName = schemaName;
	}
	public void setModelName(String modelName)
	{
		this.modelName = modelName;
	} 
	public void setGran(BangDBTSDataGran gran)
	{
		this.gran = (int)gran.ordinal();
	}
	public void setAggrType(BangDBDataAggrType aggrType)
	{
		this.aggr = (int)aggrType.ordinal();
	}
	public void setLag(int lag)
	{
		this.lag = lag;
	}
	public void setOffset(int offt)
	{
		this.offt = offt;
	}
	public void setShouldRollup(boolean rollup)
	{
		this.rollup = rollup;
	}
}
