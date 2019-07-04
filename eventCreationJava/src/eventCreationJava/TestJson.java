package eventCreationJava;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJson {

	public static void main(String[] args) throws IOException {
		Event event = new Event();
		
		event.setName("Delphine");
		event.setDescription("test");
		event.setStartEvent(LocalDateTime.of(2018, 12, 12, 12, 30));
		event.setEndEvent(LocalDateTime.of(2018, 12, 12, 13, 30));
		ObjectMapper mapper = new ObjectMapper();
		
		String string = mapper.writeValueAsString(event); //transformer l'objet event en string sous format json, quand on doit l'envoyer
		
		System.out.println(string);
		
		Event value = mapper.readValue(new ByteArrayInputStream(string.getBytes()), Event.class);
		// quand on le recoit, String qu'on transforme en objet
		System.out.println(value);
	}

}
