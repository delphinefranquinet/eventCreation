package eventCreationJava;


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

	public boolean CreatePerson(Person person) {
		boolean created = false;

		String sql = "INSERT INTO persons Values (DEFAULT, ?, ?)";
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql);) {
			// connection.setAutoCommit(true);

			connection.setAutoCommit(false);
			query.setString(1, person.getName());
			query.setString(2, person.getFirstname());
			query.executeUpdate(); // insert/update/delete
			int updatedRows = query.getUpdateCount();
			connection.commit();

			created = updatedRows > 0;
		} catch (java.sql.SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return created;
	}

	public boolean CreateEvent(Event newEvent) {

		boolean created = false;

		String sql = "insert into \"Events\" values (default, ?, ?, ?, ?, ? )";
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql);) {
			// connection.setAutoCommit(true);

			connection.setAutoCommit(false);
			query.setString(1, newEvent.getName());
			query.setString(2, newEvent.getDescription());
			query.setTimestamp(3, newEvent.getStartActivity());
			query.setTimestamp(4, newEvent.getEndActivity());
			query.setInt(5, newEvent.getPerson().getId());
			query.executeUpdate(); // insert/update/delete
			int updatedRows = query.getUpdateCount();
			connection.commit();

			created = updatedRows > 0;
		} catch (java.sql.SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return created;
	}
	
	public boolean CreateActivity(Activity newActivity) {

		boolean created = false;

		String sql = "INSERT INTO \"Activities\" Values (default, ?, ?, ?, ?, ?)";
		try (java.sql.Connection connection = java.sql.DriverManager.getConnection(url, user, password);
				java.sql.PreparedStatement query = connection.prepareStatement(sql);) {
			// connection.setAutoCommit(true);

			connection.setAutoCommit(false);
			query.setString(1, newActivity.getName());
			query.setString(2, newActivity.getDescription());
			query.setTimestamp(3, newActivity.getStartActivity());
			query.setTimestamp(4, newActivity.getEndActivity());
			query.setInt(5, newActivity.getEvent().getId());
			query.executeUpdate(); // insert/update/delete
			int updatedRows = query.getUpdateCount();
			connection.commit();

			created = updatedRows > 0;
		} catch (java.sql.SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return created;
	}

}
