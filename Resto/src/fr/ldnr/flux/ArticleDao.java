/**
 * 
 */
package fr.ldnr.flux;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	@Override
	public void create(Article article) throws SQLException {
		String execute = "INSERT INTO t_articles(Description, Brand, UnitaryPrice) VALUES (?,?,?);";

		try (PreparedStatement ps = this.getConnection().prepareStatement(execute, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, article.getDescription());
			ps.setString(2, article.getBrand());
			ps.setFloat(3, article.getPrice());
			ps.executeUpdate();

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    article.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Insert succeeded but no ID obtained.");
                }
            }
		} catch (SQLException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.warning(e.getLocalizedMessage());
		}
	}

	@Override
	public List<Article> readAll() throws SQLException {
		List<Article> listArticle = new ArrayList<Article>();
		String execute = "SELECT IdArticle, Description, Brand, UnitaryPrice FROM t_articles;";

		try (PreparedStatement ps = this.getConnection().prepareStatement(execute)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					listArticle.add(new Article(rs.getInt("IdArticle"), rs.getString("Description"),
							rs.getString("Brand"), rs.getFloat("UnitaryPrice")));
				}
			}
		} catch (SQLException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.warning(e.getLocalizedMessage());
		}
		return listArticle;
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

	@Override
	public void update(Article article) throws SQLException {
		String execute = "UPDATE t_articles SET Description = ?, Brand = ?, UnitaryPrice = ? WHERE idArticle = ?;";

		try (PreparedStatement ps = this.getConnection().prepareStatement(execute)) {
			ps.setString(1, article.getDescription());
			ps.setString(2, article.getBrand());
			ps.setFloat(3, article.getPrice());
			ps.setInt(4, article.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.warning("Error update article request: " + e.getLocalizedMessage());
		}
	}

	@Override
	public void delete(Article article) {
		String execute = "DELETE FROM t_articles WHERE idArticle = ?;";

		try (PreparedStatement ps = this.getConnection().prepareStatement(execute)) {
			ps.setInt(1, article.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.warning("Error delete article request: " + e.getLocalizedMessage());
		}
	}
}
