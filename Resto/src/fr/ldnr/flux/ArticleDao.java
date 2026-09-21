/**
 * 
 */
package fr.ldnr.flux;

import java.sql.Statement;
import java.sql.Connection;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Article read(int idj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Article obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Article obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
