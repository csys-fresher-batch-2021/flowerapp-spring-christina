package in.bloomapp.service;

import java.time.LocalDate;
import java.util.List;
import in.bloomapp.dao.OrderSummaryDAO;
import in.bloomapp.exception.DBException;
import in.bloomapp.exception.ServiceException;
import in.bloomapp.model.Flower;
import in.bloomapp.model.Order;

public class SummaryManager {
	private SummaryManager() {}
	
	/**
	 * Gets the total orders for the date respective of the flowers
	 * @param summaryDate
	 * @return
	 * @throws ServiceException
	 */
	public static List<Flower> orderSummary(LocalDate summaryDate) throws ServiceException {
		List<Flower> summary=null;
		OrderSummaryDAO orderSummaryDAO=new OrderSummaryDAO();
		try {
			summary=orderSummaryDAO.getflowersOrdered(summaryDate);
		}catch (DBException e) {
			throw new ServiceException("Database not responding");
		}
		return summary;
	}
	
	/**
	 * Gets the orders which is to be delivered 
	 * @return
	 * @throws ServiceException
	 */
	public static List<Order> getOrders() throws ServiceException {
		List<Order> deliveryList=null;
		OrderSummaryDAO orderSummaryDAO=new OrderSummaryDAO();
		try {
			deliveryList =orderSummaryDAO.getOrderList();
		} catch (DBException e) {
			throw new ServiceException("Database not responding");
		}
		return deliveryList;
	}
	
	/**
	 * Gets order summary for a user this method is for user
	 * @param userName
	 * @return
	 * @throws DBException 
	 */
	public static List<Order> getOrderSummary(String userName) throws DBException {
		List<Order> orderSummary=null;
		OrderSummaryDAO orderSummaryDAO=new OrderSummaryDAO();
		try {
			orderSummary =orderSummaryDAO.getUserOrder(userName);
		} catch (DBException e) {
			throw new DBException(e.getMessage());
		}
		return orderSummary;	
	}
	
	/**
	 * gets ordered items
	 * @param userName
	 * @return
	 * @throws DBException 
	 */
	public static List<Order> getOrderList(String userName) throws DBException{
		List<Order> orderList=null;
		OrderSummaryDAO orderSummaryDAO=new OrderSummaryDAO();
		try {
			orderList =orderSummaryDAO.getOrderItems(userName);
		} catch (DBException e) {
			throw new DBException(e.getMessage());
		}
		return orderList;	
	}
	
	/**
	 * Gets the items which are rejected by the admin
	 * @param userName
	 * @return
	 */
	public static List<Order> rejectedItems(String userName){
		OrderSummaryDAO orderSummaryDAO=new OrderSummaryDAO();
		List<Order> list = null;
		try {
			list = orderSummaryDAO.getRejectedItems(userName);
		} catch (DBException e) {
			e.printStackTrace();
		}
		return list;
	}
}