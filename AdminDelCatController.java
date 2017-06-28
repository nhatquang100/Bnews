package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.AuthUtil;

import model.bean.Category;
import model.dao.CategoryDao;

/**
 * Servlet implementation class AdminDelCatController
 */
//@WebServlet("/AdminDelCatController")
public class AdminDelCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CategoryDao category;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelCatController() {
        super();
        category=new CategoryDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String idText=request.getParameter("id");
		int id=0;
		try {
			id=Integer.parseInt(idText);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/cats?err=1");
			return;
		}
		if(category.delItem(id)>0){
			response.sendRedirect(request.getContextPath()+"/admin/cats?mgs=3");
			return;
		}else{
			response.sendRedirect(request.getContextPath()+"/admin/cats?err=3");
			return;
		}
	}

}
