package com.bangdb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataQuery {
    JSONArray arr = new JSONArray();
    JSONObject result;
    boolean firstQuery = true;

    private static JSONObject createFilter(String filterKey, ScanOperator cmpOp, String filterVal)
    {
        JSONObject obj = new JSONObject();
        try {
            obj.put("key", filterKey);
            obj.put("cmp_op", cmpOp.ordinal());
            obj.put("val", filterVal);
        } catch (JSONException e) {
            System.out.println("JSON Exception occured\n");
            e.printStackTrace();
        }
        return obj;
    }
    private static JSONObject createFilter(String filterKey, ScanOperator cmpOp, int filterVal)
    {
        JSONObject obj = new JSONObject();
        try {
            obj.put("key", filterKey);
            obj.put("cmp_op", cmpOp.ordinal());
            obj.put("val", filterVal);
        } catch (JSONException e) {
            System.out.println("JSON Exception occured\n");
            e.printStackTrace();
        }
        return obj;
    }
    private static JSONObject createFilter(String filterKey, ScanOperator cmpOp, long filterVal)
    {
        JSONObject obj = new JSONObject();
        try {
            obj.put("key", filterKey);
            obj.put("cmp_op", cmpOp.ordinal());
            obj.put("val", filterVal);
        } catch (JSONException e) {
            System.out.println("JSON Exception occured\n");
            e.printStackTrace();
        }
        return obj;
    }
    private static JSONObject createFilter(String filterKey, ScanOperator cmpOp, double filterVal)
    {
        JSONObject obj = new JSONObject();
        try {
            obj.put("key", filterKey);
            obj.put("cmp_op", cmpOp.ordinal());
            obj.put("val", filterVal);
        } catch (JSONException e) {
            System.out.println("JSON Exception occured\n");
            e.printStackTrace();
        }
        return obj;
    }

    private static JSONObject createFilter(String stringOfWords)
	{
		JoinType jOp = JoinType.JO_OR;
		JSONObject obj = new JSONObject();
		try {
			obj.put("match_words", stringOfWords);
			obj.put("joinop", JoinType.toInt(jOp.ordinal()));
		} catch (JSONException e) {
			System.out.println("JSON Exception occured");
			e.printStackTrace();
		}
		return obj;
	}

    private static JSONObject createFilter(String stringOfWords, JoinType jOp)
	{
		JSONObject obj = new JSONObject();
		try {
			obj.put("match_words", stringOfWords);
			obj.put("joinop", JoinType.toInt(jOp.ordinal()));
		} catch (JSONException e) {
			System.out.println("JSON Exception occured");
			e.printStackTrace();
		}
		return obj;
	}

    private void addQueryFilter(JoinType joinOp, JSONObject filterObj)
    {
        if (!firstQuery) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("joinop", JoinType.toInt(joinOp.ordinal()));
                arr.put(arr.length(), obj);
            } catch (JSONException e) {
                System.out.println("JSON Exception occured\n");
                e.printStackTrace();
            }
        } else {
            firstQuery = false;
        }
        
        try {
            arr.put(arr.length(), filterObj);
        } catch (JSONException e) {
            System.out.println("JSON Exception occured\n");
            e.printStackTrace();
        }
    }

    public void addQuery(String filterKey, ScanOperator cmpOp, String filterVal, JoinType joinOp)
    {
    	JSONObject obj = createFilter(filterKey, cmpOp, filterVal);
    	addQueryFilter(joinOp, obj);
    }
    public void addQuery(String filterKey, ScanOperator cmpOp, long filterVal, JoinType joinOp)
    {
    	JSONObject obj = createFilter(filterKey, cmpOp, filterVal);
    	addQueryFilter(joinOp, obj);
    }
    public void addQuery(String filterKey, ScanOperator cmpOp, double filterVal, JoinType joinOp)
    {
    	JSONObject obj = createFilter(filterKey, cmpOp, filterVal);
    	addQueryFilter(joinOp, obj);
    }

	// following 3 apis are deprecated
    public void addQueryFilter(String filterKey, ScanOperator cmpOp, String filterVal, JoinType joinOp)
    {
    	JSONObject obj = createFilter(filterKey, cmpOp, filterVal);
    	addQueryFilter(joinOp, obj);
    }
    public void addQueryFilter(String filterKey, ScanOperator cmpOp, long filterVal, JoinType joinOp)
    {
    	JSONObject obj = createFilter(filterKey, cmpOp, filterVal);
    	addQueryFilter(joinOp, obj);
    }
    public void addQueryFilter(String filterKey, ScanOperator cmpOp, double filterVal, JoinType joinOp)
    {
    	JSONObject obj = createFilter(filterKey, cmpOp, filterVal);
    	addQueryFilter(joinOp, obj);
    }

    public void addGeoQuery(String query_json)
    {
	JSONObject obj = new JSONObject();
	try 
	{
		obj.put("geoQuery", query_json);
                arr.put(arr.length(), obj);
        } 
	catch (JSONException e) 
	{
                System.out.println("JSON Exception occured\n");
                e.printStackTrace();
        }
    }

    public String getQuery()
    {
        result = new JSONObject();
        try {
            result.put("query", arr);
        } catch (JSONException e) {
            System.out.println("JSON Exception occured\n");
            e.printStackTrace();
        }
        return result.toString();
    }
    public void reset()
    {
    	arr = new JSONArray();
    	result = null;
    	firstQuery = true;
    }
    public DataQuery()
    {
	
    }
}
