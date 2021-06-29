package in.bloomapp.service;

import java.util.List;

import in.bloomapp.dao.CartManagerDAO;
import in.bloomapp.dao.OrderPlacementDAO;
import in.bloomapp.exception.DBException;
import in.bloomapp.exception.InvalidInputException;
import in.bloomapp.model.Flower;
import in.bloomapp.model.Order;
import in.bloomapp.util.BasicValidator;
import in.bloomapp.validator.OrderValidator;

/**
 * manages order placement
 * @author chri2631
 *
 */
public class OrderProcedureManager {

	private OrderProcedureManager() {	
	}
	
	/**
	 * Add order form cart to the order data
	 * @param order
	 * @throws DBException
	 * @throws InvalidInputException 
	 */
	public static void addOrder(Order order) throws DBException, InvalidInputException {
		CartManagerDAO cartManagerDAO=new CartManagerDAO();
		List<Flower> cart=cartManagerDAO.getCart(order.getUserName());
		BasicValidator.isValidString(order.getDeliveryCity());
		BasicValidator.isValidString(order.getDeliverAddress());
		for(Flower cartItem:cart) {
		order.setOrderCategory(cartItem.getCategory());
		order.setOrderType(cartItem.getType());
		order.setOrderPrice(cartItem.getPrice());
		order.setOrderQuantity(cartItem.getQuantity());
		if(OrderValidator.orderLimit(order.getOrderPrice(),order.getOrderQuantity())) {
			order.setDeliveryStatus("yetToDeliver");
		}
		else {
			order.setDeliveryStatus("yetToApprove");
		}
		OrderPlacementDAO orderPlacementDAO=new OrderPlacementDAO();
		orderPlacementDAO.add(order);
		}
	}

	/**
	 * List items that is to be approved from the user
	 * @return
	 */
	public static List<Order> approveItem(){
		OrderPlacementDAO orderPlacementDAO=new OrderPlacementDAO();
		List<Order> list = null;
		try {
			list = orderPlacementDAO.toApprove();
		} catch (DBException e) {
			e.printStackTrace();
		}
		return list;
	}
}
