package be.afelio.teamZDRR.eventCreationJava.tests.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import be.afelio.teamZDRR.eventCreationJava.beans.Person;
import be.afelio.teamZDRR.eventCreationJava.model.EventRepository;

class _01_TestConnexion {

	@Test
	void testExistingPerson() {
		EventRepository repository = Factory.CreateEventRepository();
		assertNotNull(repository);
		
		Person expected = new Person();
				
		expected.setId(1);
		expected.setName("Franquinet");
		expected.setFirstname("Delphine");
		expected.setEmail("delphine@nomail.be");
		expected.setPassword("1234");
		
		
		Person actual = repository.connexion("delphine@nomail.be", "1234");
		
		assertNotNull(actual);
		assertEquals(expected.getId(), expected.getId());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getFirstname(), actual.getFirstname());
		assertEquals(expected.getEmail(), actual.getEmail());
		assertEquals(expected.getPassword(), actual.getPassword());
		
	}
	
	@Test
	void testNonExistingPerson() {
		EventRepository repository = Factory.CreateEventRepository();
		assertNotNull(repository);
		
		Person actual = repository.connexion("xxx", "yyy");
		assertNull(actual);	
	}
	
	@Test
	void testNullEmail() {
		
		EventRepository repository = Factory.CreateEventRepository();
		assertNotNull(repository);
		
		Person actual = repository.connexion(null, "1234");
		assertNull(actual);	
	}
	
	@Test
	void testNullPassword() {
		
		EventRepository repository = Factory.CreateEventRepository();
		assertNotNull(repository);
		
		Person actual = repository.connexion("delphine@nomail.be", null);
		assertNull(actual);	
	}

}
