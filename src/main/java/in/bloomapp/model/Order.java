package in.bloomapp.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

/**
 * Contains all fields that is necessary for the order contains getters and
 * setters for the fields
 * 
 * @author chri2631
 *
 */

@Data
public class Order {
	private String orderCategory;
	private String orderType;
	private int orderPrice;
	private int orderQuantity;
	private String deliveryCity;
	private String deliverAddress;
	private LocalDate deliveryDate;
	private LocalTime deliveryTime;
	private String userName;
	private Long userMobileNo;
	private LocalDate orderDate;
	private int status = 1;
	private String deliveryStatus;
	private int orderId;
}
