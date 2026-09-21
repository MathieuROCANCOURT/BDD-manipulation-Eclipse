/**
 * 
 */
package fr.ldnr.jdbc;

/**
 * 
 */
public class Article {
	private int id;
	private String description;
	private String brand;
	private float price;
	
	public Article(int id, String description, String brand, float price) {
		this.id = id;
		this.description = description;
		this.brand = brand;
		this.price = price;
	}
}
