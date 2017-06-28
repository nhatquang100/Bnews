package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.AuthUtil;

import model.bean.News;
import model.dao.NewsDao;



/**
 * Servlet implementation class AdminDelCatController
 */
//@WebServlet("/AdminDelCatController")
public class AdminDelNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NewsDao newDao;  
 
    public AdminDelNewsController() {
        super();
       newDao=new NewsDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String idText=request.getParameter("nid");
		
		//duong dan luu file
		final String path = request.getServletContext().getRealPath("/files");
		System.out.println("đường dẫn ảnh nè:"+path);
		File dirPath = new File(path);
		
		int id=0;
		try {
			id=Integer.parseInt(idText);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/news?err=2");
			return;
		}
		News objNews=newDao.getItem(id);
		String urlFileDel=path+File.separator+objNews.getPicture();
		 File delFile=new File(urlFileDel);
		if(newDao.delItem(id)>0){
			if(!"".equals(objNews.getPicture())){
				 //xoa hinh anh cu(xoa file)
				 delFile.delete();
			 }
			 //if(delFile.isFile() &&delFile.exists()){
			// delFile.delete();
		 //}
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=3");
			return;
		}else{
			response.sendRedirect(request.getContextPath()+"/admin/news?err=3");
			return;
		}
	}

}
