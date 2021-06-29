package in.bloomapp.validator;

public class OrderValidator {

	private OrderValidator() {
	}
	/**
	 * proposes a constrains for order which needs approval
	 * @param price
	 * @param quantity
	 * @return
	 */
	public static boolean orderLimit(int price,int quantity) {
		boolean limit=true;
		if(price>7000 || quantity>10) {
			limit= false;		
			}
		return limit;
	}
}
