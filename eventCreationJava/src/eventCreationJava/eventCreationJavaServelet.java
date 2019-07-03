package eventCreationJava;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;



@WebServlet("/*")
public class eventCreationJavaServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected EventRepositoryImpl repository;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		try {
			Class.forName("org.postgresql.Driver"); // charger la class (driver) postgres
			String path = getServletContext().getRealPath("/WEB-INF/database.properties"); //je demande le chemin au contexte / il crée le chemin vers le fichier
			Properties properties = new Properties(); // properties clé et valeurs sont des string, il est capable de lire les fichier qui ont un format adapté
			try ( // le fichier pourrait être absent try() = closable
					java.io.InputStream in = new FileInputStream(path); // objet capable de lire le fichier
			) {
				properties.load(in); // va aller chercher le contenu du fichier pour remplir
			}
			String url = properties.getProperty("database.url");
			String user = properties.getProperty("database.user");
			String password = properties.getProperty("database.password");
			
			repository = new EventRepositoryImpl(user, password, url);
		} catch(Exception e) {
			throw new ServletException(e);
		}
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("eventCreationJavaServelet.doGet()");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("eventCreationJavaServelet.doPost()");
		
		String path = request.getPathInfo();
		ObjectMapper mapper= new ObjectMapper();
		
		CreateLoginParameters parameters = mapper.readValue(request.getInputStream(), CreateLoginParameters.class);
		
		System.out.println(parameters);
				// request.getInputStream() = flu de données sur lequel je peux lire
		Person person = repository.connexion(parameters.login, parameters.password);
		
		String json = mapper.writeValueAsString(person); // convertir en format json 
		
		response.setContentType("application/json"); // le type du contenu est du json
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");// ce sera écrit en utf8
		response.getWriter().write(json); // on écrit le json dans la réponse
		
		setHeaders(response);
		
		//response.addHeader("", "*"); //"la clé""*"n'importe lequel
		// Access-Control-Allow-Origin = dns + port
		// Access-Control-Allow-Headers = accepte les headers 
		// Access-Control-Allow-Methods = accepte les differentes methodes, post get ...
		
		//System.out.println(person);
		//System.out.println(password);
		
	}
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // est ce que le client a le droit de faire un post
		super.doOptions(request, response);
		setHeaders(response);
	}
	
	private void setHeaders( HttpServletResponse response ) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", "*");
	}

}
