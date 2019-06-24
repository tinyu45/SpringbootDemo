package com.tinyu.demo.config;

import org.json.JSONArray;
import org.json.JSONObject;

public class ErrorCode {

	public static final String[] SYSTEM_ERR = new String[] {"1001", "系统错误"};
	public static final String[] SUCCESS = new String[] {"0", "请求成功"};

	
	
	/**
	 * 请求成功
	 * @param jarr
	 * @return
	 */
	public static String ok(JSONArray data) {
		JSONObject resData=new JSONObject();
		resData.putOpt("code", SUCCESS[0]);
		resData.putOpt("msg", SUCCESS[1]);
		resData.putOpt("data", data);
		return resData.toString();
	}

	
	
	/**
	 * 请求失败
	 * @param systemErr
	 * @param string
	 * @return
	 */
	public static String err(String[] err_type, String extra) {
		JSONObject resData=new JSONObject();
		resData.putOpt("code", err_type[0]);
		resData.putOpt("msg", err_type[1]);
		resData.putOpt("extra", extra);
		return resData.toString();
	}

}
