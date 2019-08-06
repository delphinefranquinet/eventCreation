package be.afelio.teamZDRR.eventCreationJava.model;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import be.afelio.teamZDRR.eventCreationJava.beans.Activity;
import be.afelio.teamZDRR.eventCreationJava.beans.Event;
import be.afelio.teamZDRR.eventCreationJava.beans.Person;

public interface EventRepository {

	Person connexion(String email, String mot_de_passe);
	
	Person createNewPerson(Person newPerson);
	
	Event createNewEvent(Event newEvent);
	
	Activity createNewActivity(Activity newActivity);
	
	List<Event> findAllEvents();
	
	Event findEventAndAllActivityByIdEvent(int idEvent);
	
	boolean createNewInscriptionActivity(Integer idPerson, Integer idActivity) throws SQLException;
	
	Event updateEventAndActivitiesByIdEvent(int idResponsable, Event newEvent);
	
	boolean deleteEventByIdEvent(int idEvent);
	
	List<Activity> findAllActivitiesByIdEvent(int idEvent);
	
	List<Event> findEventAndAllActivityByIdResponsable(int idResponsable);
	
	List<Event> findEventAndAllActivityByEventName(String eventName);
	
	List<Integer> findAllActivitiesByIdPerson(int idPerson);
	
	List<LocalDateTime> findAllLocalDateTimeStartByIdActivities(int idPerson);
	
	List<LocalDateTime> findAllLocalDateTimeEndByIdActivities(int idPerson);
	
	LocalDateTime findLocalDateTimeStartNewActivity(int idActivity);
	
	LocalDateTime findLocalDateTimeEndNewActivity(int idActivity);
	
	boolean LocalDateTimeComparisonForInscription(int idPerson, int idActivity);
	
	LocalDateTime findLocalDateTimeStartEvent(int idEvent);
	
	LocalDateTime findLocalDateTimeEndEvent(int idEvent);
	
	boolean localDateTimeComparisonForCreateNewActivity(int idEvent, LocalDateTime localDateTimeStartNewActivity,
			LocalDateTime localDateTimeEndNewActivity);
	List<Person> findAllPerson ();
	
	boolean deleteOneActivityByIdActivity (int idActivity);
	
	List<Integer> findAllIdActivityByIdEvent (int idEvent);
	
	List<Activity> updateActivities (List<Activity> activities);
	
	List<Event> findAllEventByPlace (String place);
	
	Activity updateOneActivityByIdEvent(int idActivity, Activity activity);
	
	List<Activity> findAllActivityByInscription (int idPerson);
	
	List<Event> findEventByIdEvent (int idEvent);
	
	
}
