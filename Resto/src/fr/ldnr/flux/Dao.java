/**
 * 
 */
package fr.ldnr.flux;

import java.sql.SQLException;
import java.util.Optional;

/**
 * 
 */
public interface Dao<T> {
	public void create(T t);

	public T read(int id) throws SQLException;

	public void update(T t);

	public void delete(T t);
}
