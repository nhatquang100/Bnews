package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import common.DatabaseConnection;
import constant.Define;
import model.bean.Category;
import model.bean.News;

public class NewsDao {
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	private Connection conn;
	public ArrayList<News> getItems() {
		ArrayList<News> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name as nname,preview_text,detail_text,date_create,n.id_cat as idcat,picture,c.name as cname from news as n INNER JOIN cat as c on n.id_cat=c.id_cat ORDER BY id_news DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("id_news");
				String nname = rs.getString("nname");
				String preview = rs.getString("preview_text");
				String detail = rs.getString("detail_text");
				Timestamp date=rs.getTimestamp("date_create");
				int idcat = rs.getInt("idcat");
				String picture = rs.getString("picture");
				String namecat= rs.getString("cname");
				News item = new News(id,nname,preview,detail,date,idcat,picture,namecat);
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(st != null && conn !=null){
				try {
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
	
	public int addItem(News item) {
		int result=0;
		conn = DatabaseConnection.getConnectMySql();
		String query = "Insert into news(name,preview_text, detail_text,date_create,picture,id_cat) values(?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getName());
			pst.setString(2, item.getPreview());
			pst.setString(3, item.getDetail());
			pst.setTimestamp(4, item.getDate());
			pst.setString(5, item.getPicture());
			pst.setInt(6, item.getIdcat());
			result=pst.executeUpdate();
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
		return result;
	}

	public News getItem(int idnews) {
		News item =null;
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name as nname,preview_text,detail_text,date_create,n.id_cat as idcat,picture,c.name as cname from news as n INNER JOIN cat as c  on n.id_cat=c.id_cat WHERE  id_news=?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, idnews);
			rs=pst.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id_news");
				String nname = rs.getString("nname");
				String preview = rs.getString("preview_text");
				String detail = rs.getString("detail_text");
				Timestamp date=rs.getTimestamp("date_create");
				int idcat = rs.getInt("idcat");
				String picture = rs.getString("picture");
				String namecat= rs.getString("cname");
				item = new News(id,nname,preview,detail,date,idcat,picture,namecat);
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
					e.printStackTrace();
				}
				
			}
		}
		return null;
	}

	public int editItem(News item) {
		int result=0;
		conn = DatabaseConnection.getConnectMySql();
		String query = "update news set name=?,preview_text=?,detail_text=?,picture=?,id_cat=? where id_news=?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getName());
			pst.setString(2, item.getPreview());
			pst.setString(3, item.getDetail());
			pst.setString(4, item.getPicture());
			pst.setInt(5, item.getIdcat());
			pst.setInt(6, item.getId());
			result=pst.executeUpdate();
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
		return result;
	}

	public int delItem(int id) {
		int result=0;
		conn=DatabaseConnection.getConnectMySql();
		String query="delete from news where id_news=?";
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

	public ArrayList<News> getItemsByID(int cid) {
		ArrayList<News> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name as nname,preview_text,detail_text,date_create,n.id_cat as idcat,picture,c.name as cname from news as n INNER JOIN cat as c on n.id_cat=c.id_cat where n.id_cat="+cid+" ORDER BY id_news DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("id_news");
				String nname = rs.getString("nname");
				String preview = rs.getString("preview_text");
				String detail = rs.getString("detail_text");
				Timestamp date=rs.getTimestamp("date_create");
				int idcat = rs.getInt("idcat");
				String picture = rs.getString("picture");
				String namecat= rs.getString("cname");
				News item = new News(id,nname,preview,detail,date,idcat,picture,namecat);
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(st != null && conn !=null){
				try {
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

	public Category getCatByID(int cid) {
		Category obj=null;
		conn=DatabaseConnection.getConnectMySql();
		String query="select *from cat where id_cat=?";
		try {
			pst=conn.prepareStatement(query);
			pst.setInt(1, cid);
			rs=pst.executeQuery();
			if(rs.next()){
				int catid=rs.getInt("id_cat");
				String name=rs.getString("name");
				obj=new Category(catid,name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public News getItemByID(int did) {
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name as nname,preview_text,detail_text,date_create,n.id_cat as idcat,picture,c.name as cname from news as n INNER JOIN cat as c on n.id_cat=c.id_cat where id_news="+did;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("id_news");
				String nname = rs.getString("nname");
				String preview = rs.getString("preview_text");
				String detail = rs.getString("detail_text");
				Timestamp date=rs.getTimestamp("date_create");
				int idcat = rs.getInt("idcat");
				String picture = rs.getString("picture");
				String namecat= rs.getString("cname");
				News item = new News(id,nname,preview,detail,date,idcat,picture,namecat);
				return item;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(st != null && conn !=null){
				try {
					st.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return null;
	}

	public ArrayList<News> getTinLienQuan(int catid, int idNews) {
		ArrayList<News> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name as nname,preview_text,detail_text,date_create,n.id_cat as idcat,picture,c.name as cname from news as n INNER JOIN cat as c on n.id_cat=c.id_cat where n.id_cat="+catid+" && id_news!="+idNews+" ORDER BY id_news DESC limit 2";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("id_news");
				String nname = rs.getString("nname");
				String preview = rs.getString("preview_text");
				String detail = rs.getString("detail_text");
				Timestamp date=rs.getTimestamp("date_create");
				int idcat = rs.getInt("idcat");
				String picture = rs.getString("picture");
				String namecat= rs.getString("cname");
				News item = new News(id,nname,preview,detail,date,idcat,picture,namecat);
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(st != null && conn !=null){
				try {
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
	public int countNews(){
		int result=0;
		conn=DatabaseConnection.getConnectMySql();
		String query = "SELECT count(*) as sumNews from news as n INNER JOIN cat as c on n.id_cat=c.id_cat";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next()){
				result=rs.getInt("sumNews");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(st != null && conn !=null){
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
	public int countNewsByID( int cid){
		int result=0;
		conn=DatabaseConnection.getConnectMySql();
		String query = "SELECT count(*) as sumNews from news as n INNER JOIN cat as c on n.id_cat=c.id_cat where n.id_cat="+cid;
		try {
			st=conn.createStatement();
			rs=st.executeQuery(query);
			if(rs.next()){
				result=rs.getInt("sumNews");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(st != null && conn !=null){
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
	public ArrayList<News> getItemsPagenation(int offset) {
		ArrayList<News> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name as nname,preview_text,detail_text,date_create,n.id_cat as idcat,picture,c.name as cname from news as n INNER JOIN cat as c on n.id_cat=c.id_cat ORDER BY id_news DESC Limit "+offset+","+Define.ROW_COUNT_ADMIN;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("id_news");
				String nname = rs.getString("nname");
				String preview = rs.getString("preview_text");
				String detail = rs.getString("detail_text");
				Timestamp date=rs.getTimestamp("date_create");
				int idcat = rs.getInt("idcat");
				String picture = rs.getString("picture");
				String namecat= rs.getString("cname");
				News item = new News(id,nname,preview,detail,date,idcat,picture,namecat);
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(st != null && conn !=null){
				try {
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
	public ArrayList<News> getItemsByIDPagenation(int cid, int offset) {
		ArrayList<News> items = new ArrayList<>();
		conn = DatabaseConnection.getConnectMySql();
		String query = "SELECT id_news, n.name as nname,preview_text,detail_text,date_create,n.id_cat as idcat,picture,c.name as cname from news as n INNER JOIN cat as c on n.id_cat=c.id_cat where n.id_cat="+cid+" ORDER BY id_news DESC limit "+offset+","+Define.ROW_COUNT_ADMIN;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("id_news");
				String nname = rs.getString("nname");
				String preview = rs.getString("preview_text");
				String detail = rs.getString("detail_text");
				Timestamp date=rs.getTimestamp("date_create");
				int idcat = rs.getInt("idcat");
				String picture = rs.getString("picture");
				String namecat= rs.getString("cname");
				News item = new News(id,nname,preview,detail,date,idcat,picture,namecat);
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(st != null && conn !=null){
				try {
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
