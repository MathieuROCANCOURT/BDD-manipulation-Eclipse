/**
 * 
 */
package fr.ldnr.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;

import fr.ldnr.flux.ArticleDao;

/**
 * 
 */
public class TestJdbc {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ArticleDao articleDao = new ArticleDao();
		
		try (Connection conenction = articleDao.getConnection()) {
			Optional<Article> article = articleDao.read(2);
			System.out.println(article);
		} catch (SQLException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.severe(e.getLocalizedMessage());
		}
		
	}

}
