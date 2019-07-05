package eventCreationJava;

public class CreatePersonParameters {
	
	public Integer id;
	public String name;
	public String firstname;
	public String login;
	public String password;
	
	@Override
	public String toString() {
		return "CreatePersonParameters [id=" + id + ", name=" + name + ", firstname=" + firstname + ", login=" + login
				+ ", password=" + password + "]";
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
