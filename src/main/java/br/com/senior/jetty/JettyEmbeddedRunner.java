package br.com.senior.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import br.com.senior.servlet.ServletMock;

public class JettyEmbeddedRunner {

	/**
	 * Inicia o servidor de aplica��o Jetty
	 * 
	 * @param port
	 */
	public void startServer(int port) {
		Server jettyServer = new Server(port);

		try {
			ServletHolder sh = new ServletHolder(ServletMock.class);
					
			ServletContextHandler context = new ServletContextHandler(jettyServer, "/", ServletContextHandler.SESSIONS);
			context.addServlet(sh, "/*");
			
			jettyServer.setHandler(context);
			jettyServer.start();
			jettyServer.join();
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível iniciar o servidor jetty.", e);
		}

	}
}
