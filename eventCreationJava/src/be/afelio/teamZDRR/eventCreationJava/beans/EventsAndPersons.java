package be.afelio.teamZDRR.eventCreationJava.beans;

import java.util.List;

public class EventsAndPersons {

	private List<Event> events;
	private List<Person> persons;
	
	@Override
	public String toString() {
		return "EventsAndPersons [events=" + events + ", persons=" + persons + "]";
	}
	
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	
}
