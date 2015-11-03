package br.com.senior.parser;

import java.util.Map;

public class Call {

	private Map<String, String> params;
	private String body;
	private long lastExecution;

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setLastExecution(long lastExecution) {
		this.lastExecution = lastExecution;
	}

	public long getLastExecution() {
		return this.lastExecution;
	}
}
