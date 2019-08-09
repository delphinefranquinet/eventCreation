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

	String sql = "Select id_person, namePerson, firstnamePerson From persons Where email = ? AND password = ?";
	try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {
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

    protected Person createPerson(ResultSet rs) throws SQLException {
	// changer de protected a public
	Person person = new Person();
	person.setId(rs.getInt("id_person"));
	person.setName(rs.getString("namePerson"));
	person.setFirstname(rs.getString("firstnamePerson"));
	return person;
    }

    public Person createNewPerson(Person newPerson) {

	String sql = "INSERT INTO persons Values (DEFAULT, ?, ?, ?, ?)";
	try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

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
	String sql = "insert into events values (default, ?, ?, ?, ?, ?, ? )";

	try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

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
	LocalDateTime startActivity = newActivity.getStartActivity();
	LocalDateTime endActivity = newActivity.getEndActivity();
	int idEvent = newActivity.getIdEvent();

	timeIsCorrect = localDateTimeComparisonForCreateNewActivity(idEvent, startActivity, endActivity);

	if (timeIsCorrect) {

	    String sql = "INSERT INTO activities Values (default, ?, ?, ?, ?, ?)";

	    try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

		connection.setAutoCommit(false);
		query.setString(1, newActivity.getName());
		query.setString(2, newActivity.getDescription());
		query.setTimestamp(3, Timestamp.valueOf(newActivity.getStartActivity()));
		query.setTimestamp(4, Timestamp.valueOf(newActivity.getEndActivity()));
		query.setInt(5, idEvent);
		query.executeUpdate(); // insert/update/delete

		try (ResultSet resultSet = query.getGeneratedKeys()) {
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
	}
	// System.out.println(timeIsCorrect);
	return newActivity;
    }

    public List<Event> findAllEvents() {

	List<Event> events = new ArrayList<>();
	String sql = "Select id_event, eventName, description, startdate, enddate, id_person, eventplace From events order by id_event";

	try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql)) {

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

    public Event findEventAndAllActivityByIdEvent(int idEvent) {

	Event event = new Event();

	if (idEvent > 0) {

	    String sql = "Select eventName, description, startdate, enddate, eventplace From Events Where id_event = ? order by id_event";
	    try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

		query.setInt(1, idEvent);

		try (java.sql.ResultSet rs = query.executeQuery();) {
		    while (rs.next()) {

			String name = rs.getString(1);
			String description = rs.getString(2);
			LocalDateTime startEvent = rs.getTimestamp(3).toLocalDateTime();
			LocalDateTime endEvent = rs.getTimestamp(4).toLocalDateTime();
			String place = rs.getString(5);

			List<Activity> activities = findAllActivitiesByIdEvent(idEvent);

			event.setId(idEvent);
			event.setName(name);
			event.setDescription(description);
			event.setStartEvent(startEvent);
			event.setEndEvent(endEvent);
			event.setPlace(place);
			event.setActivities(activities);

		    }
		}
	    } catch (java.sql.SQLException sqle) {
		throw new RuntimeException(sqle);
	    }
	}
	System.out.println(event);
	return event;
    }

    public boolean createNewInscriptionActivity(Integer idPerson, Integer idActivity) throws SQLException {
	boolean add = false;
	boolean freeTime = false;

	freeTime = LocalDateTimeComparisonForInscription(idPerson, idActivity);

	if (idPerson != null && idActivity != null && freeTime) {
	    String sql = "INSERT INTO Inscription_activity Values (DEFAULT, ?, ?)";
	    try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
		connection.setAutoCommit(false);
		query.setInt(1, idPerson);
		query.setInt(2, idActivity);
		query.executeUpdate();
		int updatedRows = query.getUpdateCount();
		connection.commit();
		add = updatedRows > 0;

	    } catch (java.sql.SQLException sqle) {
		throw new RuntimeException(sqle);
	    }
	}

	return add;

    }

    public boolean updateEventAndActivitiesByIdEvent(int idResponsable, Event newEvent) {

	List<Activity> activities = new ArrayList<Activity>();
	activities = newEvent.getActivities();
	int idEvent = newEvent.getId();
	boolean confirmation = false;

	String sql = "update Events set eventName = ? , description = ?, startdate= ?, enddate = ?, id_person = " + idResponsable + ", eventplace = ? Where id_event = " + idEvent;
	try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

	    connection.setAutoCommit(false);
	    query.setString(1, newEvent.getName());
	    query.setString(2, newEvent.getDescription());
	    query.setTimestamp(3, Timestamp.valueOf(newEvent.getStartEvent()));
	    query.setTimestamp(4, Timestamp.valueOf(newEvent.getEndEvent()));
	    query.setString(5, newEvent.getPlace());
	    query.executeUpdate();
	    connection.commit();

	    if (activities != null) {

		activities = updateActivities(activities);
	    }
	    confirmation = true;

	} catch (java.sql.SQLException sqle) {

	}
	return confirmation;
    }

    public boolean deleteEventByIdEvent(int idEvent) {

	boolean deleted = false;

	if (idEvent > 0) {

	    System.out.println("EventRepositoryImpl.deleteEventByIdEvent()");

	    String sqlDeleteEvent = "delete from Events Where id_event = ?";

	    try (java.sql.Connection connection2 = java.sql.DriverManager.getConnection(url, user, password); PreparedStatement deleteEventStatement = connection2.prepareStatement(sqlDeleteEvent)) {
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
	    String sql = "SELECT id_activity, nameActivity, descriptionActivity, startActivity, endActivity FROM Activities WHERE id_event = ?";

	    try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

		query.setInt(1, idEvent);

		try (java.sql.ResultSet rs = query.executeQuery();) {
		    if (rs.next()) {

			do {
			    Activity activity = new Activity();
			    activity.setId(rs.getInt("id_activity"));
			    activity.setName(rs.getString("nameActivity"));
			    activity.setDescription(rs.getString("descriptionActivity"));
			    activity.setStartActivity(rs.getTimestamp("startActivity").toLocalDateTime());
			    activity.setEndActivity(rs.getTimestamp("endActivity").toLocalDateTime());
			    activities.add(activity);
			} while (rs.next());
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

	    String sql = "Select eventName, description, startdate, enddate, eventplace, id_event From Events Where id_person = ? order by id_event";
	    try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

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

	    String sql = "Select id_event, eventName, description, startdate, enddate, eventplace From Events Where lower(eventName) LIKE lower('%" + eventName + "%')";

	    try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

		// query.setString(1, eventName);

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

    public List<Integer> findAllActivitiesByIdPerson(int idPerson) {

	List<Integer> idActivities = new ArrayList<Integer>();

	if (idPerson > 0) {

	    String sql = "Select id_activity From Inscription_activity Where id_person = ?";

	    try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

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

	List<Integer> idActivities = findAllActivitiesByIdPerson(idPerson);

	if (idActivities != null) {

	    for (int i = 0; i < idActivities.size(); i++) {

		int idActivity = idActivities.get(i);

		String sql = "Select startActivity From Activities Where id_activity = ?";

		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

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
	} else {
	    idActivities = Collections.emptyList();
	}
	return LocalDateTimeStartActivities;
    }

    public List<LocalDateTime> findAllLocalDateTimeEndByIdActivities(int idPerson) {

	List<LocalDateTime> LocalDateTimeEndActivities = new ArrayList<LocalDateTime>();
	List<Integer> idActivities = findAllActivitiesByIdPerson(idPerson);

	if (idActivities != null) {

	    for (int i = 0; i < idActivities.size(); i++) {

		int idActivity = idActivities.get(i);

		String sql = "Select endActivity From Activities Where id_activity = ?";

		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

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
	} else {
	    idActivities = Collections.emptyList();
	}
	return LocalDateTimeEndActivities;
    }

    public LocalDateTime findLocalDateTimeStartNewActivity(int idActivity) {

	LocalDateTime newLocalDateTimeStartActivity = null;

	if (idActivity > 0) {

	    String sql = "select startActivity From Activities Where id_activity = ?";
	    try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

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

	    String sql = "select endActivity From Activities Where id_activity = ?";
	    try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

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

		if ((compareValueStartToStart >= 0 && compareValueStartToEnd <= 0) || (compareValueEndToStart <= 0 && compareValueEndToEnd >= 0)) {
		    freeTime = false;

		}
	    }
	}
	System.out.println(freeTime);
	return freeTime;
    }

    public LocalDateTime findLocalDateTimeStartEvent(int idEvent) {

	LocalDateTime localDateTimeStartEvent = null;

	if (idEvent > 0) {

	    String sql = "select startdate From events Where id_event = ?";
	    try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

		query.setInt(1, idEvent);

		try (java.sql.ResultSet rs = query.executeQuery();) {
		    while (rs.next()) {

			localDateTimeStartEvent = rs.getTimestamp("startdate").toLocalDateTime();
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

	    String sql = "select enddate From events Where id_event = ?";
	    try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

		query.setInt(1, idEvent);

		try (java.sql.ResultSet rs = query.executeQuery();) {
		    while (rs.next()) {

			localDateTimeEndEvent = rs.getTimestamp("enddate").toLocalDateTime();
		    }
		}
	    } catch (java.sql.SQLException sqle) {
		throw new RuntimeException(sqle);
	    }

	}

	return localDateTimeEndEvent;
    }

    public boolean localDateTimeComparisonForCreateNewActivity(int idEvent, LocalDateTime localDateTimeStartNewActivity, LocalDateTime localDateTimeEndNewActivity) {

	LocalDateTime localDateTimeStartEvent = null;
	LocalDateTime localDateTimeEndEvent = null;
	boolean timeIsCorrect = false;

	localDateTimeStartEvent = findLocalDateTimeStartEvent(idEvent);
	localDateTimeEndEvent = findLocalDateTimeEndEvent(idEvent);

	int compareValueStart;
	int compareValueEnd;

	if (localDateTimeStartNewActivity != null && localDateTimeEndNewActivity != null) {

	    compareValueStart = localDateTimeStartNewActivity.compareTo(localDateTimeStartEvent);
	    compareValueEnd = localDateTimeEndNewActivity.compareTo(localDateTimeEndEvent);

	    if (compareValueStart >= 0 && compareValueEnd <= 0) {

		timeIsCorrect = true;
	    }

	    System.out.println(timeIsCorrect);
	}
	return timeIsCorrect;
    }

    public List<Person> findAllPerson() {

	List<Person> persons = new ArrayList<Person>();

	String sql = "Select * From persons";

	try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql)) {

	    try (ResultSet rs = query.executeQuery()) {

		while (rs.next()) {
		    Person person = new Person();
		    person.setId(rs.getInt(1));
		    person.setName(rs.getString(2));
		    person.setFirstname(rs.getString(3));
		    person.setEmail(rs.getString(4));
		    person.setPassword(rs.getString(5));

		    persons.add(person);
		}
	    }
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}

	return persons;
    }

    public boolean deleteOneActivityByIdActivity(int idActivity) {
	boolean deleted = false;

	if (idActivity > 0) {

	    String sql = "delete from activities Where id_activity = ?";

	    try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); PreparedStatement deleteActivityStatement = connection.prepareStatement(sql)) {
		connection.setAutoCommit(false);

		deleteActivityStatement.setInt(1, idActivity);
		deleteActivityStatement.executeUpdate();
		connection.commit();
		deleted = deleteActivityStatement.getUpdateCount() > 0;
	    } catch (Exception e) {
		throw new RuntimeException(e);
	    }
	}

	return deleted;
    }

    public List<Integer> findAllIdActivityByIdEvent(int idEvent) {

	List<Integer> idActivities = new ArrayList<Integer>();

	if (idEvent > 0) {

	    String sql = "Select id_activity From activities Where id_event = ?";

	    try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

		query.setInt(1, idEvent);

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

    public List<Activity> updateActivities(List<Activity> activities) {
	/*
	 * List<Integer> idActivities = findAllIdActivityByIdEvent(idEvent); List<Activity> activities = new ArrayList<Activity>();
	 */
	for (int i = 0; i < activities.size(); i++) {

	    Activity activity = activities.get(i);

	    String sql = "update activities set nameActivity = ? , descriptionActivity = ?, startActivity = ?, endActivity = ? Where id_activity = " + activity.getId();

	    try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

		connection.setAutoCommit(false);

		// Activity activity = new Activity();
		query.setString(1, activity.getName());
		query.setString(2, activity.getDescription());
		query.setTimestamp(3, Timestamp.valueOf(activity.getStartActivity()));
		query.setTimestamp(4, Timestamp.valueOf(activity.getEndActivity()));
		query.executeUpdate();
		connection.commit();

	    } catch (java.sql.SQLException sqle) {
		throw new RuntimeException(sqle);
	    }
	}
	return activities;

    }

    public List<Event> findAllEventByPlace(String place) {

	List<Event> events = new ArrayList<Event>();

	String sql = "Select id_event, eventName, description, startdate, enddate, id_person From events Where lower (eventplace) LIKE lower ('%" + place + "%')";

	try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql)) {

	    try (ResultSet rs = query.executeQuery()) {

		while (rs.next()) {

		    Event event = new Event();
		    event.setId(rs.getInt(1));
		    event.setName(rs.getString(2));
		    event.setDescription(rs.getString(3));
		    event.setStartEvent(rs.getTimestamp(4).toLocalDateTime());
		    event.setEndEvent(rs.getTimestamp(5).toLocalDateTime());
		    event.setIdResponsable(rs.getInt(6));
		    events.add(event);
		}
	    }
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}

	return events;

    }

    public Activity updateOneActivityByIdEvent(int idActivity, Activity activity) {

	String sql = "update activities set nameActivity = ? , descriptionActivity = ?, startActivity = ?, endActivity = ? Where id_activity = " + idActivity;

	try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

	    connection.setAutoCommit(false);

	    query.setString(1, activity.getName());
	    query.setString(2, activity.getDescription());
	    query.setTimestamp(3, Timestamp.valueOf(activity.getStartActivity()));
	    query.setTimestamp(4, Timestamp.valueOf(activity.getEndActivity()));
	    query.executeUpdate();
	    connection.commit();

	} catch (java.sql.SQLException sqle) {
	    throw new RuntimeException(sqle);
	}

	return activity;
    }

    public List<Activity> findAllActivityByInscription(int idPerson) {

	List<Activity> activityList = new ArrayList<Activity>();
	List<Integer> idActivities = new ArrayList<Integer>();

	if (idPerson > 0) {

	    idActivities = findAllActivitiesByIdPerson(idPerson);

	    for (int i = 0; i < idActivities.size(); i++) {

		int idActivity = idActivities.get(i);

		String sql = "Select nameactivity, descriptionactivity, startactivity, endactivity, id_event From activities Where id_activity = ?";

		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

		    query.setInt(1, idActivity);

		    try (java.sql.ResultSet rs = query.executeQuery();) {
			if (rs.next()) {

			    Activity activity = new Activity();
			    activity.setName(rs.getString("nameActivity"));
			    activity.setDescription(rs.getString("descriptionActivity"));
			    activity.setStartActivity(rs.getTimestamp("startActivity").toLocalDateTime());
			    activity.setEndActivity(rs.getTimestamp("endActivity").toLocalDateTime());
			    activity.setIdEvent(rs.getInt("id_event"));
			    activityList.add(activity);

			    while (rs.next()) {

				activity.setName(rs.getString("nameActivity"));
				activity.setDescription(rs.getString("descriptionActivity"));
				activity.setStartActivity(rs.getTimestamp("startActivity").toLocalDateTime());
				activity.setEndActivity(rs.getTimestamp("endActivity").toLocalDateTime());
				activity.setIdEvent(rs.getInt("id_event"));
				activityList.add(activity);
			    }
			} else {
			    activityList = Collections.emptyList();

			}
		    }
		} catch (java.sql.SQLException sqle) {
		    throw new RuntimeException(sqle);
		}
	    }
	} else {
	    idActivities = Collections.emptyList();
	}

	return activityList;
    }

    public List<Event> findEventByIdEvent(int idEvent) {

	List<Event> events = new ArrayList<Event>();
	Event event = null;

	if (idEvent > 0) {

	    String sql = "Select eventName, description, startdate, enddate, eventplace From Events Where id_event = ?";
	    try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

		query.setInt(1, idEvent);

		try (java.sql.ResultSet rs = query.executeQuery();) {
		    while (rs.next()) {

			String name = rs.getString(1);
			String description = rs.getString(2);
			LocalDateTime startEvent = rs.getTimestamp(3).toLocalDateTime();
			LocalDateTime endEvent = rs.getTimestamp(4).toLocalDateTime();
			String place = rs.getString(5);

			event = new Event();
			event.setName(name);
			event.setDescription(description);
			event.setStartEvent(startEvent);
			event.setEndEvent(endEvent);
			event.setPlace(place);

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

    public Activity findOneActivityByIdActivity(int idActivity) {

	Activity activity = new Activity();

	if (idActivity > 0) {

	    String sql = "Select id_activity, nameActivity, descriptionActivity, startActivity, endActivity, id_event From Activities Where id_activity = ? ";

	    try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password); java.sql.PreparedStatement query = connection.prepareStatement(sql);) {

		query.setInt(1, idActivity);

		try (java.sql.ResultSet rs = query.executeQuery();) {
		    if (rs.next()) {

			activity.setId(rs.getInt("id_activity"));
			activity.setName(rs.getString("nameActivity"));
			activity.setDescription(rs.getString("descriptionActivity"));
			activity.setStartActivity(rs.getTimestamp("startActivity").toLocalDateTime());
			activity.setEndActivity(rs.getTimestamp("endActivity").toLocalDateTime());
			activity.setIdEvent(rs.getInt("id_event"));

		    }

		}
	    } catch (java.sql.SQLException sqle) {
		throw new RuntimeException(sqle);
	    }

	}
	return activity;
    }
}
