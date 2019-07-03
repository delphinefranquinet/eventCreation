package eventCreationJava;

import java.time.LocalDateTime;

public class CreateEventParameters {
	
	
	public String name;
	public String description;
	public LocalDateTime startActivity;
	public LocalDateTime endActivity;
	
	
	@Override
	public String toString() {
		return "CreateEventParameters [name=" + name + ", description=" + description + ", startActivity="
				+ startActivity + ", endActivity=" + endActivity + "]";
	}
	

	
}
