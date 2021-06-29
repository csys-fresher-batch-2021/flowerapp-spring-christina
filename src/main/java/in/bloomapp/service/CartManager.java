package in.bloomapp.service;

import java.util.ArrayList;
import java.util.List;
import in.bloomapp.dao.CartManagerDAO;
import in.bloomapp.exception.DBException;
import in.bloomapp.model.Flower;
import in.bloomapp.validator.CartValidation;

public class CartManager {
	
	private CartManager() {
		
	}
	/**
	 * Adds the item to the cart
	 * if the item is already available updates the quantity
	 * @param item
	 * @param username
	 * @return
	 */
	public static boolean addToCart(Flower item) throws DBException{
		CartManagerDAO cartManagerDAO = new CartManagerDAO();
		if (CartValidation.isAddedToCart(item)) {
			cartManagerDAO.update(item);
		} 
		else {
			cartManagerDAO.save(item);
		}
		return true;
	}
	
	/**
	 * Deletes the item from cart
	 * @param item
	 * @throws DBException
	 */
	public static void deleteFromCart(Flower item) throws DBException {
		CartManagerDAO cartManagerDAO = new CartManagerDAO();
		cartManagerDAO.delete(item);	
	}
	
	/**
	 * To reduce quantity in cart
	 * @param item
	 * @throws DBException
	 */
	public static void reduceQuantity(Flower item) throws DBException {	
		CartManagerDAO cartManagerDAO = new CartManagerDAO();
		if(item.getQuantity()>1) {
		item.setQuantity(item.getQuantity() - 1);
		cartManagerDAO.update(item);}
		else {
			cartManagerDAO.delete(item);
		}		
	}
	
	/**
	 * Gets the cart list
	 * @return
	 * @throws DBException 
	 */
	public static List<Flower> getOrder(String userName) throws DBException {	
		CartManagerDAO cartManagerDAO = new CartManagerDAO();
		List<Flower> cart= cartManagerDAO.getCart(userName);
		List<Flower> buyerzCart= new ArrayList<>();
		for (Flower item:cart) {
			if(item.getBuyer().equals(userName)){			
				buyerzCart.add(item);
			}
		}
		return buyerzCart;
	}	
}
