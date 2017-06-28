package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.AuthUtil;

import constant.Define;
import model.bean.News;
import model.dao.NewsDao;

/**
 * Servlet implementation class AdminIndexNewController
 */
//@WebServlet("/AdminIndexNewController")
public class AdminIndexNewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NewsDao newsDao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexNewController() {
        super();
       newsDao =new NewsDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("txt/html");
		
		//tong so tin
		int sumNews=newsDao.countNews();
		int sumPage=(int)Math.ceil((float)sumNews/Define.ROW_COUNT_ADMIN);
		//lay trang hien tai=curent_page
		int current_page=1;
		if(request.getParameter("page")!=null){
			current_page=Integer.parseInt(request.getParameter("page"));
		}
		int offset=(current_page-1)*Define.ROW_COUNT_ADMIN;
		ArrayList<News> list=newsDao.getItemsPagenation(offset);
		request.setAttribute("sumPage", sumPage);
		request.setAttribute("current_page", current_page);
		request.setAttribute("list", list);
		RequestDispatcher rd=request.getRequestDispatcher("/admin/indexNews.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		
	}

}
