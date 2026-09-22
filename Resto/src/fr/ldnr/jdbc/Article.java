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
	
	public Article() {
	}
	
	public Article(int id, String description, String brand, float price) {
		this.id = id;
		this.description = description;
		this.brand = brand;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Article " + this.id + ": " + this.description + ", marque: " + this.brand + ", prix: " + this.price + '.';
	}
}
