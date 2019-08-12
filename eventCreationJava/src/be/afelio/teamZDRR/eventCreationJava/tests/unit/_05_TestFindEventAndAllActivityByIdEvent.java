package be.afelio.teamZDRR.eventCreationJava.tests.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import be.afelio.teamZDRR.eventCreationJava.beans.Event;
import be.afelio.teamZDRR.eventCreationJava.model.EventRepository;

class _05_TestFindEventAndAllActivityByIdEvent {

	@Test
	void test() {
		EventRepository repository = Factory.CreateEventRepository();
		assertNotNull(repository);
		
		Event expected = new Event();
		expected.setId(6);
		expected.setName("Barbecue Afelio");
		expected.setDescription("On a r�ussi le projet donc on est content (1 activit� / pas d'inscrit)");
		expected.setStartEvent(LocalDateTime.of(2019, 8, 9, 18, 00, 00));
		expected.setEndEvent(LocalDateTime.of(2019, 8, 9, 22, 00, 00));
		expected.setIdResponsable(4);
		expected.setPlace("Afelio");
		
		
		
		Event actual = repository.findEventAndAllActivityByIdEvent(6);
		
	}

}