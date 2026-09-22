/**
 * 
 */
package fr.ldnr.flux;

import java.util.logging.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.ldnr.jdbc.Article;

/**
 * 
 */
public class ArticleDao implements Dao<Article> {
	// Database connection details (injected or configured externally in real apps)
	private static final String URL = "jdbc:mariadb://127.0.0.1/shop";
	private static final String LOGIN = "root";
	private static final String PASSWORD = System.getProperty("database.password");

	// Helper method to get a database connection (simplified for example)
	public Connection getConnection() throws SQLException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.severe(e.getLocalizedMessage());
		}

		return DriverManager.getConnection(URL, LOGIN, PASSWORD);
	}
	/**
	 * 
	 */
	public ArticleDao(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Article obj) {
		return false;
	}

	@Override
	public Article read(int id) throws SQLException {
		Article article = new Article();
		Statement statement = null;
		String execute = "SELECT Description, Brand, UnitaryPrice FROM articles WHERE idArticle = ?";

		try (PreparedStatement ps = this.connect.prepareStatement(execute)) {
			ps.setInt(1, id);
			statement = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeQuery(execute);
			if (statement.getResultSet().first()) {
				article = new Article(id, statement.getResultSet().getString("Description"),
						statement.getResultSet().getString("Brand"), statement.getResultSet().getFloat("UnitaryPrice"));
			}
		} catch (SQLException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.warning(e.getLocalizedMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
		return article;
	}

	@Override
	public boolean update(Article obj) {
		return false;
	}

	@Override
	public boolean delete(Article obj) {
		return false;
	}

}
