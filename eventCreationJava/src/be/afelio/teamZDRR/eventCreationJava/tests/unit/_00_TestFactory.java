package be.afelio.teamZDRR.eventCreationJava.tests.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import be.afelio.teamZDRR.eventCreationJava.model.EventRepository;

class _00_TestFactory {

	@Test
	void test() {
		EventRepository manager = Factory.CreateEventRepository();
		assertNotNull(manager);
	}

}
