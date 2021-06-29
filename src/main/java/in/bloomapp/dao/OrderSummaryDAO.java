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

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import in.bloomapp.exception.DBException;
import in.bloomapp.model.Flower;
import in.bloomapp.model.Order;
import in.bloomapp.util.ConnectionUtil;

public class OrderSummaryDAO {
	private static final String DELIVERY_ADDRESS = "delivery_address";
	private static final String DELIVERY_CITY = "delivery_city";
	private static final String DELIVERY_DATE = "deliver_date";
	private static final String DELIVERY_TIME = "delivery_time";
	private static final String USER_NAME = "user_name";
	private static final String MOBILE_NO = "mobile_no";
	private static final String ORDER_DATE = "order_date";
	private static final String TOTAL_FLOWERS = "total_flowers";
	private static final String CATEGORY = "category";
	private static final String NAME = "name";
	private static final String PRICE = "price";
	private static final String QUANTITY = "quantity";
	private static final String TOTAL_SUM="total_sum";

	// jdbc template which helps in giving and closing connections
	private static JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	/**
	 * This methods gets the total flowers that was ordered for the given date
	 * 
	 * @param summaryDate
	 * @return
	 * @throws DBException
	 */
	public List<Flower> getflowersOrdered(LocalDate summaryDate) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Flower> summary = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select flowersdata.category AS flower_category, flowersdata.name AS type ,"
					+ " sum(orders.quantity) AS total from orders"
					+ " left Join flowersdata on orders.flower_id =flowersdata.id  Where order_date=?"
					+ " group by flowersdata.category ,flowersdata.name order by total";
			pst = connection.prepareStatement(sql);
			pst.setObject(1, summaryDate);
			rs = pst.executeQuery();
			while (rs.next()) {
				Flower flower = new Flower();
				flower.setCategory(rs.getString("flower_category"));
				flower.setType(rs.getString("type"));
				flower.setQuantity(rs.getInt("total"));
				summary.add(flower);
			}
		} catch (SQLException e) {
			throw new DBException("Unable to get summary list");
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
		return summary;
	}

	/**
	 * Gets the order list that is not yet delivered
	 * 
	 * @return
	 * @throws DBException
	 */
	public List<Order> getOrderList() throws DBException {
		List<Order> orders = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();

			String sql = "select category,name,quantity,price,delivery_city,delivery_address,"
					+ "deliver_date,delivery_time,user_name,mobile_no,"
					+ "order_date from orders WHERE delivery_status='yetToDeliver' AND status=1";
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Order subject = new Order();
				subject.setOrderCategory(rs.getString(CATEGORY));
				subject.setOrderType(rs.getString(NAME));
				subject.setOrderPrice(rs.getInt(PRICE));
				subject.setOrderQuantity(rs.getInt(QUANTITY));
				subject.setDeliveryCity(rs.getString(DELIVERY_CITY));
				subject.setDeliverAddress(rs.getString(DELIVERY_ADDRESS));
				subject.setDeliveryDate(LocalDate.parse(rs.getString(DELIVERY_DATE)));
				Time deliveryTime = (rs.getTime(DELIVERY_TIME));
				String time = deliveryTime.toString();
				LocalTime parsedTime = LocalTime.parse(time);
				subject.setDeliveryTime(parsedTime);
				subject.setUserName(rs.getString(USER_NAME));
				subject.setUserMobileNo(rs.getLong(MOBILE_NO));
				subject.setOrderDate(LocalDate.parse(rs.getString(ORDER_DATE)));
				orders.add(subject);
			}
		} catch (SQLException e) {
			throw new DBException("unable to get order list");
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
		return orders;
	}

	/**
	 * 
	 * @param userName
	 * @return
	 * @throws DBException
	 */
	public List<Order> getUserOrder(String userName) throws DBException {
		List<Order> orders = null;
		try {
			String sql = "select delivery_city,delivery_address,deliver_date,delivery_time,user_name"
					+ ",mobile_no,order_date ,sum(quantity) AS total_flowers, SUM(price) AS total_sum "
					+ "from orders WHERE user_name=? AND delivery_status='yetToDeliver' AND "
					+ "status=1 group by delivery_city,delivery_address,deliver_date,delivery_time,user_name,"
					+ "mobile_no,order_date";

			// fields that is to be passed to the query are given as parameter
			Object[] params = { userName };
			// And the returned data is stored in a variable
			orders = jdbcTemplate.query(sql, (rs, rowNo) -> {
				Order subject = new Order();
				subject.setDeliveryCity(rs.getString(DELIVERY_CITY));
				subject.setDeliverAddress(rs.getString(DELIVERY_ADDRESS));
				subject.setDeliveryDate(LocalDate.parse(rs.getString(DELIVERY_DATE)));
				Time deliveryTime = (rs.getTime(DELIVERY_TIME));
				String time = deliveryTime.toString();
				LocalTime parsedTime = LocalTime.parse(time);
				subject.setDeliveryTime(parsedTime);
				subject.setUserName(rs.getString(USER_NAME));
				subject.setUserMobileNo(rs.getLong(MOBILE_NO));
				subject.setOrderDate(LocalDate.parse(rs.getString(ORDER_DATE)));
				subject.setOrderQuantity(rs.getInt(TOTAL_FLOWERS));
				subject.setOrderPrice(rs.getInt(TOTAL_SUM));
				return subject;
			}, params);
		} catch (DataAccessException e) {
			throw new DBException("unable to get user order list");
		}
		return orders;
	}

	/**
	 * Gets the order of the particular user that is shown as order summary
	 * 
	 * @param userName
	 * @return
	 * @throws DBException
	 */
	public List<Order> getOrderItems(String userName) throws DBException {

		List<Order> orders = null;
		try {
			String sql = "select category,name,price,quantity,delivery_city,delivery_address,"
					+ "deliver_date,delivery_time,user_name"
					+ ",mobile_no,order_date ,sum(quantity) AS total_flowers, SUM(price) AS total_sum "
					+ "from orders WHERE user_name=? AND delivery_status='yetToDeliver' AND "
					+ "status=1 group by category,name,price,quantity,delivery_city,"
					+ "delivery_address,deliver_date,delivery_time,user_name,mobile_no,order_date";

			// fields that is to be passed to the query are given as parameter
			Object[] params = { userName };
			// And the returned data is stored in a variable
			orders = jdbcTemplate.query(sql, (rs, rowNo) -> {
				Order subject = new Order();
				subject.setOrderCategory(rs.getString(CATEGORY));
				subject.setOrderType(rs.getString(NAME));
				subject.setOrderPrice(rs.getInt(PRICE));
				subject.setOrderQuantity(rs.getInt(QUANTITY));
				subject.setDeliveryCity(rs.getString(DELIVERY_CITY));
				subject.setDeliverAddress(rs.getString(DELIVERY_ADDRESS));
				subject.setDeliveryDate(LocalDate.parse(rs.getString(DELIVERY_DATE)));
				Time deliveryTime = (rs.getTime(DELIVERY_TIME));
				String time = deliveryTime.toString();
				LocalTime parsedTime = LocalTime.parse(time);
				subject.setDeliveryTime(parsedTime);
				subject.setUserName(rs.getString(USER_NAME));
				subject.setUserMobileNo(rs.getLong(MOBILE_NO));
				subject.setOrderDate(LocalDate.parse(rs.getString(ORDER_DATE)));
				subject.setOrderQuantity(rs.getInt(TOTAL_FLOWERS));
				subject.setOrderPrice(rs.getInt(TOTAL_SUM));
				return subject;
			}, params);
		} catch (DataAccessException e) {
			throw new DBException("unable to get user order list");
		}
		return orders;
	}

	/**
	 * The items which are rejected are shown here
	 * 
	 * @param userName
	 * @return
	 * @throws DBException
	 */
	public List<Order> getRejectedItems(String userName) throws DBException {

		List<Order> rejectedOrders = null;
		try {
			String sql = "select category,name,price,quantity,delivery_city,delivery_address,"
					+ "deliver_date,delivery_time,user_name,mobile_no,order_date ," + "sum(quantity) AS total_flowers"
					+ ", SUM(price) AS total_sum " + "from orders WHERE user_name=? "
					+ "AND delivery_status='Rejected' " + "AND status=1 group by category,name,price,quantity"
					+ ",delivery_city,delivery_address,deliver_date,delivery_time,user_name,mobile_no,order_date";

			// fields that is to be passed to the query are given as parameter
			Object[] params = { userName };
			// And the returned data is stored in a variable
			rejectedOrders = jdbcTemplate.query(sql, (rs, rowNo) -> {
				Order subject = new Order();
				subject.setOrderCategory(rs.getString(CATEGORY));
				subject.setOrderType(rs.getString(NAME));
				subject.setOrderPrice(rs.getInt(PRICE));
				subject.setOrderQuantity(rs.getInt(QUANTITY));
				subject.setDeliveryCity(rs.getString(DELIVERY_CITY));
				subject.setDeliverAddress(rs.getString(DELIVERY_ADDRESS));
				subject.setDeliveryDate(LocalDate.parse(rs.getString(DELIVERY_DATE)));
				Time deliveryTime = (rs.getTime(DELIVERY_TIME));
				String time = deliveryTime.toString();
				LocalTime parsedTime = LocalTime.parse(time);
				subject.setDeliveryTime(parsedTime);
				subject.setUserName(rs.getString(USER_NAME));
				subject.setUserMobileNo(rs.getLong(MOBILE_NO));
				subject.setOrderDate(LocalDate.parse(rs.getString(ORDER_DATE)));
				subject.setOrderQuantity(rs.getInt(TOTAL_FLOWERS));
				subject.setOrderPrice(rs.getInt(TOTAL_SUM));
				return subject;
			}, params);
		} catch (DataAccessException e) {
			throw new DBException("unable to get rejected user order list");
		}
		return rejectedOrders;
	}

}
