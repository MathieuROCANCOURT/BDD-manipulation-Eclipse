/**
 * 
 */
package fr.ldnr.flux;

import java.util.Optional;
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
	public void create(Article obj) {
	}

	@Override
	public Article read(int id) throws SQLException {
		Article article = new Article();
		String execute = "SELECT Description, Brand, UnitaryPrice FROM t_articles WHERE idArticle = ?;";

		try (PreparedStatement ps = this.getConnection().prepareStatement(execute)) {
			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					article = new Article(id, rs.getString("Description"), rs.getString("Brand"),
							rs.getFloat("UnitaryPrice"));
				}
			}
		} catch (SQLException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.warning(e.getLocalizedMessage());
		}
		return article;
	}

	public void update(Article obj) {
	}

	public void delete(Article obj) {
	}

}
