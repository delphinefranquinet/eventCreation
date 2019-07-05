package eventCreationJava;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


import eventCreationJava.utilJson.LocalDateDeserializer;
import eventCreationJava.utilJson.LocalDateSerializer;

public class Event implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String description;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDateTime startEvent;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDateTime endEvent;
	private Integer idResponsable;
	
	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", startEvent=" + startEvent
				+ ", endEvent=" + endEvent + ", idResponsable=" + idResponsable + "]";
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
	public Integer getIdResponsable() {
		return idResponsable;
	}
	public void setIdResponsable(Integer idResponsable) {
		this.idResponsable = idResponsable;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
