package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

	/**
	 * tạo connect kết nối tới database
	 * 
	 * @return trả về đối tượng Connection hoặc null nếu kết nối không thành công
	 */
	public static Connection create() {
		Connection connection = null;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			connection = DriverManager
					.getConnection("jdbc:ucanaccess://C://LT_Mang_ThayTinh_Thu4Ca2//Workspaces//Database1.accdb");
//			System.out.println("Connected Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * ngắt kết nối với connection được truyền vào
	 * 
	 * @param connection connection cần ngắt kết nối
	 */
	public static void close(Connection connection) {
		if (connection != null)
			try {
				connection.close();
//				System.out.println("Disconnect");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void main(String[] args) {
		Connection connection = create();
		close(connection);
	}
}
