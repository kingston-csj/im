package com.kingston.im.http;

import java.util.HashMap;
import java.util.Map;

public class HttpResult {
	
	private byte code;
	
	private Map<String, String> params = new HashMap<>();
	
	public static HttpResult valueOf(byte code) {
		HttpResult result = new HttpResult();
		result.code = code;
		return result;
	}

	public byte getCode() {
		return code;
	}

	public void setCode(byte code) {
		this.code = code;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

}
