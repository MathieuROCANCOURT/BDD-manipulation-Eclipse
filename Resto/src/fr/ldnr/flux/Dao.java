/**
 * 
 */
package fr.ldnr.flux;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 
 */
abstract class Dao<T> {
	protected Connection connect = null;

	protected Dao(Connection conn){
	    this.connect = conn;
	}

	abstract boolean create(T obj);

	abstract T read(int id) throws SQLException;

	abstract boolean update(T obj);

	abstract boolean delete(T obj);
}
