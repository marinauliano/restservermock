package br.com.senior.parser;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class ParserJsonServices {

	private final static String JSON_SERVICE_PATH = "./json-services";
	private Map<String, Service> map = new HashMap<String, Service>();

	public void loadServices() {
		File jsonService = new File(JSON_SERVICE_PATH);

		File[] listFiles = jsonService.listFiles();
		for (File file : listFiles) {
			parserService(file);
		}
	}

	private void parserService(File service) {
		Gson gson = new Gson();
		try {
			
			Service restService = gson.fromJson(new FileReader(service), Service.class);
			map.put(restService.getUrl(), restService);

		} catch (Exception e) {
			throw new RuntimeException("Não foi possível ler o serviço: " + service.getName(), e);
		}

	}

	public Service getRestService(String contextPath) {
		return map.get(contextPath);
	}
}
