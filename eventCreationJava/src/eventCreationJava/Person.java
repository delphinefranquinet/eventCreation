package eventCreationJava;

public class Person {
	private Integer id;
	private String name;
	private String firstname;
	

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
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", firstname=" + firstname + "]";
	}
	

}
