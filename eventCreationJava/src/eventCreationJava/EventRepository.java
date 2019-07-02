package eventCreationJava;

public interface EventRepository {

	boolean CreatePerson(Person person);
	boolean CreateEvent(Event newEvent);
	boolean CreateActivity(Activity newActivity);
	
}
