package eventCreationJava;

public class Person {
	private Integer id;
	private String name;
	private String firstname;
	private String login;
	private String password;
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", firstname=" + firstname + ", login=" + login + ", password="
				+ password + "]";
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
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	

}
