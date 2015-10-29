package br.com.senior.parser;

import java.util.List;

/**
 * Representa JSON de um serviço rest.
 * 
 * @author marina.uliano
 *
 */
public class Service {

	private String method;
	private String url;
	private int timeToExecute;
	private boolean consume;
	private List<Call> call;
	private DefaultReturn defaultReturn;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Call> getCall() {
		return call;
	}

	public void setCall(List<Call> paramBody) {
		this.call = paramBody;
	}

	public boolean isConsume() {
		return consume;
	}

	public void setConsume(boolean consume) {
		this.consume = consume;
	}

	public int getTimeToExecute() {
		return timeToExecute;
	}

	public void setTimeToExecute(int timeToExecute) {
		this.timeToExecute = timeToExecute;
	}

	public DefaultReturn getDefaultReturn() {
		return defaultReturn;
	}

	public void setDefaultReturn(DefaultReturn defaultReturn) {
		this.defaultReturn = defaultReturn;
	}
}
