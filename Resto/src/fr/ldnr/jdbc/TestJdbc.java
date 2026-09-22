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
			Article article = new Article("Disquette", "IBM", 1.43f);
			articleDao.create(article);
			
			List<Article> listArticles = articleDao.readAll();
			System.out.println("--------- Voici la liste de toutes les articles --------------");
			for (int index = 0; index < listArticles.size(); index++) {
				System.out.println(listArticles.get(index));
			}
			System.out.println("-------------------------------------------------------------");
			
			System.out.println(articleDao.read(2));
		} catch (SQLException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.severe(e.getLocalizedMessage());
		}

	}

}
