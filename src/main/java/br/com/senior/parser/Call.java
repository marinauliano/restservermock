package br.com.senior.parser;

import java.util.Map;

public class Call {

	private Map<String, String> params;
	private Map<String, String> body;
	private long lastExecution;

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public Map<String, String> getBody() {
		return body;
	}

	public void setBody(Map<String, String> body) {
		this.body = body;
	}

	public void setLastExecution(long lastExecution) {
		this.lastExecution = lastExecution;
	}

	public long getLastExecution() {
		return this.lastExecution;
	}
}
