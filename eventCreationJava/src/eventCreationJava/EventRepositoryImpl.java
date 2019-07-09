package eventCreationJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class EventRepositoryImpl {

	protected String user;
	protected String password;
	protected String url;

	public EventRepositoryImpl(String user, String password, String url) {
		super();
		this.user = user;
		this.password = password;
		this.url = url;
	}

	public Person connexion(String login, String mot_de_passe) {

		Person person = null;

		String sql = "Select id_person, \"namePerson\", \"firstnamePerson\" From persons Where login = ? AND password = ?";
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql);) {
			query.setString(1, login);
			query.setString(2, mot_de_passe);
			try (ResultSet resultSet = query.executeQuery()) {
				if (resultSet.next()) {

					person = createPerson(resultSet);

				}
			}
		} catch (java.sql.SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return person;
	}

	protected Person createPerson(ResultSet rs) throws SQLException {

		Person person = new Person();
		person.setId(rs.getInt("id_person"));
		person.setName(rs.getString("namePerson"));
		person.setFirstname(rs.getString("firstnamePerson"));
		return person;
	}

	public Person CreateNewPerson(Person newPerson) {

		String sql = "INSERT INTO persons Values (DEFAULT, ?, ?, ?, ?)";
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

			connection.setAutoCommit(false);// pour etre certain que les 2 aboutissent, sinon que les 2 échouent.
			query.setString(1, newPerson.getName());
			query.setString(2, newPerson.getFirstname());
			query.setString(3, newPerson.getLogin());
			query.setString(4, newPerson.getPassword());
			query.executeUpdate(); // insert/update/delete
			try (ResultSet resultSet = query.getGeneratedKeys() // pour récupérer l'id qui est en autoincrement, ne pas
			// oublier Statement.RETURN_GENERATED_KEYS)
			) {
				if (resultSet.next()) {
					int id = resultSet.getInt(1);
					// System.out.println("bonjour" + id);
					newPerson.setId(id);
				}
			}
			connection.commit();

		} catch (java.sql.SQLException sqle) {
			throw new RuntimeException(sqle);
		}
		newPerson.setPassword(null);

		return newPerson;
	}

	public Event CreateNewEvent(Event newEvent) {

		// Timestamp timestamp = Timestamp.valueOf(localDateTime); Convertir
		// localDateTime en Timestamp
		String sql = "insert into \"Events\" values (default, ?, ?, ?, ?, ? )";
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

			connection.setAutoCommit(false);// pour etre certain que les 2 aboutissent, sinon que les 2 échouent.
			query.setString(1, newEvent.getName());
			query.setString(2, newEvent.getDescription());
			query.setTimestamp(3, Timestamp.valueOf(newEvent.getStartEvent())); // Timestamp.valueOf(startEvent)
			query.setTimestamp(4, Timestamp.valueOf(newEvent.getEndEvent())); // Timestamp.valueOf(endEvent)
			query.setInt(5, newEvent.getIdResponsable());
			query.executeUpdate(); // insert/update/delete

			try (ResultSet resultSet = query.getGeneratedKeys() // pour récupérer l'id qui est en autoincrement, ne pas
																// oublier Statement.RETURN_GENERATED_KEYS)
			) {
				if (resultSet.next()) {
					int id = resultSet.getInt(1);
					// System.out.println("bonjour" + id);
					newEvent.setId(id);
				}
			}
			connection.commit();

		} catch (java.sql.SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return newEvent;
	}

	public Activity CreateNewActivity(Activity newActivity) {

		String sql = "INSERT INTO \"Activities\" Values (default, ?, ?, ?, ?, ?)";
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			// connection.setAutoCommit(true);

			connection.setAutoCommit(false);
			query.setString(1, newActivity.getName());
			query.setString(2, newActivity.getDescription());
			query.setTimestamp(3, Timestamp.valueOf(newActivity.getStartActivity()));
			query.setTimestamp(4, Timestamp.valueOf(newActivity.getEndActivity()));
			query.setInt(5, newActivity.getIdEvent());
			query.executeUpdate(); // insert/update/delete

			try (ResultSet resultSet = query.getGeneratedKeys() // pour récupérer l'id qui est en autoincrement, ne pas
																// oublier Statement.RETURN_GENERATED_KEYS)
			) {
				if (resultSet.next()) {
					int id = resultSet.getInt(1);
					// System.out.println("bonjour" + id);
					newActivity.setId(id);
				}
			}
			connection.commit();

		} catch (java.sql.SQLException sqle) {
			throw new RuntimeException(sqle);
		}
		return newActivity;
	}

	public List<Event> FindAllEvents() {

		List<Event> events = new ArrayList<>();
		String sql = "Select id_event, \"eventName\", description, \"dateDebut\", \"dateFin\", id_person From \"Events\"";

		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql)) {

			try (ResultSet rs = query.executeQuery()) {

				while (rs.next()) {
					Event event = new Event();
					event.setId(rs.getInt(1));
					event.setName(rs.getString(2));
					event.setDescription(rs.getString(3));

					Timestamp timestamp = rs.getTimestamp(4); // récupérer le timestamp
					LocalDateTime localDateTime = timestamp.toLocalDateTime(); // convertir Timestamp en localDateTime
					event.setStartEvent(localDateTime); // stock l'information dans l'event

					event.setEndEvent(rs.getTimestamp(5).toLocalDateTime()); // idem 3 lignes
					event.setIdResponsable(rs.getInt(6));

					events.add(event);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return events;
	} 

	public Event FindEventAndAllActivityByIdEvent(int id) {
		
		Event event = null;
		List<Activity> activities = new ArrayList<Activity>();
		
		if (id > 0) {
		String sql = "Select \"eventName\", description, \"dateDebut\", \"dateFin\" From \"Events\" e left join \"Activities\" a on e.id_event = a.id_event Where e.id_event = ?" ;
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql);) {
			
			query.setInt(1, id);

			try (java.sql.ResultSet rs = query.executeQuery();) {

				if (rs.next()) {
					
					String name = rs.getString(1);
					String description = rs.getString(2);
					LocalDateTime startEvent = rs.getTimestamp(3).toLocalDateTime();
					LocalDateTime endEvent = rs.getTimestamp(4).toLocalDateTime();
					
					if (activities != null) {

						sql = "SELECT id_activity, \"nameActivity\", \"descriptionActivity\", \"startActivity\", \"endActivity\" FROM \"Activities\" a join \"Events\" e on a.id_event = e.id_event WHERE a.id_event = ?";

						try (java.sql.Connection connection2 = java.sql.DriverManager.getConnection(url, user,
								password); java.sql.PreparedStatement query2 = connection.prepareStatement(sql);) {
							query2.setInt(1, id);
							try (java.sql.ResultSet resultSet2 = query2.executeQuery();) {

								if (resultSet2.next()) {
									
									Activity activity = new Activity();
									activity.setId(resultSet2.getInt("id_activity"));
									activity.setName(resultSet2.getString("nameActivity"));
									activity.setDescription(resultSet2.getString("descriptionActivity"));
									activity.setStartActivity(resultSet2.getTimestamp("startActivity").toLocalDateTime());
									activity.setEndActivity(resultSet2.getTimestamp("endActivity").toLocalDateTime());
									activities.add(activity);

									while (resultSet2.next()) {
										
										activity.setName(resultSet2.getString("nameActivity"));
										activity.setDescription(resultSet2.getString("descriptionActivity"));
										activity.setStartActivity(resultSet2.getTimestamp("startActivity").toLocalDateTime());
										activity.setEndActivity(resultSet2.getTimestamp("endActivity").toLocalDateTime());
										activities.add(activity);
									}
								} else {
									activities = Collections.emptyList();
								}
							}
						}
						
						event = new Event();
						event.setId(id);
						event.setName(name);
						event.setDescription(description);
						event.setStartEvent(startEvent);
						event.setEndEvent(endEvent);
						event.setActivities(activities);
						
						
					}

				}
			} 
		}catch (java.sql.SQLException sqle) {
			throw new RuntimeException(sqle);
		}
	}

	return event;
}

	public InscriptionActivity CreateNewInscriptionActivity(InscriptionActivity newInscriptionActivity)
			throws SQLException {

		if (newInscriptionActivity.getIdPerson() > 0 && newInscriptionActivity.getIdPerson() > 0) {
			String sql = "INSERT INTO \"Inscription_activity\" Values (DEFAULT, (SELECT id_person FROM persons WHERE id_person = ?),(SELECT id_activity FROM \"Activities\" WHERE id_activity = ?))";
			try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
					java.sql.PreparedStatement query = connection.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);) {
				connection.setAutoCommit(false);
				query.setInt(1, newInscriptionActivity.getIdPerson());
				query.setInt(2, newInscriptionActivity.getIdActivity());
				query.executeUpdate();
				try (ResultSet resultSet = query.getGeneratedKeys()

				) {
					if (resultSet.next()) {
						int id = resultSet.getInt(1);

						newInscriptionActivity.setIdInscriptionActivity(id);
					}
				}
				connection.commit();

			} catch (java.sql.SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		}

		return newInscriptionActivity;

	}
	
	public boolean deleteEventById(int idEvent, int idResponsable) {

		boolean deleted = false;

		String SqlIdPersonString = "Select id_person From \"Events\" Where id_event = ?";
		int idPerson = Integer.parseInt(SqlIdPersonString);

		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement idPersonString = connection.prepareStatement(SqlIdPersonString);) {

			idPersonString.setInt(1, idEvent);

			if (idEvent > 0 && idResponsable == idPerson) {
				
				String sqlDeleteActivities = "delete from \"Activities\" Where id_event = ?";
				String sqlDeleteEvent = "delete from \"Events\" Where id_event = ?";
				try (
						java.sql.Connection connection2 = java.sql.DriverManager.getConnection(url, user, password);
		                PreparedStatement deleteActivitiesStatement = connection2.prepareStatement(sqlDeleteActivities);
		                PreparedStatement deleteEventStatement = connection2.prepareStatement(sqlDeleteEvent)
		            ) {
		                connection.setAutoCommit(false); 
		                
		                deleteActivitiesStatement.setInt(1, idEvent); 
		                deleteActivitiesStatement.executeUpdate();
		                deleteEventStatement.setInt(1, idEvent);
		                deleteEventStatement.executeUpdate();
		                connection.commit();
		                deleted = deleteEventStatement.getUpdateCount() > 0;
		            } catch (Exception e) {
		                throw new RuntimeException(e);
		            }
			}
		} catch (java.sql.SQLException sqle) {
			throw new RuntimeException(sqle);
		}
		return deleted;
	}



	/*
	 * public Integer FindIdEventByName(String eventName) {
	 * 
	 * Integer idEvent = null;
	 * 
	 * String sql = "Select id_event From \"Events\" Where \"eventName\" = ?";
	 * 
	 * try ( PreparedStatement preparedStatement = c.prepareStatement(sql) ) {
	 * preparedStatement.setString(1, password); try ( ResultSet resultSet =
	 * preparedStatement.executeQuery() ) { if (resultSet.next()) { //récupérer la
	 * ou les colonne(s) demandée(s) email = resultSet.getString(1); } } }
	 * 
	 * 
	 * return idEvent; }
	 */

}
