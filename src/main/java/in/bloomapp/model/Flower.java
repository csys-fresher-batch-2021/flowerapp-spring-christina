package in.bloomapp.model;

import java.time.LocalDate;

import lombok.Data;

/**
 * Main model class with required field
 * 
 * @author chri2631
 *
 */
@Data
public class Flower {
	private String category;
	private String type;
	private int price;
	private int quantity;
	private String buyer;
	private LocalDate date;

}
