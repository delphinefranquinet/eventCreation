package eventCreationJava;

import java.sql.Timestamp;

public class Event {
	private Integer id;
	private String name;
	private String description;
	private Timestamp startActivity;
	private Timestamp endActivity;
	
	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", startActivity="
				+ startActivity + ", endActivity=" + endActivity + "]";
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

	public Timestamp getStartActivity() {
		return startActivity;
	}

	public void setStartActivity(Timestamp startActivity) {
		this.startActivity = startActivity;
	}

	public Timestamp getEndActivity() {
		return endActivity;
	}

	public void setEndActivity(Timestamp endActivity) {
		this.endActivity = endActivity;
	}
	
	
	

}
