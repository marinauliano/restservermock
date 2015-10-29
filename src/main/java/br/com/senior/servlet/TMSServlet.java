package br.com.senior.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.senior.parser.Call;
import br.com.senior.parser.DefaultReturn;
import br.com.senior.parser.ParserJsonServices;
import br.com.senior.parser.Service;

public class TMSServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ParserJsonServices parser;

	public TMSServlet() {
		parser = new ParserJsonServices();
		parser.loadServices();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath = req.getPathInfo();
		Service restService = parser.getRestService(contextPath);
		if (restService != null) {
			List<Call> callList = restService.getCall();

			Call call = null;
			int count = 0;
			while (callList.size() > count) {
				call = callList.get(count);
				if (compareParams(req, call.getParams())) {
					
					// Timer da execução (caso deseje ter um tempo entre uma chamada e outra)
					int timeToExecute = restService.getTimeToExecute();
					if (call.getLastExecution() > 0 && timeToExecute > 0) {
						if (System.currentTimeMillis() - call.getLastExecution() <= timeToExecute) {
							writeInResponse(resp, 400, "Timeout entre execuções ainda não terminou.");
							break;
						}
					}

					writeInResponse(resp, HttpServletResponse.SC_OK, call.getBody().toString());

					// Caso ele deve ser removido assim que consumido
					if (restService.isConsume()) {
						callList.remove(call);
					} else {
						call.setLastExecution(System.currentTimeMillis());
					}
				}
				count++;
			}

			if (count >= callList.size()) {
				DefaultReturn defaultReturn = restService.getDefaultReturn();
				writeInResponse(resp, defaultReturn.getStatus(), defaultReturn.getBody());
			}
		}
	}

	private boolean compareParams(HttpServletRequest req, Map<String, String> map) {

		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();

		while (iterator.hasNext()) {
			Entry<String, String> next = iterator.next();
			String value = req.getParameter(next.getKey());
			if (value == null || (!value.equalsIgnoreCase(next.getValue()))) {
				return false;
			}
		}
		return true;
	}

	private void writeInResponse(HttpServletResponse resp, int status, String response) throws IOException {
		resp.setStatus(status);
		resp.getWriter().write(response);
		resp.getWriter().flush();
		resp.getWriter().close();
	}

}
