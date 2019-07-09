package eventCreationJava;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
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
																							// contexte / il crée le
																							// chemin vers le fichier
			Properties properties = new Properties(); // properties clé et valeurs sont des string, il est capable de
														// lire les fichier qui ont un format adapté
			try ( // le fichier pourrait être absent try() = closable
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
			throws ServletException, IOException { // tu recuperes des données et je n'envoie rien
		
		HttpSession session = request.getSession(true);
		String path = request.getPathInfo();
		ObjectMapper mapper = new ObjectMapper();

		if (path.startsWith("/home")) {

			List<Event> events = repository.FindAllEvents();
			System.out.println(events);
			String json = mapper.writeValueAsString(events); // convertir en format json
			setHeaders(response);
			response.setContentType("application/json"); // le type du contenu est du json
			response.setCharacterEncoding("UTF-8");// ce sera écrit en utf8
			response.getWriter().write(json); // on écrit le json dans la réponse

		} else if (path.startsWith("/event")) {

			String[] parts = path.split("/");
			String idEvent = parts[2];
			int id = Integer.parseInt(idEvent);
			Event event = repository.FindEventAndAllActivityByIdEvent(id);
			String json = mapper.writeValueAsString(event); // convertir en format json
			setHeaders(response);
			response.setContentType("application/json"); // le type du contenu est du json
			response.setCharacterEncoding("UTF-8");// ce sera écrit en utf8
			response.getWriter().write(json); // on écrit le json dans la réponse

		} else if (path.startsWith("/activityInscription")) {
			Integer idPerson = (Integer) session.getAttribute("idPerson");
			if (idPerson != null) {
				String[] parts = path.split("/");
				String id = parts[2];
				Integer idActivity = Integer.parseInt(id);
				setHeaders(response);
				try {
					if (repository.CreateNewInscriptionActivity(idPerson, idActivity)) {
						response.setStatus(200);
					} else {
						response.setStatus(400);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else {
				response.setStatus(401);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // tu envoies des données // tu vas créer
		HttpSession session = request.getSession(true);// endroit de memorisation qui dure plusieurs requetes et qui est
														// lié au client (5 clients, chacun aura sa sesssion) (espace
														// memoire)
		try {
			String path = request.getPathInfo();
			ObjectMapper mapper = new ObjectMapper();

			if (path.startsWith("/login")) {

				CreateLoginParameters parameters = mapper.readValue(request.getInputStream(),
						CreateLoginParameters.class);

				// request.getInputStream() = flu de données sur lequel je peux lire
				Person person = repository.connexion(parameters.login, parameters.password);

				if (person != null) {
					session.setAttribute("idPerson", person.getId()); // pour récupérer l'id du responsable
					System.out.println("idPerson " + session.getAttribute("idPerson"));
					
				}

				String json = mapper.writeValueAsString(person); // convertir en format json
				setHeaders(response);
				response.setContentType("application/json"); // le type du contenu est du json
				response.setCharacterEncoding("UTF-8");// ce sera écrit en utf8
				response.getWriter().write(json); // on écrit le json dans la réponse

			} else if (path.startsWith("/createEvent")) {

				Integer idResponsable = (Integer) session.getAttribute("idPerson");
				setHeaders(response);

				if (idResponsable != null) {

					CreateEventParameters parameters = mapper.readValue(request.getInputStream(),
							CreateEventParameters.class);

					Event event = new Event();
					event.setName(parameters.name);
					event.setDescription(parameters.description);
					event.setStartEvent(parameters.startEvent);
					event.setEndEvent(parameters.endEvent);
					event.setIdResponsable(idResponsable);
					event = repository.CreateNewEvent(event);

					session.setAttribute("idEvent", event.getId());

					String json = mapper.writeValueAsString(event); // convertir en format json
					System.out.println(json);
					response.setContentType("application/json"); // le type du contenu est du json
					response.setCharacterEncoding("UTF-8");// ce sera écrit en utf8
					response.getWriter().write(json); // on écrit le json dans la réponse
				} else {
					response.setStatus(401); // si connexion NOK, code erreur (google)
				}
			} else if (path.startsWith("/activity")) {

				Integer idEvent = (Integer) session.getAttribute("idEvent");
				setHeaders(response);

				if (idEvent != null) {
					CreateActivityParameters parameters = mapper.readValue(request.getInputStream(),
							CreateActivityParameters.class);

					Activity activity = new Activity();
					activity.setName(parameters.name);
					activity.setDescription(parameters.description);
					activity.setStartActivity(parameters.startActivity);
					activity.setEndActivity(parameters.endActivity);
					activity.setIdEvent(idEvent);
					activity = repository.CreateNewActivity(activity);

					String json = mapper.writeValueAsString(activity); // convertir en format json
					System.out.println(json);
					response.setContentType("application/json"); // le type du contenu est du json
					response.setCharacterEncoding("UTF-8");// ce sera écrit en utf8
					response.getWriter().write(json); // on écrit le json dans la réponse
				} else {
					response.setStatus(401); // si connexion NOK, code erreur (google)
				}
			} else if (path.startsWith("/register")) {

				setHeaders(response);

				CreatePersonParameters parameters = mapper.readValue(request.getInputStream(),
						CreatePersonParameters.class);

				Person newPerson = new Person();
				newPerson.setName(parameters.name);
				newPerson.setFirstname(parameters.firstname);
				newPerson.setLogin(parameters.login);
				newPerson.setPassword(parameters.password);
				
				newPerson = repository.CreateNewPerson(newPerson);
				
				session.setAttribute("idPerson", newPerson.getId());
				

				String json = mapper.writeValueAsString(newPerson); // convertir en format json
				System.out.println(json);
				response.setContentType("application/json"); // le type du contenu est du json
				response.setCharacterEncoding("UTF-8");// ce sera écrit en utf8
				response.getWriter().write(json); // on écrit le json dans la réponse
				
			} 
				
		} catch (Exception e) {
			response.setStatus(500);
			e.printStackTrace(); // affiche une exception sur le canal d'erreur (console)
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		try {
			String path = request.getPathInfo();
			ObjectMapper mapper = new ObjectMapper();

			if (path.startsWith("/deleteEvent")) {
				
				int idResponsable = (Integer) session.getAttribute("idPerson");
				int idEvent = request.getPathInfo().lastIndexOf("/");
				
				boolean deleted = repository.deleteEventById(idEvent, idResponsable);
				
				response.setContentType("application/json"); // le type du contenu est du json
				response.setCharacterEncoding("UTF-8");// ce sera écrit en utf8
				String.format("{\"deleted\": %s}", deleted);
			}
			
			
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
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Headers", "content-type"); // pour que ca soit accepté par tous les
																			// browser content-type pour le headers
	}

}

// response.addHeader("", "*"); //"la clé""*"n'importe lequel
// Access-Control-Allow-Origin = dns + port
// Access-Control-Allow-Headers = accepte les headers
// Access-Control-Allow-Methods = accepte les differentes methodes, post get ...

// System.out.println(person);
// System.out.println(password);
