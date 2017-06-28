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

import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDao;
import model.dao.NewsDao;


public class DetailPublicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDao newsDao;
	
    
    public DetailPublicController() {
		super();
		newsDao=new NewsDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int did=0;
		try {
			did=Integer.parseInt(request.getParameter("did"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/");
			return;
		}
		News obj=newsDao.getItemByID(did);
		request.setAttribute("obj", obj);
		request.setAttribute("listtinlquan", newsDao.getTinLienQuan(obj.getIdcat(), did));
		RequestDispatcher rd = request.getRequestDispatcher("/chitiet.jsp");
		rd.forward(request, response);
	}

}
