package be.afelio.teamZDRR.eventCreationJava.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import be.afelio.teamZDRR.eventCreationJava.util.json.LocalDateDeserializer;
import be.afelio.teamZDRR.eventCreationJava.util.json.LocalDateSerializer;

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
	private String place;
	private List<Activity> activities = new ArrayList<>();
	
	
	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", startEvent=" + startEvent
				+ ", endEvent=" + endEvent + ", idResponsable=" + idResponsable + ", place=" + place + ", activities="
				+ activities + "]";
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
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}
