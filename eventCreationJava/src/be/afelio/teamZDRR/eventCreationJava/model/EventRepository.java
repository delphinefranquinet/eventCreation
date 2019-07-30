package be.afelio.teamZDRR.eventCreationJava.model;

import be.afelio.teamZDRR.eventCreationJava.beans.Activity;
import be.afelio.teamZDRR.eventCreationJava.beans.Event;
import be.afelio.teamZDRR.eventCreationJava.beans.Person;

public interface EventRepository {

	boolean CreateNewPerson(Person person);
	boolean CreateNewEvent(Event newEvent);
	boolean CreateNewActivity(Activity newActivity);
	
}
