package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.DatabaseConnection;
import constant.Define;
import model.bean.User;

public class UserDao {

	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	
	public ArrayList<User> getItems() {
		ArrayList<User> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id,username,password,fullname FROM user ORDER BY username ASC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("username");
				String pass = rs.getString("password");
				String fullname = rs.getString("fullname");
				User item = new User(id, name,pass,fullname);
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs != null && st != null && conn !=null){
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return items;
	}

	public int addUser(User objUser) {
		int result=0;
		conn= DatabaseConnection.getConnectMySql();
		String query="INsert into user(username,password,fullname) values(?,?,?)";
		try {
			pst=conn.prepareStatement(query);
			pst.setString(1, objUser.getUsername());
			pst.setString(2, objUser.getPassword());
			pst.setString(3, objUser.getFullname());
			result=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pst.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return result;
	}
	public User checkUser(String username){
		conn=DatabaseConnection.getConnectMySql();
		User objUser=null;
		String query="select *from user where username=?";
		try {
			pst=conn.prepareStatement(query);
			pst.setString(1, username);
			rs=pst.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("username");
				String pass = rs.getString("password");
				String fullname = rs.getString("fullname");
				objUser = new User(id, name,pass,fullname);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return objUser;
	}

	public User getItem(int idUser) {
		conn = DatabaseConnection.getConnectMySql();
		User objUser=null;
		String query = "SELECT * FROM user where id=?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, idUser);
			rs=pst.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("username");
				String pass = rs.getString("password");
				String fullname = rs.getString("fullname");
				objUser = new User(id, name,pass,fullname);
				return objUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null && pst != null && conn !=null){
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return null;
	}

	public int editItem(User item) {
		int result=0;
		conn = DatabaseConnection.getConnectMySql();
		String query = "Update user set password=?,fullname=? where id=?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getPassword());
			pst.setString(2, item.getFullname());
			pst.setInt(3, item.getID());
			result=pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null && pst != null && conn !=null){
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		return result;
	}

	public int delItem(int id) {
		int result=0;
		conn=DatabaseConnection.getConnectMySql();
		String query="delete from user where id=?";
		try {
			pst=conn.prepareStatement(query);
			pst.setInt(1, id);
			result=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pst.close();conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return result;
	}
	
	public User existUser(String username, String password) {
		User item=null;
		conn = DatabaseConnection.getConnectMySql();
		String query = "select id, username, password, fullname from user where username=? && password=? ";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			rs=pst.executeQuery();
			if(rs.next()){
				int id=rs.getInt("id");
				String fullname=rs.getString("fullname");
				item=new User(id,username,"",fullname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pst != null && conn !=null){
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return item;
	}
	public int countUser(){
		int result=0;
		conn = DatabaseConnection.getConnectMySql();
		User objUser=null;
		String query = "SELECT count(*) as sumuser FROM user";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(query);
			while(rs.next()){
				result = rs.getInt("sumuser");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null && pst != null && conn !=null){
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return result;
	}
	public ArrayList<User> getItemsPagenation( int offset) {
		ArrayList<User> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id,username,password,fullname FROM user ORDER BY username ASC limit "+offset+","+Define.ROW_COUNT_ADMIN;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("username");
				String pass = rs.getString("password");
				String fullname = rs.getString("fullname");
				User item = new User(id, name,pass,fullname);
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs != null && st != null && conn !=null){
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return items;
	}

}
