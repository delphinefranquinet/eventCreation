package eventCreationJava;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			String path = getServletContext().getRealPath("/WEB-INF/database.properties"); // je demande le chemin au
																							// contexte / il cr�e le
																							// chemin vers le fichier
			Properties properties = new Properties(); // properties cl� et valeurs sont des string, il est capable de
														// lire les fichier qui ont un format adapt�
			try ( // le fichier pourrait �tre absent try() = closable
					java.io.InputStream in = new FileInputStream(path); // objet capable de lire le fichier
			) {
				properties.load(in); // va aller chercher le contenu du fichier pour remplir
			}
			String url = properties.getProperty("database.url");
			String user = properties.getProperty("database.user");
			String password = properties.getProperty("database.password");

			repository = new EventRepositoryImpl(user, password, url);
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // tu recuperes des donn�es et je n'envoie rien

		String path = request.getPathInfo();
		ObjectMapper mapper = new ObjectMapper();

		if (path.startsWith("/event")) {

			List<Event> events = repository.FindAllEvents();
			System.out.println(events);

			String json = mapper.writeValueAsString(events); // convertir en format json
			setHeaders(response);
			response.setContentType("application/json"); // le type du contenu est du json
			response.setCharacterEncoding("UTF-8");// ce sera �crit en utf8
			response.getWriter().write(json); // on �crit le json dans la r�ponse

			/*
			 * request.setAttribute("list", events);// add elements to req
			 * request.getRequestDispatcher("/event.jsp").forward(request, response);
			 */

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // tu envoies des donn�es // tu vas cr�er
		HttpSession session = request.getSession(true);// endroit de memorisation qui dure plusieurs requetes et qui est
														// li� au client (5 clients, chacun aura sa sesssion) (espace
														// memoire)

		try {
			String path = request.getPathInfo();
			ObjectMapper mapper = new ObjectMapper();

			if (path.startsWith("/login")) {

				CreateLoginParameters parameters = mapper.readValue(request.getInputStream(),
						CreateLoginParameters.class);

				// request.getInputStream() = flu de donn�es sur lequel je peux lire
				Person person = repository.connexion(parameters.login, parameters.password);

				if (person != null) {
					session.setAttribute("idResponsable", person.getId()); // pour r�cup�rer l'id du responsable
				}

				String json = mapper.writeValueAsString(person); // convertir en format json
				setHeaders(response);
				response.setContentType("application/json"); // le type du contenu est du json
				response.setCharacterEncoding("UTF-8");// ce sera �crit en utf8
				response.getWriter().write(json); // on �crit le json dans la r�ponse

			} else if (path.startsWith("/createEvent")) {

				CreateEventParameters parameters = mapper.readValue(request.getInputStream(),
						CreateEventParameters.class);
				Integer idResponsable = (Integer) session.getAttribute("idResponsable");

				if (idResponsable != null) {

					Event event = new Event();
					event.setName(parameters.name);
					event.setDescription(parameters.description);
					event.setStartEvent(parameters.startEvent);
					event.setEndEvent(parameters.endEvent);
					event.setIdResponsable(idResponsable);

					event = repository.CreateNewEvent(event);

					String json = mapper.writeValueAsString(event); // convertir en format json
					System.out.println(json);
					setHeaders(response);
					response.setContentType("application/json"); // le type du contenu est du json
					response.setCharacterEncoding("UTF-8");// ce sera �crit en utf8
					response.getWriter().write(json); // on �crit le json dans la r�ponse
				} else {
					response.setStatus(401); // si connexion NOK, code erreur (google)
				}
			}
			// response.addHeader("", "*"); //"la cl�""*"n'importe lequel
			// Access-Control-Allow-Origin = dns + port
			// Access-Control-Allow-Headers = accepte les headers
			// Access-Control-Allow-Methods = accepte les differentes methodes, post get ...

			// System.out.println(person);
			// System.out.println(password);

		} catch (Exception e) {
			response.setStatus(500);
			e.printStackTrace(); // affiche une exception sur le canal d'erreur (console)
		}

	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // est ce que le client a le droit de faire un post
		super.doOptions(request, response);
		setHeaders(response);
	}

	private void setHeaders(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", "content-type"); // pour que ca soit accept� par tous les
																			// browser content-type pour le headers
	}

}
