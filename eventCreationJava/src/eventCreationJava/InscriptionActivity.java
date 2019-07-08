package eventCreationJava;

public class InscriptionActivity {
	
	private Integer idInscriptionActivity;
	private Integer idPerson;
	private Integer idActivity;
	
	@Override
	public String toString() {
		return "InscriptionActivity [idInscriptionActivity=" + idInscriptionActivity + ", idPerson=" + idPerson
				+ ", idActivity=" + idActivity + "]";
	}
	public Integer getIdInscriptionActivity() {
		return idInscriptionActivity;
	}
	public void setIdInscriptionActivity(Integer idInscriptionActivity) {
		this.idInscriptionActivity = idInscriptionActivity;
	}
	public Integer getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}
	public Integer getIdActivity() {
		return idActivity;
	}
	public void setIdActivity(Integer idActivity) {
		this.idActivity = idActivity;
	}
	
	

}
