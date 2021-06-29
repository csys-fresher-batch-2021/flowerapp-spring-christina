package in.bloomapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import in.bloomapp.exception.DBException;
import in.bloomapp.model.Order;
import in.bloomapp.util.ConnectionUtil;

/**
 * Cares about the order placement and cancellation
 * @author chri2631
 *
 */
public class OrderPlacementDAO {

	/**
	 * Stores the order
	 * @param order
	 * @throws DBException
	 */
	public void add(Order order) throws DBException {
		Connection connection=null;
		PreparedStatement pst=null;
		try {
			connection=ConnectionUtil.getConnection();
			String sql="insert into orders (flower_id,category,name,quantity,price_per,price,delivery_city,"
					+ "delivery_address,deliver_date,delivery_time,user_id,user_name,mobile_no,"
					+ "order_date,status,delivery_status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pst=connection.prepareStatement(sql);
			CartManagerDAO cartManagerDAO=new CartManagerDAO();
			//prepares the query
			pst.setInt(1,cartManagerDAO.getFlowerId(order.getOrderCategory(),order.getOrderType()));
			pst.setString(2, order.getOrderCategory());
			pst.setString(3, order.getOrderType());
			pst.setInt(4, order.getOrderQuantity());
			pst.setInt(5, order.getOrderPrice());
			pst.setInt(6, order.getOrderPrice()*order.getOrderQuantity());
			pst.setString(7,order.getDeliveryCity());
			pst.setString(8,order.getDeliverAddress());	
			pst.setObject(9, order.getDeliveryDate());
			pst.setObject(10,order.getDeliveryTime());
			pst.setInt(11, getUserId(order.getUserName()));
			pst.setString(12,  order.getUserName());
			pst.setLong(13, getUserMobileNo(order.getUserName()));
			LocalDate orderDate=LocalDate.now();
			pst.setObject(14,orderDate);
			pst.setInt(15,1);
			pst.setString(16,order.getDeliveryStatus());
			pst.executeUpdate();
					
		}
		 catch (SQLException e) {
				throw new DBException("Unable to add order");
			} finally {
				ConnectionUtil.close(pst, connection);
				removeCart(order);
			}
	}	
	
	/**
	 * Gets the list the is to be approved from the order database
	 * @return
	 * @throws DBException
	 */
	public List<Order> toApprove() throws DBException {
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		List<Order> order=new ArrayList<>();
		try {
			connection=ConnectionUtil.getConnection();
			String sql="select category,name,quantity,price,delivery_city,delivery_address,"
					+ "deliver_date,delivery_time,user_name,mobile_no,"
					+ "order_date from orders WHERE delivery_status='yetToApprove'";
			pst=connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
			Order subject=new Order();
			subject.setOrderCategory(rs.getString("category")); 
			subject.setOrderType(rs.getString("name"));
			subject.setOrderPrice(rs.getInt("price"));
			subject.setDeliveryCity(rs.getString("delivery_city"));
			subject.setDeliverAddress(rs.getString("delivery_address"));
			subject.setDeliveryDate(LocalDate.parse(rs.getString("deliver_date")));
			Time deliveryTime=(rs.getTime("delivery_time"));
			String time=deliveryTime.toString();
			LocalTime parsedTime=LocalTime.parse(time);
			subject.setDeliveryTime(parsedTime);
			subject.setUserName(rs.getString("user_name"));
			subject.setUserMobileNo(rs.getLong("mobile_no"));
			subject.setOrderDate(LocalDate.parse(rs.getString("order_date")));
			order.add(subject);
			}
		} 
		catch (SQLException | DBException e) {
			throw new DBException("Unable to get order id");
		} 
		finally {
			ConnectionUtil.close(rs, pst, connection);
		}		
		return order;		
	}
	
	/**
	 * If ordered the item will be removed from the cart database
	 * @param order
	 * @throws DBException
	 */
	public void removeCart(Order order) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			// Prepare data to insert into the driver
			String sql = 
				"update bill set status=0 WHERE name=? AND category=? AND username=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, order.getOrderType());
			pst.setString(2, order.getOrderCategory());
			pst.setString(3, order.getUserName());
			// Executes the Query
			pst.executeUpdate();
		} 
		catch (SQLException | DBException e) {
			throw new DBException("Unable to delete item from cart");
		} 
		finally {
			ConnectionUtil.close( pst, connection);
		}		
	}
	
	/**
	 * Get user id for the respective username
	 * @param userName
	 * @return
	 * @throws DBException
	 */
	public int getUserId(String userName) throws DBException {
		
		int userId;
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			connection=ConnectionUtil.getConnection();
			String sql="select id from user_data WHERE name=?";
			pst=connection.prepareStatement(sql);
			pst.setString(1, userName);
			rs = pst.executeQuery();
			rs.next();
			userId=rs.getInt("id"); 				
		} 
		catch (SQLException | DBException e) {
			throw new DBException("Unable to get id");
		} 
		finally {
			ConnectionUtil.close(rs, pst, connection);
		}	
		return userId;
	}
	
	/**
	 * Get user mobile number for the respective user name
	 * @param userName
	 * @return
	 * @throws DBException
	 */
	public long getUserMobileNo(String userName) throws DBException {
		
		Long mobileNo;
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			connection=ConnectionUtil.getConnection();
			String sql="select mobileno from user_data WHERE name=?";
			pst=connection.prepareStatement(sql);
			pst.setString(1, userName);
			rs = pst.executeQuery();
			rs.next();
			mobileNo=rs.getLong("mobileno"); 				
		} 
		catch (SQLException | DBException e) {
			throw new DBException("Unable to get id");
		} 
		finally {
			ConnectionUtil.close(rs, pst, connection);
		}	
		return mobileNo;
	}
}
