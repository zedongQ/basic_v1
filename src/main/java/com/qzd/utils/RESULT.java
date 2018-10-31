package com.qzd.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 返回数据
 * 
 */
public class RESULT extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public RESULT() {
		put("code", 0);
	}
	
	public static RESULT error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static RESULT error(String msg) {
		return error(500, msg);
	}
	
	public static RESULT error(int code, String msg) {
		RESULT r = new RESULT();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static RESULT ok(String msg) {
		RESULT r = new RESULT();
		r.put("msg", msg);
		return r;
	}
	
	public static RESULT ok(Map<String, Object> map) {
		RESULT r = new RESULT();
		r.putAll(map);
		return r;
	}
	
	public static RESULT ok() {
		return new RESULT();
	}

	@Override
	public RESULT put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	
	public static String page(String page) {
		return page;
	}
	
	public String aes() {
		try {
			return AesCryptUtil.encrypt(JSON.toJSONString(this));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
