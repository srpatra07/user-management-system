package com.org.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.org.dto.User;

public class UserDao {
	public void saveUser(User user) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_mgmt", "root", "root");
			PreparedStatement psmt = con
					.prepareStatement("insert into user(name,age,email,password,mobile) values(?,?,?,?,?)");
			psmt.setString(1, user.getName());
			psmt.setInt(2, user.getAge());
			psmt.setString(3, user.getEmail());
			psmt.setString(4, user.getPassword());
			psmt.setLong(5, user.getMobile());

			psmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public User fetchUserByEmailAndPassword(String email, String password) {
		User user = new User();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_mgmt", "root", "root");
			PreparedStatement psmt = con.prepareStatement("select * from user where email=? and password=?");
			psmt.setString(1, email);
			psmt.setString(2, password);
			ResultSet resultSet = psmt.executeQuery();
			if (resultSet.next()) {
				user.setName(resultSet.getString("name"));
				user.setAge(resultSet.getInt("age"));
				user.setEmail(email);
				user.setPassword(password);
				user.setMobile(resultSet.getLong("mobile"));
				user.setId(resultSet.getInt("id"));

			}
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public User fetchUserById(int id) {
		User user = new User();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_mgmt", "root", "root");
			PreparedStatement psmt = con.prepareStatement("select * from user where id=?");
			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setMobile(rs.getLong("mobile"));
			}
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public List<User> fetchAllUsers() {
		List<User> list = new ArrayList<User>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_mgmt", "root", "root");
			PreparedStatement psmt = con.prepareStatement("SELECT * FROM USER");
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setAge(rs.getInt("age"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
				u.setMobile(rs.getLong("mobile"));

				list.add(u);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void updateUserById(User u)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_mgmt","root","root");
			PreparedStatement psmt = con.prepareStatement("UPDATE USER SET NAME=?,AGE=?,EMAIL=?,MOBILE=? WHERE ID=?");
			psmt.setString(1, u.getName());
			psmt.setInt(2, u.getAge());
			psmt.setString(3, u.getEmail());
			psmt.setLong(4, u.getMobile());
			psmt.setInt(5, u.getId());
			
			psmt.executeUpdate();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateUserPasswordById(int id,String password)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_mgmt","root","root");
			PreparedStatement psmt = con.prepareStatement("UPDATE USER SET PASSWORD = ? WHERE ID=?");
			psmt.setString(1, password);
			psmt.setInt(2, id);
			psmt.executeUpdate();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteUserById(int id)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_mgmt","root","root");
			PreparedStatement psmt = con.prepareStatement("DELETE FROM USER WHERE ID=?");
			psmt.setInt(1, id);
			psmt.executeUpdate();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}