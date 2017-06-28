package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.DatabaseConnection;
import constant.Define;
import model.bean.Category;
import model.bean.User;

public class CategoryDao {
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	
	public ArrayList<Category> getItems() {
		ArrayList<Category> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_cat,name FROM cat ORDER BY name ASC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("id_cat");
				String name = rs.getString("name");
				Category item = new Category(id, name);
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

	public int addItem(Category obj) {
		int result=0;
		conn = DatabaseConnection.getConnectMySql();
		String query = "Insert into cat(name) values(?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, obj.getName());
			result=pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public Category getItem(int id) {
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_cat,name FROM cat where id_cat=?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			if(rs.next()){
				String name = rs.getString("name");
				Category item = new Category(id, name);
				return item;
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

	public int editItem(Category item) {
		int result=0;
		conn = DatabaseConnection.getConnectMySql();
		String query = "Update cat Set name=? where id_cat=?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getName());
			pst.setInt(2, item.getId());
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
		conn = DatabaseConnection.getConnectMySql();
		String query = "Delete from cat where id_cat=?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
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
	public int countCat(){
		int result=0;
		conn = DatabaseConnection.getConnectMySql();
		User objUser=null;
		String query = "SELECT count(*) as sumuser FROM cat";
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
	public ArrayList<Category> getItemsPagenation(int offset) {
		ArrayList<Category> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_cat,name FROM cat ORDER BY name ASC limit "+offset+","+Define.ROW_COUNT_ADMIN;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("id_cat");
				String name = rs.getString("name");
				Category item = new Category(id, name);
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
