package be.afelio.teamZDRR.eventCreationJava.servlet;

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

import be.afelio.teamZDRR.eventCreationJava.beans.Activity;
import be.afelio.teamZDRR.eventCreationJava.beans.CreateActivityParameters;
import be.afelio.teamZDRR.eventCreationJava.beans.CreateEventParameters;
import be.afelio.teamZDRR.eventCreationJava.beans.CreateLoginParameters;
import be.afelio.teamZDRR.eventCreationJava.beans.CreatePersonParameters;
import be.afelio.teamZDRR.eventCreationJava.beans.Event;
import be.afelio.teamZDRR.eventCreationJava.beans.EventsAndPersons;
import be.afelio.teamZDRR.eventCreationJava.beans.Person;
import be.afelio.teamZDRR.eventCreationJava.impl.EventRepositoryImpl;

@WebServlet("/*")
public class EventCreationJavaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected EventRepositoryImpl repository;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {
			Class.forName("org.postgresql.Driver"); // charger la class (driver) postgres
			String path = getServletContext().getRealPath("/WEB-INF/database.properties"); // je demande le chemin au
																							// contexte / il crï¿½e le
																							// chemin vers le fichier
			Properties properties = new Properties(); // properties clï¿½ et valeurs sont des string, il est capable de
														// lire les fichier qui ont un format adaptï¿½
			try ( // le fichier pourrait ï¿½tre absent try() = closable
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
			throws ServletException, IOException { // tu recuperes des donnï¿½es et je n'envoie rien

		HttpSession session = request.getSession(true);
		String path = request.getPathInfo();
		ObjectMapper mapper = new ObjectMapper();

		if (path.startsWith("/home")) {

			List<Event> events = repository.findAllEvents();
			List<Person> persons = repository.findAllPerson();
			EventsAndPersons eventsAndPersons = new EventsAndPersons();
			eventsAndPersons.setEvents(events);
			eventsAndPersons.setPersons(persons);
			System.out.println(eventsAndPersons);
			String json = mapper.writeValueAsString(eventsAndPersons);
			setHeaders(response);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

		} else if (path.startsWith("/event")) {

			String[] parts = path.split("/");
			String idEvent = parts[2];
			int id = Integer.parseInt(idEvent);
			Event event = repository.findEventAndAllActivityByIdEvent(id);
			String json = mapper.writeValueAsString(event);
			setHeaders(response);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

		} else if (path.startsWith("/clientSpace")) {

			String[] parts = path.split("/");
			String idResponsable = parts[2];
			int id = Integer.parseInt(idResponsable);
			List<Event> events = repository.findEventAndAllActivityByIdResponsable(id);
			String json = mapper.writeValueAsString(events);
			setHeaders(response);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

		} else if (path.startsWith("/search")) {

			String[] parts = path.split("/");
			String EventName = parts[2];
			List<Event> events = repository.findEventAndAllActivityByEventName(EventName);
			String json = mapper.writeValueAsString(events);
			setHeaders(response);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

		} else if (path.startsWith("/search")) {
			// TODO

		} else if (path.startsWith("/allActivityInscription")) {

			Integer idPerson = (Integer) session.getAttribute("idPerson");

			List<Activity> listActivities = repository.findAllActivityByInscription(idPerson);
			String json = mapper.writeValueAsString(listActivities);
			setHeaders(response);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

		} else {
			response.setStatus(401);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // tu envoies des donnï¿½es // tu vas crï¿½er
		HttpSession session = request.getSession(true);// endroit de memorisation qui dure plusieurs requetes et qui est
														// liï¿½ au client (5 clients, chacun aura sa sesssion) (espace
														// memoire)
		try {
			String path = request.getPathInfo();
			ObjectMapper mapper = new ObjectMapper();

			if (path.startsWith("/login")) {

				CreateLoginParameters parameters = mapper.readValue(request.getInputStream(),
						CreateLoginParameters.class);

				Person person = repository.connexion(parameters.email, parameters.password);

				if (person != null) {
					session.setAttribute("idPerson", person.getId()); // pour récupérer l'id du responsable
					System.out.println("idPerson " + session.getAttribute("idPerson"));

				}

				String json = mapper.writeValueAsString(person); // convertir en format json
				setHeaders(response);
				response.setContentType("application/json"); // le type du contenu est du json
				response.setCharacterEncoding("UTF-8");// ce sera ï¿½crit en utf8
				response.getWriter().write(json); // on ï¿½crit le json dans la rï¿½ponse

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
					event.setPlace(parameters.place);
					event = repository.createNewEvent(event);

					String json = mapper.writeValueAsString(event);
					System.out.println(json);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(json);
				} else {
					response.setStatus(401);
				}
			} else if (path.startsWith("/activity/")) {

				Integer idResponsable = (Integer) session.getAttribute("idPerson");
				String[] parts = path.split("/");
				String id = parts[2];
				int idEvent = Integer.parseInt(id);
				System.out.println(idEvent);
				setHeaders(response);

				if (idResponsable != null) {
					CreateActivityParameters parameters = mapper.readValue(request.getInputStream(),
							CreateActivityParameters.class);

					Activity activity = new Activity();
					activity.setName(parameters.name);
					activity.setDescription(parameters.description);
					activity.setStartActivity(parameters.startActivity);
					activity.setEndActivity(parameters.endActivity);
					activity.setIdEvent(idEvent);
					activity = repository.createNewActivity(activity);

					String json = mapper.writeValueAsString(activity);
					System.out.println(json);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(json);
				} else {
					response.setStatus(401);
				}
			} else if (path.startsWith("/register")) {

				setHeaders(response);

				CreatePersonParameters parameters = mapper.readValue(request.getInputStream(),
						CreatePersonParameters.class);

				Person newPerson = new Person();
				newPerson.setName(parameters.name);
				newPerson.setFirstname(parameters.firstname);
				newPerson.setEmail(parameters.email);
				newPerson.setPassword(parameters.password);

				newPerson = repository.createNewPerson(newPerson);

				session.setAttribute("idPerson", newPerson.getId());

				String json = mapper.writeValueAsString(newPerson);
				System.out.println(json);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);

			} else if (path.startsWith("/activityInscription")) {
				Integer idPerson = (Integer) session.getAttribute("idPerson");
				if (idPerson != null) {
					String[] parts = path.split("/");
					String id = parts[2];
					Integer idActivity = Integer.parseInt(id);
					setHeaders(response);

					boolean activityInscription = repository.createNewInscriptionActivity(idPerson, idActivity);

					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					String json = mapper.writeValueAsString(activityInscription);
					response.getWriter().write(json);

					/*
					 * try { if (repository.createNewInscriptionActivity(idPerson, idActivity)) {
					 * response.getWriter().write("true"); } else {
					 * response.getWriter().write("false"); }
					 * 
					 * } catch (SQLException e) { e.printStackTrace(); }
					 */
				}
			}

		} catch (Exception e) {
			response.setStatus(500);
			e.printStackTrace(); // affiche une exception sur le canal d'erreur (console)
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("EventCreationJavaServlet.doPut()");
		HttpSession session = request.getSession(true);

		try {
			String path = request.getPathInfo();
			ObjectMapper mapper = new ObjectMapper();

			if (path.startsWith("/updateEvent")) {
				setHeaders(response);
				CreateEventParameters parameters = mapper.readValue(request.getInputStream(),
						CreateEventParameters.class);

				int idResponsable = (Integer) session.getAttribute("idPerson");

				Event event = new Event();
				event.setName(parameters.name);
				event.setDescription(parameters.description);
				event.setStartEvent(parameters.startEvent);
				event.setEndEvent(parameters.endEvent);
				event.setIdResponsable(idResponsable);
				event.setPlace(parameters.place);
				event.setId(parameters.id);
				event.setActivities(parameters.activities);
				boolean confirmation = repository.updateEventAndActivitiesByIdEvent(idResponsable, event);

				String json = mapper.writeValueAsString(confirmation);
				System.out.println(json);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);

			} else if (path.startsWith("/updateOneActivity")) {
				setHeaders(response);
				CreateActivityParameters parameters = mapper.readValue(request.getInputStream(),
						CreateActivityParameters.class);
				int idResponsable = (Integer) session.getAttribute("idPerson");

				String[] parts = path.split("/");
				String id = parts[2];
				int idActivity = Integer.parseInt(id);

				Activity activity = new Activity();
				activity.setName(parameters.name);
				activity.setDescription(parameters.description);
				activity.setStartActivity(parameters.startActivity);
				activity.setEndActivity(parameters.endActivity);
				Activity UpdateActivity = repository.updateOneActivityByIdEvent(idActivity, activity);

				String json = mapper.writeValueAsString(UpdateActivity);
				System.out.println(json);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);

			}

		} catch (Exception e) {
			response.setStatus(500);
			e.printStackTrace(); // affiche une exception sur le canal d'erreur (console)
		}

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String path = request.getPathInfo();
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("EventCreationJavaServlet.doDelete()");

			if (path.startsWith("/deleteEvent")) {
				setHeaders(response);

				String[] parts = path.split("/");
				String idEvent = parts[2];
				int id = Integer.parseInt(idEvent);

				// int idEvent = request.getPathInfo().lastIndexOf("/");
				System.out.println(id);

				boolean deleted = repository.deleteEventByIdEvent(id);

				response.setContentType("application/json"); // le type du contenu est du json
				response.setCharacterEncoding("UTF-8");// ce sera ï¿½crit en utf8
				// String.format("{\"deleted\": %s}", deleted);
				String json = mapper.writeValueAsString(deleted);
				response.getWriter().write(json);

			} else if (path.startsWith("/deleteActivity")) {

				setHeaders(response);

				String[] parts = path.split("/");
				String idActivity = parts[2];
				int id = Integer.parseInt(idActivity);
				System.out.println(id);
				boolean deleted = repository.deleteOneActivityByIdActivity(id);

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				String json = mapper.writeValueAsString(deleted);
				response.getWriter().write(json);

			} else if (path.startsWith("/deleteInscription")) {

				setHeaders(response);

				String[] parts = path.split("/");
				String idInscription = parts[2];
				int id = Integer.parseInt(idInscription);
				System.out.println(id);
				boolean deleted = repository.deleteOneInscriptionByIdInscription(id);

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				String json = mapper.writeValueAsString(deleted);
				response.getWriter().write(json);

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
		response.addHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Headers", "content-type"); // pour que ca soit acceptï¿½ par tous les
																			// browser content-type pour le headers
	}

}

// response.addHeader("", "*"); //"la clï¿½""*"n'importe lequel
// Access-Control-Allow-Origin = dns + port
// Access-Control-Allow-Headers = accepte les headers
// Access-Control-Allow-Methods = accepte les differentes methodes, post get ...

// System.out.println(person);
// System.out.println(password);
