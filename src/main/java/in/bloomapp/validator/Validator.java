package in.bloomapp.validator;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import in.bloomapp.dao.FlowerManagerDAO;
import in.bloomapp.model.Flower;
import in.bloomapp.exception.DBException;
import in.bloomapp.exception.ValidFlowerException;

public class Validator {
	 private Validator(){
		//Default constructor
	 }
	/**
	 * Checks if the given category name is valid and correct.
	 * @param category
	 * @return
	 * @throws IOException
	 * @throws ValidFlowerException 
	 */
	public static boolean isCategory(String category) throws ValidFlowerException {
		boolean validity=false;
		if(category.equalsIgnoreCase("Natural")|| category.equalsIgnoreCase("Artificial")) {
			validity=true;
			return validity;	
		}
		else {		
			throw new ValidFlowerException ("Invalid category");
		}	
	}
	
	/**
	 * returns true if the flower is already available
	 * @param type
	 * @param category
	 * @return
	 * @throws SQLException 
	 * @throws DBException 
	 * @throws Exception 
	 */
	public static void flowerIsDuplicate(String type, String category) throws ValidFlowerException, DBException, SQLException {
		//returns true if the flower is already available
		FlowerManagerDAO flowerManagerDAO1=new FlowerManagerDAO();
		final List<Flower> flowers = flowerManagerDAO1.getFlower();
		for (Flower checkFlower : flowers) {
			if (checkFlower.getCategory().equalsIgnoreCase(category) && checkFlower.getType().equalsIgnoreCase(type)) {
				throw new ValidFlowerException("Flower already available");	
			}	
		}
	}
	
	/**
	 * Checks weather the given flower is available in the list or not
	 * @param category
	 * @param type
	 * @return
	 * @throws SQLException 
	 * @throws DBException 
	 * @throws Exception 
	 */
	public static Flower flowerIsExist(String category,String type) throws ValidFlowerException, DBException, SQLException {

		FlowerManagerDAO flowerManagerDAO1=new FlowerManagerDAO();
		final List<Flower> flowers = flowerManagerDAO1.getFlower();
		for(Flower item : flowers) {
		
			if (item.getType().equalsIgnoreCase(type) && item.getCategory().equalsIgnoreCase(category)) {
				return item;
			}
		
		}
		throw new ValidFlowerException("Flower not available");
	}

}
