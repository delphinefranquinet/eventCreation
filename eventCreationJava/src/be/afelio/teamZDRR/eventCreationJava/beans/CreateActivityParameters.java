package be.afelio.teamZDRR.eventCreationJava.beans;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import be.afelio.teamZDRR.eventCreationJava.util.json.LocalDateDeserializer;
import be.afelio.teamZDRR.eventCreationJava.util.json.LocalDateSerializer;

public class CreateActivityParameters {
	
	public Integer id;
	public String name;
	public String description;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDateTime startActivity;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDateTime endActivity;
	public Integer idEvent;
	
	
	@Override
	public String toString() {
		return "CreateActivityParameters [id=" + id + ", name=" + name + ", description=" + description
				+ ", startActivity=" + startActivity + ", endActivity=" + endActivity + ", idEvent=" + idEvent + "]";
	}
	
	

}
