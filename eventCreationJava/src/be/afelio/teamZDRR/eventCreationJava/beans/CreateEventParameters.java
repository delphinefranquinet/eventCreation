package be.afelio.teamZDRR.eventCreationJava.beans;

import java.time.LocalDateTime;

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
	
	@Override
	public String toString() {
		return "CreateEventParameters [id=" + id + ", name=" + name + ", description=" + description + ", startEvent="
				+ startEvent + ", endEvent=" + endEvent + ", idResponsable=" + idResponsable + "]";
	}

}
