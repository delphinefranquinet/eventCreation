package eventCreationJava;

import java.sql.SQLException;

public class Test {

	public static void main(String[] args) throws SQLException {
		
		try {
		EventRepositoryImpl repository = new EventRepositoryImpl("postgres", "postgres", "jdbc:postgresql://localhost:5432/EventInscription");
		InscriptionActivity newInscriptionActivity = new InscriptionActivity();
		newInscriptionActivity.setIdActivity(10);
		newInscriptionActivity.setIdPerson(2);
		
				
		InscriptionActivity newInscription = repository.CreateNewInscriptionActivity(newInscriptionActivity);
		System.out.println("ok");
		}catch(Exception e) {
			System.out.println("erreur");
		}
		
		
		
	}

}
