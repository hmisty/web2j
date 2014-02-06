package demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

public class IndexServlet extends HttpServlet {

	class Feature {
		String description;
		
		Feature(String description) {
			this.description = description;
		}
	}
	
	HashMap<String, Object> data = new HashMap<String, Object>();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		MustacheFactory mf = new DefaultMustacheFactory();
		Mustache m = mf.compile("index.mustache");
	
		data.put("name", "Mustache");
		data.put("price", "3.0");
		data.put("features", Arrays.asList(new Feature("Stainless steel"),
				new Feature("Front camera")));
		
		m.execute(res.getWriter(), data);
	}

}

