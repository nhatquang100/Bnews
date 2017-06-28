package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import common.AuthUtil;
import common.StringNameFile;
import common.StringUntil;
import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDao;
import model.dao.NewsDao;


@MultipartConfig
public class AdminAddNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoryDao category;
    private NewsDao newsDao;
    
    public AdminAddNewsController() {
        super();
        category=new CategoryDao();
        newsDao=new NewsDao();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("txt/html");
		ArrayList<Category> list=category.getItems();
		
		request.setAttribute("list", list);
		RequestDispatcher rd=request.getRequestDispatcher("/admin/addNews.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("txt/html");
		
		//duong dan luu file
		 final String path = request.getServletContext().getRealPath("/files");
			System.out.println("đường dẫn ảnh nè:"+path);
			File dirPath = new File(path);
			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
			int catID=0;
		//get du lieu
		 String name=request.getParameter("tentin");
		 try {
			 catID=Integer.parseInt(request.getParameter("danhmuc"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/admin/news");
		}
		 String preview=request.getParameter("mota");
		 String detail=request.getParameter("chitiet");
		 PrintWriter outw=response.getWriter();
		 Part part=request.getPart("hinhanh");
		 
		 //lay ten file
		 String fileName = StringUntil.getFileName(part);
		//doi ten file theo time
		 String nameFile=StringNameFile.getFileName(fileName);
		 
		 //SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		 //String date=sdf.format(new Date());
		 Timestamp dateCreate=new  Timestamp(new Date().getTime());
		 News item=new News(0,name,preview,detail,dateCreate,catID,nameFile,null);
		 if(newsDao.addItem(item)>0){
				//themthanhcong
				// ghi file
				String filePath = path + File.separator + nameFile;
				part.write(filePath);
				
				response.sendRedirect(request.getContextPath()+"/admin/news?msg=1");
				return;
		 }else{
			 RequestDispatcher rd=request.getRequestDispatcher("/admin/addNews.jsp?err=1");
				rd.forward(request, response);
		 }
	}

}
