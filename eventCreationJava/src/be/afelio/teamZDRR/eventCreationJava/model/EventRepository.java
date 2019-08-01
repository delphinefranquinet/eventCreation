package be.afelio.teamZDRR.eventCreationJava.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import be.afelio.teamZDRR.eventCreationJava.beans.Activity;
import be.afelio.teamZDRR.eventCreationJava.beans.Event;
import be.afelio.teamZDRR.eventCreationJava.beans.Person;

public interface EventRepository {

	Person connexion(String email, String mot_de_passe);
	
	Person createPerson(ResultSet rs) throws SQLException;
	
	Person createNewPerson(Person newPerson);
	
	Event createNewEvent(Event newEvent);
	
	Activity createNewActivity(Activity newActivity);
	
	List<Event> FindAllEvents();
	
	Event findEventAndAllActivityByIdEvent(int id);
	
	boolean createNewInscriptionActivity(Integer idPerson, Integer idActivity) throws SQLException;
	
	Event updateEventByIdEvent(int idResponsable, int idEvent, Event newEvent);
	
	boolean deleteEventByIdEvent(int idEvent);
	
	List<Activity> findAllActivitiesByIdEvent(int idEvent);
	
	List<Event> findEventAndAllActivityByIdResponsable(int idResponsable);
	
	List<Event> findEventAndAllActivityByEventName(String eventName);
	
	List<Integer> findIdActivityByIdPerson(int idPerson);
	
	List<LocalDateTime> findAllLocalDateTimeStartByIdActivities(int idPerson);
	
	List<LocalDateTime> findAllLocalDateTimeEndByIdActivities(int idPerson);
	
	LocalDateTime findLocalDateTimeStartNewActivity(int idActivity);
	
	LocalDateTime findLocalDateTimeEndNewActivity(int idActivity);
	
	boolean LocalDateTimeComparisonForInscription(int idPerson, int idActivity);
	
	LocalDateTime findLocalDateTimeStartEvent(int idEvent);
	
	LocalDateTime findLocalDateTimeEndEvent(int idEvent);
	
	public boolean LocalDateTimeComparisonForCreateNewActivity(int idEvent, LocalDateTime localDateTimeStartNewActivity,
			LocalDateTime localDateTimeEndNewActivity);
	
	
	
}
