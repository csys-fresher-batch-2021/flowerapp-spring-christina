package in.bloomapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import in.bloomapp.exception.DBException;
import in.bloomapp.model.Order;
import in.bloomapp.util.ConnectionUtil;

/**
 * Cares about the approval of the orders
 * 
 * @author chri2631
 *
 */
public class ApprovalManagerDAO {

	/**
	 * Updates the approval status of order to rejected or to be delivered
	 * 
	 * @param order
	 * @throws DBException
	 */
	public void updateApprovalSts(Order order) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			// updates the delivery status by id and status
			String sql = "update orders set delivery_status=? Where id=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, order.getDeliveryStatus());
			pst.setInt(2, getMaxId(order));
			pst.executeUpdate();
		} catch (SQLException | DBException e) {
			throw new DBException("Unable to add order");
		} finally {
			ConnectionUtil.close(pst, connection);
		}
	}

	/**
	 * Get id of the order to be updated
	 * 
	 * @param order
	 * @return
	 * @throws DBException
	 */
	public int getMaxId(Order order) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int orderItemId;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select id from orders WHERE category=?" + "AND name=?" + " AND price=? AND delivery_address=?"
					+ " AND deliver_date=? AND delivery_time=?" + " AND mobile_no=? AND order_date=?"
					+ "AND status=1 AND delivery_status='yetToApprove'";
			pst = connection.prepareStatement(sql);
			pst.setString(1, order.getOrderCategory());
			pst.setString(2, order.getOrderType());
			pst.setInt(3, order.getOrderPrice());
			pst.setString(4, order.getDeliverAddress());
			pst.setObject(5, order.getDeliveryDate());
			pst.setObject(6, order.getDeliveryTime());
			pst.setLong(7, order.getUserMobileNo());
			pst.setObject(8, order.getOrderDate());
			rs = pst.executeQuery();
			rs.next();
			// gets id from table
			orderItemId = rs.getInt("id");
		} catch (SQLException e) {
			throw new DBException("Unable to get item id");
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
		return orderItemId;
	}

}
