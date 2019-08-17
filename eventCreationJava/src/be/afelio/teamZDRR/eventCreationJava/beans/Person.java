package be.afelio.teamZDRR.eventCreationJava.beans;

public class Person {
	private Integer id;
	private String name;
	private String firstname;
	private String email;
	private String password;
	
	/*public Person(Integer id, String name, String firstname, String email, String password) {
		this.id = id;
		this.name = name;
		this.firstname = firstname;
		this.email = email;
		this.password = password;
	}*/

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", firstname=" + firstname + ", email=" + email + ", password="
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	

}
