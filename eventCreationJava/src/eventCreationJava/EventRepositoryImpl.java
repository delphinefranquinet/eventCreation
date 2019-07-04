package eventCreationJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class EventRepositoryImpl {

	protected String user;
	protected String password;
	protected String url;

	public EventRepositoryImpl(String user, String password, String url) {
		super();
		this.user = user;
		this.password = password;
		this.url = url;
	}

	public Person connexion(String login, String mot_de_passe) {

		Person person = null;

		String sql = "Select id_person, \"namePerson\", \"firstnamePerson\" From persons Where login = ? AND password = ?";
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql);) {
			query.setString(1, login);
			query.setString(2, mot_de_passe);
			try (ResultSet resultSet = query.executeQuery()) {
				if (resultSet.next()) {

					person = createPerson(resultSet);

				}
			}
		} catch (java.sql.SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return person;
	}

	protected Person createPerson(ResultSet rs) throws SQLException {

		Person person = new Person();
		person.setId(rs.getInt("id_person"));
		person.setName(rs.getString("namePerson"));
		person.setFirstname(rs.getString("firstnamePerson"));
		return person;
	}

	public boolean CreateNewPerson(Person person) {
		boolean created = false;

		String sql = "INSERT INTO persons Values (DEFAULT, ?, ?)";
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql);) {
			// connection.setAutoCommit(true);

			connection.setAutoCommit(false);// pour etre certain que les 2 aboutissent, sinon que les 2 échouent.
			query.setString(1, person.getName());
			query.setString(2, person.getFirstname());
			query.executeUpdate(); // insert/update/delete
			int updatedRows = query.getUpdateCount(); // combien de ligne ont été mise à jour
			connection.commit();

			created = updatedRows > 0;
		} catch (java.sql.SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return created;
	}

	public Event CreateNewEvent(Event newEvent) {
	
		//Timestamp timestamp = Timestamp.valueOf(localDateTime); Convertir localDateTime en Timestamp
		
		//boolean created = false;

		String sql = "insert into \"Events\" values (default, ?, ?, ?, ?, ? )";
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql);) {
			// connection.setAutoCommit(true);

			connection.setAutoCommit(false);// pour etre certain que les 2 aboutissent, sinon que les 2 échouent.
			query.setString(1, newEvent.getName());
			query.setString(2, newEvent.getDescription());
			query.setTimestamp(3, Timestamp.valueOf(newEvent.getStartEvent())); //Timestamp.valueOf(startEvent)
			query.setTimestamp(4, Timestamp.valueOf(newEvent.getEndEvent())); //Timestamp.valueOf(endEvent)
			query.setInt(5, newEvent.getIdResponsable());
			query.executeUpdate(); // insert/update/delete
			//int updatedRows = query.getUpdateCount();
			connection.commit();

			//created = updatedRows > 0;
		} catch (java.sql.SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return newEvent;
	}

	public boolean CreateNewActivity(Activity newActivity) {

		boolean created = false;

		String sql = "INSERT INTO \"Activities\" Values (default, ?, ?, ?, ?, ?)";
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql);) {
			// connection.setAutoCommit(true);

			connection.setAutoCommit(false);
			query.setString(1, newActivity.getName());
			query.setString(2, newActivity.getDescription());
			//query.setTimestamp(3, newActivity.getStartActivity()).;
			//query.setTimestamp(4, newActivity.getEndActivity());
			// query.setInt(5, newActivity.getEvent().getId());
			query.executeUpdate(); // insert/update/delete
			int updatedRows = query.getUpdateCount();
			connection.commit();

			created = updatedRows > 0;
		} catch (java.sql.SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return created;
	}

	public List<Event> FindAllEvents() {
	
		List<Event> events = new ArrayList<Event>();;
		
		try (Connection c = DriverManager.getConnection(url, user, password);
                Statement s = c.createStatement();
                ResultSet rs = s.executeQuery("Select \"eventName\", description, \"dateDebut\", \"dateFin\" From \"Events\"")) {
		while (rs.next()) {
               Event event = new Event();
              event.setName(rs.getString("eventName"));
              event.setDescription(rs.getString("description"));
              event.setStartEvent(rs.getTimestamp("dateDebut").toLocalDateTime());
              event.setEndEvent(rs.getTimestamp("dateFin").toLocalDateTime());
              events.add(event);
            
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new RuntimeException(sqle);
        }

        return events;
			
	}

}
