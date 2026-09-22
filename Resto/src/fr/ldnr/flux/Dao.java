/**
 * 
 */
package fr.ldnr.flux;

import java.sql.SQLException;
import java.util.List;

/**
 * 
 */
public interface Dao<T> {
	public void create(T t);
	
	public List<T> readAll() throws SQLException;

	public T read(int id) throws SQLException;

	public void update(T t);

	public void delete(T t);
}
