package in.bloomapp.validator;

import java.util.List;
import in.bloomapp.dao.CartManagerDAO;
import in.bloomapp.exception.DBException;
import in.bloomapp.model.Flower;

public class CartValidation {
	private CartValidation(){	
	}	

	/**
	 * Checks if the product is already available in the cart and if available updates quantity to plus one
	 * @param flower
	 * @return
	 * @throws DBException 
	 */
	public static boolean isAddedToCart(Flower flower) throws DBException {
		boolean isAdded=false;
		CartManagerDAO cartManagerDAO=new CartManagerDAO();
		List<Flower> order=cartManagerDAO.getCart(flower.getBuyer());
		for (Flower item:order) {	
			if( (flower.getCategory().equals(item.getCategory()))&& (flower.getType().equals(item.getType()))) {
				flower.setQuantity(item.getQuantity() + 1);
				isAdded=true;
			}
	}
		return isAdded;
	}
}