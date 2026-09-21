/**
 * 
 */
package fr.ldnr.flux;

/**
 * 
 */
public interface Dao<T> {
	public void create(T obj);
	
	public void read(T obj);
	
	public void update(T obj);
	
	public void delete(T obj);
}
