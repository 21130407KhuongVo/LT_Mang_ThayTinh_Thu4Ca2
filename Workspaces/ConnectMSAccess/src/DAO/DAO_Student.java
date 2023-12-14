package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC.JDBC;
import Model.Student;

public class DAO_Student {
	public static ArrayList<Student> findByName(String n) {
		ArrayList<Student> list = new ArrayList<>();
		Connection connection = JDBC.create();
		try {
			String sql = "SELECT * FROM STUDENTS WHERE NAME LIKE ?";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, "%" + n + "%");
			ResultSet resultSet = st.executeQuery();
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				Date birthday = resultSet.getDate("birthday");
				double score = resultSet.getDouble("score");
				list.add(new Student(id, name, birthday, score));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBC.close(connection);
		return list;
	}

	public static ArrayList<Student> findByAge(int age) {
		ArrayList<Student> list = new ArrayList<>();
		Connection connection = JDBC.create();
		try {
			String sql = "SELECT * FROM STUDENTS WHERE AGE = ?";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, age);
			ResultSet resultSet = st.executeQuery();
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				Date birthday = resultSet.getDate("birthday");
				double score = resultSet.getDouble("score");
				list.add(new Student(id, name, birthday, score));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBC.close(connection);
		return list;
	}

	public static ArrayList<Student> findByScore(double s) {
		ArrayList<Student> list = new ArrayList<>();
		Connection connection = JDBC.create();
		try {
			String sql = "SELECT * FROM STUDENTS WHERE SCORE = ?";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setDouble(1, s);
			ResultSet resultSet = st.executeQuery();
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				Date birthday = resultSet.getDate("birthday");
				double score = resultSet.getDouble("score");
				list.add(new Student(id, name, birthday, score));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBC.close(connection);
		return list;
	}

	public static Student findById(String param) {
		Student student = null;
		Connection connection = JDBC.create();
		try {
			String sql = "SELECT * FROM STUDENTS WHERE ID = ?";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, param);
			ResultSet resultSet = st.executeQuery();
			if (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				Date birthday = resultSet.getDate("birthday");
				double score = resultSet.getDouble("score");
				student = new Student(id, name, birthday, score);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBC.close(connection);
		System.out.println("Student: "+ student.toString());
		return student;
	}
}
