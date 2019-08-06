package be.afelio.teamZDRR.eventCreationJava.beans;

public class CreateLoginParameters {
	
	public String email;
	public String password;
	
	
	@Override
	public String toString() {
		return "CreateLoginParameters [email=" + email + ", password=" + password + "]";
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
	
	
	

}
