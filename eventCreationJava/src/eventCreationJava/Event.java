package eventCreationJava;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Event {
	
	private String name;
	private String description;
	private LocalDateTime startEvent;
	private LocalDateTime endEvent;
	//private Person person;
	//private List<Activity> activities;
	
	@Override
	public String toString() {
		return "Event [name=" + name + ", description=" + description + ", startEvent=" + startEvent + ", endEvent="
				+ endEvent + "]";
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getStartEvent() {
		return startEvent;
	}
	public void setStartEvent(LocalDateTime startEvent) {
		this.startEvent = startEvent;
	}
	public LocalDateTime getEndEvent() {
		return endEvent;
	}
	public void setEndEvent(LocalDateTime endEvent) {
		this.endEvent = endEvent;
	}
	public Person getPerson() {//i will find the responsible
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	

}
