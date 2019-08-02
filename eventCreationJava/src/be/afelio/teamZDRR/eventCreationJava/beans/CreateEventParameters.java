package be.afelio.teamZDRR.eventCreationJava.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import be.afelio.teamZDRR.eventCreationJava.util.json.LocalDateDeserializer;
import be.afelio.teamZDRR.eventCreationJava.util.json.LocalDateSerializer;

public class CreateEventParameters {
	
	public Integer id;
	public String name;
	public String description;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDateTime startEvent;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDateTime endEvent;
	public Integer idResponsable;
	public String place;
	public List<Activity> activities = new ArrayList<>();
	
	@Override
	public String toString() {
		return "CreateEventParameters [id=" + id + ", name=" + name + ", description=" + description + ", startEvent="
				+ startEvent + ", endEvent=" + endEvent + ", idResponsable=" + idResponsable + ", place=" + place
				+ ", activities=" + activities + "]";
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
	
	
	
	
	

}
