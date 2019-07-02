package eventCreationJava;

public interface EventRepository {

	boolean CreateNewPerson(Person person);
	boolean CreateNewEvent(Event newEvent);
	boolean CreateNewActivity(Activity newActivity);
	
}
