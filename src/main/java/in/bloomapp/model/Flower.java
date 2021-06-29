package in.bloomapp.model;

import java.time.LocalDate;

/**
 * Main model class with required field
 * @author chri2631
 *
 */
public class Flower {
	private String category;
	private String type;
	private int price;
	private int quantity;
	private String buyer;
	private LocalDate date;
	
	public String getCategory() {
		return category;
	}
	public String getType() {
		return type;
	}
	public int getPrice() {
		return price;
	}	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getBuyer() {
		return buyer;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Flower [category=" + category + ", type=" + type + ", price=" + price + ", quantity=" + quantity
				+ ", buyer=" + buyer + ", date=" + date + "]";
	}	
}
