/**
 * 
 */
package fr.ldnr.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
			List<Article> listArticles = articleDao.readAll();
			System.out.println("--------- Voici la liste de toutes les articles --------------");
			for (int index = 0; index < listArticles.size(); index++) {
				System.out.println(listArticles.get(index));
			}
			System.out.println("-------------------------------------------------------------");
			
			Article article = articleDao.read(2);
			System.out.println(article);
		} catch (SQLException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.severe(e.getLocalizedMessage());
		}

	}

}
