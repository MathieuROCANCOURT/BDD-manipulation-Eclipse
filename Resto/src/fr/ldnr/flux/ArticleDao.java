/**
 * 
 */
package fr.ldnr.flux;

import java.util.logging.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.ldnr.jdbc.Article;

/**
 * 
 */
public class ArticleDao extends Dao<Article> {
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
