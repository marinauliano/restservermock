package br.com.senior.jetty;

import br.com.senior.parser.ParserJsonServices;

public class Main {

	public static void main(String[] args) {
		JettyEmbeddedRunner start = new JettyEmbeddedRunner();
		start.startServer(8989);
		
	}
}
