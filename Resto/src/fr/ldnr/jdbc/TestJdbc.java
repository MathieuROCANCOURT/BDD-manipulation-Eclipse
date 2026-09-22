/**
 * 
 */
package fr.ldnr.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

		try (Connection conenction = articleDao.getConnection();
				PreparedStatement st = articleDao.getConnection()
						.prepareStatement("ALTER TABLE t_articles AUTO_INCREMENT = 1");) {
			st.executeUpdate();

			Article article = new Article("Disquette", "IBM", 1.43f);
			articleDao.create(article);

			List<Article> listArticles = articleDao.readAll();
			System.out.println("--------- Voici la liste de toutes les articles --------------");
			for (int index = 0; index < listArticles.size(); index++) {
				System.out.println(listArticles.get(index));
			}
			System.out.println("-------------------------------------------------------------");

			article.setDescription("CD-ROM 32 bits");
			articleDao.update(article);

			System.out.println(articleDao.read(2));

			for (int index = 12; index < listArticles.size(); index++) {
				articleDao.delete(listArticles.get(index));
			}
		} catch (SQLException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.severe(e.getLocalizedMessage());
		}

	}

}
