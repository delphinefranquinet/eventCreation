package eventCreationJava;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Activity {
	private Integer id;
	private String name;
	private String description;
	private LocalDateTime startActivity;
	private LocalDateTime endActivity;
	private Event event;
	
	
	
	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", description=" + description + ", startActivity="
				+ startActivity + ", endActivity=" + endActivity + ", event=" + event + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public LocalDateTime getStartActivity() {
		return startActivity;
	}
	public void setStartActivity(LocalDateTime startActivity) {
		this.startActivity = startActivity;
	}
	public LocalDateTime getEndActivity() {
		return endActivity;
	}
	public void setEndActivity(LocalDateTime endActivity) {
		this.endActivity = endActivity;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	



}
