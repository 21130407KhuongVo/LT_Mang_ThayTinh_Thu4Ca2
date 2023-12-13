package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBC.JDBC;

public class DAO_User {
	public static boolean selectByUsername(String username) {
		boolean result = false;
		Connection connection = JDBC.create();
		try {
			String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next())
				result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBC.close(connection);
		return result;
	}

	public static boolean select(String username, String password) {
		boolean result = false;
		Connection connection = JDBC.create();
		try {
			String sql = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next())
				result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBC.close(connection);
		return result;
	}
}
