package be.afelio.teamZDRR.eventCreationJava.tests;

import be.afelio.teamZDRR.eventCreationJava.impl.EventRepositoryImpl;
import be.afelio.teamZDRR.eventCreationJava.model.EventRepository;

public class Factory {

	public static EventRepository CreateEventRepository() {
		return new EventRepositoryImpl(
				"postgres",
				"postgres",
				"jdbc:postgresql://localhost:5432/EventInscription");
	}
}
