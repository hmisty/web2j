import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.FilterHolder;
import org.mortbay.jetty.servlet.ServletHolder;

import demo.HejServlet;
import demo.HelloFilter;
import demo.HelloServlet;
import demo.IndexServlet;
import demo.StaticServlet;

/** the jetty app of xweb
 * @author Evan/hmisty
 */
public class AppServer {

	public static void main(String[] args) throws Exception {
		int port = 8081;
		Server server = new Server(port);
		Context root = new Context(server, "/", Context.SESSIONS);
		
		root.addFilter(new FilterHolder(new HelloFilter()), "/*", 1);
		root.addServlet(new ServletHolder(new HelloServlet()), "/hello");
		root.addServlet(new ServletHolder(new IndexServlet()), "/");
		root.addServlet(new ServletHolder(new HejServlet()), "/hej/*");
		
		root.addServlet(new ServletHolder(new StaticServlet("./static")), "/static/*");

		server.start();
	}
}

