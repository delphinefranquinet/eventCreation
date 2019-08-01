package be.afelio.teamZDRR.eventCreationJava.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.afelio.teamZDRR.eventCreationJava.beans.Activity;
import be.afelio.teamZDRR.eventCreationJava.beans.Event;
import be.afelio.teamZDRR.eventCreationJava.beans.Person;
import be.afelio.teamZDRR.eventCreationJava.model.EventRepository;

public class EventRepositoryImpl implements EventRepository {

	protected String user;
	protected String password;
	protected String url;

	public EventRepositoryImpl(String user, String password, String url) {
		super();
		this.user = user;
		this.password = password;
		this.url = url;
	}

	public Person connexion(String email, String mot_de_passe) {

		Person person = null;

		String sql = "Select id_person, \"namePerson\", \"firstnamePerson\" From persons Where email = ? AND password = ?";
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql);) {
			query.setString(1, email);
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

	public Person createPerson(ResultSet rs) throws SQLException {
		//changer de protected a public 
		Person person = new Person();
		person.setId(rs.getInt("id_person"));
		person.setName(rs.getString("namePerson"));
		person.setFirstname(rs.getString("firstnamePerson"));
		return person;
	}

	public Person createNewPerson(Person newPerson) {

		String sql = "INSERT INTO persons Values (DEFAULT, ?, ?, ?, ?)";
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

			connection.setAutoCommit(false);// pour etre certain que les 2 aboutissent, sinon que les 2 �chouent.
			query.setString(1, newPerson.getName());
			query.setString(2, newPerson.getFirstname());
			query.setString(3, newPerson.getEmail());
			query.setString(4, newPerson.getPassword());
			query.executeUpdate(); // insert/update/delete
			try (ResultSet resultSet = query.getGeneratedKeys() // pour r�cup�rer l'id qui est en autoincrement, ne
																// pas
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

	public Event createNewEvent(Event newEvent) {

		// Timestamp timestamp = Timestamp.valueOf(localDateTime); Convertir
		// localDateTime en Timestamp
		String sql = "insert into \"Events\" values (default, ?, ?, ?, ?, ?, ? )";
		
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

			connection.setAutoCommit(false);// pour etre certain que les 2 aboutissent, sinon que les 2 �chouent.
			query.setString(1, newEvent.getName());
			query.setString(2, newEvent.getDescription());
			query.setTimestamp(3, Timestamp.valueOf(newEvent.getStartEvent())); // Timestamp.valueOf(startEvent)
			query.setTimestamp(4, Timestamp.valueOf(newEvent.getEndEvent())); // Timestamp.valueOf(endEvent)
			query.setInt(5, newEvent.getIdResponsable());
			query.setString(6, newEvent.getPlace());
			query.executeUpdate(); // insert/update/delete

			try (ResultSet resultSet = query.getGeneratedKeys() // pour r�cup�rer l'id qui est en autoincrement, ne
																// pas
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

	public Activity createNewActivity(Activity newActivity) {

		boolean timeIsCorrect = false;

		String sql = "INSERT INTO \"Activities\" Values (default, ?, ?, ?, ?, ?)";
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			
			/*try (ResultSet rs = query.executeQuery()) {
				while (rs.next()) {
			LocalDateTime startActivity = query.setTimestamp(3, Timestamp.valueOf(newActivity.getStartActivity()));
			LocalDateTime endActivity = query.setTimestamp(4, Timestamp.valueOf(newActivity.getEndActivity()));
			int idEvent = query.setInt(5, newActivity.getIdEvent());
					
					timeIsCorrect = LocalDateTimeComparisonForCreateNewActivity(idEvent, startActivity, endActivity);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
			*/
			if (timeIsCorrect) {
				connection.setAutoCommit(false);
				query.setString(1, newActivity.getName());
				query.setString(2, newActivity.getDescription());
				query.setTimestamp(3, Timestamp.valueOf(newActivity.getStartActivity()));
				query.setTimestamp(4, Timestamp.valueOf(newActivity.getEndActivity()));
				query.setInt(5, newActivity.getIdEvent());
				query.executeUpdate(); // insert/update/delete

				try (ResultSet resultSet = query.getGeneratedKeys() // pour recuperer l'id qui est en autoincrement, ne
																	// pas
																	// oublier Statement.RETURN_GENERATED_KEYS)
				) {
					if (resultSet.next()) {
						int id = resultSet.getInt(1);
						// System.out.println("bonjour" + id);
						newActivity.setId(id);
					}
				}
				connection.commit();
			}
		} catch (java.sql.SQLException sqle) {
			throw new RuntimeException(sqle);

		}
		System.out.println(timeIsCorrect);
		return newActivity;
	}

	public List<Event> FindAllEvents() {

		List<Event> events = new ArrayList<>();
		String sql = "Select id_event, \"eventName\", description, \"dateDebut\", \"dateFin\", id_person, place From \"Events\"";

		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql)) {

			try (ResultSet rs = query.executeQuery()) {

				while (rs.next()) {
					Event event = new Event();
					event.setId(rs.getInt(1));
					event.setName(rs.getString(2));
					event.setDescription(rs.getString(3));

					Timestamp timestamp = rs.getTimestamp(4); // r�cup�rer le timestamp
					LocalDateTime localDateTime = timestamp.toLocalDateTime(); // convertir Timestamp en localDateTime
					event.setStartEvent(localDateTime); // stock l'information dans l'event

					event.setEndEvent(rs.getTimestamp(5).toLocalDateTime()); // idem 3 lignes
					event.setIdResponsable(rs.getInt(6));
					event.setPlace(rs.getString(7));

					events.add(event);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return events;
	}

	public Event findEventAndAllActivityByIdEvent(int id) {

		Event event = null;
		List<Activity> activities = new ArrayList<Activity>();

		if (id > 0) {
			String sql = "Select \"eventName\", description, \"dateDebut\", \"dateFin\", place From \"Events\" e left join \"Activities\" a on e.id_event = a.id_event Where e.id_event = ?";
			try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
					java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

				query.setInt(1, id);

				try (java.sql.ResultSet rs = query.executeQuery();) {

					if (rs.next()) {

						String name = rs.getString(1);
						String description = rs.getString(2);
						LocalDateTime startEvent = rs.getTimestamp(3).toLocalDateTime();
						LocalDateTime endEvent = rs.getTimestamp(4).toLocalDateTime();
						String place = rs.getString(5);

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
										activity.setStartActivity(
												resultSet2.getTimestamp("startActivity").toLocalDateTime());
										activity.setEndActivity(
												resultSet2.getTimestamp("endActivity").toLocalDateTime());
										activities.add(activity);

										while (resultSet2.next()) {

											activity.setName(resultSet2.getString("nameActivity"));
											activity.setDescription(resultSet2.getString("descriptionActivity"));
											activity.setStartActivity(
													resultSet2.getTimestamp("startActivity").toLocalDateTime());
											activity.setEndActivity(
													resultSet2.getTimestamp("endActivity").toLocalDateTime());
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
							event.setPlace(place);
							event.setActivities(activities);

						}

					}
				}
			} catch (java.sql.SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		}

		return event;
	}

	public boolean createNewInscriptionActivity(Integer idPerson, Integer idActivity) throws SQLException {
		boolean add = false;
		boolean freeTime = false;
		
		freeTime = LocalDateTimeComparisonForInscription(idPerson, idActivity);
		
		if (idPerson != null && idActivity != null && freeTime) {
			String sql = "INSERT INTO \"Inscription_activity\" Values (DEFAULT, ?, ?)";
			try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
					java.sql.PreparedStatement query = connection.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);) {
				connection.setAutoCommit(false);
				query.setInt(1, idPerson);
				query.setInt(2, idActivity);
				query.executeUpdate();
				int updatedRows = query.getUpdateCount();
				connection.commit();
				add = updatedRows > 0;

			} catch (java.sql.SQLException sqle) {
				// throw new RuntimeException(sqle);
			}
		}

		return add;

	}

	public Event updateEventByIdEvent(int idResponsable, int idEvent, Event newEvent) {

		String sql = "update \"Events\" set \"eventName\" = ? , description = ?, \"dateDebut\" = ?, \"dateFin\" = ?, id_person = " + idResponsable + ", place = ? Where id_event = " + idEvent;
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

			connection.setAutoCommit(false);
			query.setString(1, newEvent.getName());
			query.setString(2, newEvent.getDescription());
			query.setTimestamp(3, Timestamp.valueOf(newEvent.getStartEvent()));
			query.setTimestamp(4, Timestamp.valueOf(newEvent.getEndEvent())); 
			//query.setInt(5, idResponsable);
			query.setString(5, newEvent.getPlace());
			//query.setInt(7, idEvent);
			query.executeUpdate();

			connection.commit();

		} catch (java.sql.SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return newEvent;
	}
	
	public boolean deleteEventByIdEvent(int idEvent) {

		boolean deleted = false;

			if (idEvent > 0) {
				
				System.out.println("EventRepositoryImpl.deleteEventByIdEvent()");

				String sqlDeleteEvent = "delete from \"Events\" Where id_event = ?";
				
				try (java.sql.Connection connection2 = java.sql.DriverManager.getConnection(url, user, password);
						PreparedStatement deleteEventStatement = connection2.prepareStatement(sqlDeleteEvent)) {
					connection2.setAutoCommit(false);

					deleteEventStatement.setInt(1, idEvent);
					deleteEventStatement.executeUpdate();
					connection2.commit();
					deleted = deleteEventStatement.getUpdateCount() > 0;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		
		return deleted;
	}
	
	public List<Activity> findAllActivitiesByIdEvent(int idEvent) {
		List<Activity> activities = new ArrayList<Activity>();

		if (idEvent > 0) {
			String sql = "SELECT id_activity, \"nameActivity\", \"descriptionActivity\", \"startActivity\", \"endActivity\" FROM \"Activities\" a join \"Events\" e on a.id_event = e.id_event WHERE a.id_event = ?";

			try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
					java.sql.PreparedStatement query = connection.prepareStatement(sql);) {
				
				query.setInt(1, idEvent);
				
				try (java.sql.ResultSet rs = query.executeQuery();) {
					if (rs.next()) {
						
						Activity activity = new Activity();
						activity.setId(rs.getInt("id_activity"));
						activity.setName(rs.getString("nameActivity"));
						activity.setDescription(rs.getString("descriptionActivity"));
						activity.setStartActivity(
								rs.getTimestamp("startActivity").toLocalDateTime());
						activity.setEndActivity(
								rs.getTimestamp("endActivity").toLocalDateTime());
						activities.add(activity);

						while (rs.next()) {

							activity.setName(rs.getString("nameActivity"));
							activity.setDescription(rs.getString("descriptionActivity"));
							activity.setStartActivity(
									rs.getTimestamp("startActivity").toLocalDateTime());
							activity.setEndActivity(
									rs.getTimestamp("endActivity").toLocalDateTime());
							activities.add(activity);
						}
					} else {
						activities = Collections.emptyList();
						
					}
				}
				
				
			} catch (java.sql.SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		}

		return activities;
	}
	
	public List<Event> findEventAndAllActivityByIdResponsable(int idResponsable) {

		List<Event> events = new ArrayList<Event>();
		Event event = null;
		
		if (idResponsable > 0) {
			
			String sql = "Select \"eventName\", description, \"dateDebut\", \"dateFin\", place, id_event From \"Events\" Where id_person = ?";
			try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
					java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

				query.setInt(1, idResponsable);

				try (java.sql.ResultSet rs = query.executeQuery();) {
					while (rs.next()) {
						
						String name = rs.getString(1);
						String description = rs.getString(2);
						LocalDateTime startEvent = rs.getTimestamp(3).toLocalDateTime();
						LocalDateTime endEvent = rs.getTimestamp(4).toLocalDateTime();
						String place = rs.getString(5);
						int idEvent = rs.getInt(6);

						List<Activity> activities = findAllActivitiesByIdEvent(idEvent);

						event = new Event();
						event.setId(idEvent);
						event.setName(name);
						event.setDescription(description);
						event.setStartEvent(startEvent);
						event.setEndEvent(endEvent);
						event.setPlace(place);
						event.setIdResponsable(idResponsable);
						event.setActivities(activities);

						events.add(event);

					}
				}
			} catch (java.sql.SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		}
		System.out.println(events);
		return events;
	}

	public List<Event> findEventAndAllActivityByEventName(String eventName) {

		List<Event> events = new ArrayList<Event>();
		Event event = null;
		List<Activity> activities = new ArrayList<Activity>();

		if (eventName != null) {
			
			String sql = "Select id_event, \"eventName\", description, \"dateDebut\", \"dateFin\", place From \"Events\" Where lower(\"eventName\") LIKE lower('%" + eventName + "%')";
			
			try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
					java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

				//query.setString(1, eventName);

				try (java.sql.ResultSet rs = query.executeQuery();) {
					while (rs.next()) {
						
						int idEvent = rs.getInt(1);
						String name = rs.getString(2);
						String description = rs.getString(3);
						LocalDateTime startEvent = rs.getTimestamp(4).toLocalDateTime();
						LocalDateTime endEvent = rs.getTimestamp(5).toLocalDateTime();
						String place = rs.getString(6);
						

						activities = findAllActivitiesByIdEvent(idEvent);

						event = new Event();
						event.setId(idEvent);
						event.setName(name);
						event.setDescription(description);
						event.setStartEvent(startEvent);
						event.setEndEvent(endEvent);
						event.setPlace(place);
						event.setActivities(activities);

						events.add(event);

					}
				}
			} catch (java.sql.SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		}
		System.out.println(events);
		return events;
	}

	public List<Integer> findIdActivityByIdPerson(int idPerson) {

		List<Integer> idActivities = new ArrayList<Integer>();

		if (idPerson > 0) {

			String sql = "Select id_activity From \"Inscription_activity\" Where id_person = ?";

			try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
					java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

				query.setInt(1, idPerson);

				try (java.sql.ResultSet rs = query.executeQuery();) {

					while (rs.next()) {

						int idActivity = rs.getInt(1);

						idActivities.add(idActivity);
					}
				}
			} catch (java.sql.SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		}

		return idActivities;
	}
	
	public List<LocalDateTime> findAllLocalDateTimeStartByIdActivities(int idPerson) {

		List<LocalDateTime> LocalDateTimeStartActivities = new ArrayList<LocalDateTime>();
		
		List<Integer> idActivities = findIdActivityByIdPerson(idPerson);

		if (idActivities != null) {

			for (int i = 0; i < idActivities.size(); i++) {

				int idActivity = idActivities.get(i);

				String sql = "Select \"startActivity\" From \"Activities\" Where id_activity = ?";

				try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
						java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

					query.setInt(1, idActivity);

					try (java.sql.ResultSet rs = query.executeQuery();) {

						while (rs.next()) {

							LocalDateTime timeStartActivity = rs.getTimestamp("startActivity").toLocalDateTime();

							LocalDateTimeStartActivities.add(timeStartActivity);
						}
					}
				} catch (java.sql.SQLException sqle) {
					throw new RuntimeException(sqle);
				}
			}
		}else {
			idActivities = Collections.emptyList();
		}
		return LocalDateTimeStartActivities;
	}

	public List<LocalDateTime> findAllLocalDateTimeEndByIdActivities(int idPerson) {

		List<LocalDateTime> LocalDateTimeEndActivities = new ArrayList<LocalDateTime>();
		List<Integer> idActivities = findIdActivityByIdPerson(idPerson);

		if (idActivities != null) {

			for (int i = 0; i < idActivities.size(); i++) {

				int idActivity = idActivities.get(i);

				String sql = "Select \"endActivity\" From \"Activities\" Where id_activity = ?";

				try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
						java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

					query.setInt(1, idActivity);

					try (java.sql.ResultSet rs = query.executeQuery();) {

						while (rs.next()) {

							LocalDateTime timeEndActivity = rs.getTimestamp("endActivity").toLocalDateTime();

							LocalDateTimeEndActivities.add(timeEndActivity);
						}
					}
				} catch (java.sql.SQLException sqle) {
					throw new RuntimeException(sqle);
				}
			}
		}else {
			idActivities = Collections.emptyList();
		}
		return LocalDateTimeEndActivities;
	}

	public LocalDateTime findLocalDateTimeStartNewActivity(int idActivity) {

		LocalDateTime newLocalDateTimeStartActivity = null;

		if (idActivity > 0) {

			String sql = "select \"startActivity\" From \"Activities\" Where id_activity = ?";
			try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
					java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

				query.setInt(1, idActivity);

				try (java.sql.ResultSet rs = query.executeQuery();) {
					while (rs.next()) {

						newLocalDateTimeStartActivity = rs.getTimestamp("startActivity").toLocalDateTime();
					}
				}
			} catch (java.sql.SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		}

		return newLocalDateTimeStartActivity;
	}

	public LocalDateTime findLocalDateTimeEndNewActivity(int idActivity) {

		LocalDateTime newLocalDateTimeEndActivity = null;

		if (idActivity > 0) {

			String sql = "select \"endActivity\" From \"Activities\" Where id_activity = ?";
			try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
					java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

				query.setInt(1, idActivity);

				try (java.sql.ResultSet rs = query.executeQuery();) {
					while (rs.next()) {
						newLocalDateTimeEndActivity = rs.getTimestamp("endActivity").toLocalDateTime();
					}
				}
			} catch (java.sql.SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		}

		return newLocalDateTimeEndActivity;
	}
	
	public boolean LocalDateTimeComparisonForInscription(int idPerson, int idActivity) {

		boolean freeTime = true;

		List<LocalDateTime> localDateTimeStartActivities = new ArrayList<LocalDateTime>();
		List<LocalDateTime> localDateTimeEndActivities = new ArrayList<LocalDateTime>();
		int compareValueStartToStart;
		int compareValueStartToEnd;
		int compareValueEndToStart;
		int compareValueEndToEnd;
		LocalDateTime newLocalDateTimeStartActivity = null;
		LocalDateTime newLocalDateTimeEndActivity = null;

		localDateTimeStartActivities = findAllLocalDateTimeStartByIdActivities(idPerson);
		localDateTimeEndActivities = findAllLocalDateTimeEndByIdActivities(idPerson);
		newLocalDateTimeStartActivity = findLocalDateTimeStartNewActivity(idActivity);
		newLocalDateTimeEndActivity = findLocalDateTimeEndNewActivity(idActivity);

		if (localDateTimeStartActivities != null && localDateTimeEndActivities != null) {

			for (int i = 0; i < localDateTimeStartActivities.size(); i++) {

				LocalDateTime localDateTimeStartActivity = localDateTimeStartActivities.get(i);
				LocalDateTime localDateTimeEndActivity = localDateTimeStartActivities.get(i);

				compareValueStartToStart = newLocalDateTimeStartActivity.compareTo(localDateTimeStartActivity);
				compareValueStartToEnd = newLocalDateTimeStartActivity.compareTo(localDateTimeEndActivity);
				compareValueEndToStart = newLocalDateTimeEndActivity.compareTo(localDateTimeStartActivity);
				compareValueEndToEnd = newLocalDateTimeEndActivity.compareTo(localDateTimeEndActivity);

				if ((compareValueStartToStart >= 0 && compareValueStartToEnd <= 0)
						|| (compareValueEndToStart <= 0 && compareValueEndToEnd >= 0)) {
					freeTime = false;

				} else {
					freeTime = true;
				}
			}
		} else {
			freeTime = true;
		}
		System.out.println(freeTime);
		return freeTime;
	}

	public LocalDateTime findLocalDateTimeStartEvent(int idEvent) {

		LocalDateTime localDateTimeStartEvent = null;

		if (idEvent > 0) {

			String sql = "select \"dateDebut\" From \"Events\" Where id_event = ?";
			try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
					java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

				query.setInt(1, idEvent);

				try (java.sql.ResultSet rs = query.executeQuery();) {
					while (rs.next()) {

						localDateTimeStartEvent = rs.getTimestamp("dateDebut").toLocalDateTime();
					}
				}
			} catch (java.sql.SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		}

		return localDateTimeStartEvent;
	}
	
	public LocalDateTime findLocalDateTimeEndEvent(int idEvent) {

		LocalDateTime localDateTimeEndEvent = null;

		if (idEvent > 0) {

			String sql = "select \"dateFin\" From \"Events\" Where id_event = ?";
			try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
					java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

				query.setInt(1, idEvent);

				try (java.sql.ResultSet rs = query.executeQuery();) {
					while (rs.next()) {

						localDateTimeEndEvent = rs.getTimestamp("dateFin").toLocalDateTime();
					}
				}
			} catch (java.sql.SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		}

		return localDateTimeEndEvent;
	}

	public boolean LocalDateTimeComparisonForCreateNewActivity(int idEvent, LocalDateTime localDateTimeStartNewActivity,
			LocalDateTime localDateTimeEndNewActivity) {

		LocalDateTime localDateTimeStartEvent = null;
		LocalDateTime localDateTimeEndEvent = null;
		boolean timeIsCorrect = true;

		localDateTimeStartEvent = findLocalDateTimeStartEvent(idEvent);
		localDateTimeEndEvent = findLocalDateTimeEndEvent(idEvent);

		int compareValueStartToStart;
		int compareValueStartToEnd;
		int compareValueEndToStart;
		int compareValueEndToEnd;

		if (localDateTimeStartNewActivity != null && localDateTimeEndNewActivity != null) {

			compareValueStartToStart = localDateTimeStartNewActivity.compareTo(localDateTimeStartEvent);
			compareValueStartToEnd = localDateTimeStartNewActivity.compareTo(localDateTimeEndEvent);
			compareValueEndToStart = localDateTimeEndNewActivity.compareTo(localDateTimeStartEvent);
			compareValueEndToEnd = localDateTimeEndNewActivity.compareTo(localDateTimeEndEvent);

			if ((compareValueStartToStart >= 0 && compareValueStartToEnd <= 0)
					|| (compareValueEndToStart <= 0 && compareValueEndToEnd >= 0)) {
				timeIsCorrect = false;

			} else {
				timeIsCorrect = true;
			}

		} else {
			timeIsCorrect = true;
		}
		System.out.println(timeIsCorrect);
		return timeIsCorrect;
	}
}
