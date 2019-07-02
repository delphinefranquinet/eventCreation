package eventCreationJava;

import java.io.IOException;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/json/*")
public class eventCreationJavaServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected EventRepositoryImpl repository;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("eventCreationJavaServelet.init()");
		try {
			Class.forName("org.postgresql.Driver");
			// where is "database.properties"  ?
			String path = getServletContext().getRealPath("/WEB-INF/database.properties");
			System.out.println("Path = " + path);
			
			// load configuration properties from file
			java.util.Properties properties = new java.util.Properties();
			try (
					java.io.InputStream in = new java.io.FileInputStream(path);
			) {
				properties.load(in);
			}
			String url = properties.getProperty("database.url");
			String user = properties.getProperty("database.user");
			String password = properties.getProperty("database.password");
			
			// instanciate repository
			repository = new EventRepositoryImpl(url, user, password);
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
