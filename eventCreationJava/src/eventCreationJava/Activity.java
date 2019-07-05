package eventCreationJava;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eventCreationJava.utilJson.LocalDateDeserializer;
import eventCreationJava.utilJson.LocalDateSerializer;

public class Activity {
	private Integer id;
	private String name;
	private String description;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDateTime startActivity;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDateTime endActivity;
	private Integer idEvent;
	//private Event event;
	
	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", description=" + description + ", startActivity="
				+ startActivity + ", endActivity=" + endActivity + ", idEvent=" + idEvent + "]";
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
	public Integer getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}
	
	
	


}
