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
 * Servlet implementation class AdminAddCatController
 */
//@WebServlet("/AdminAddCatController")
public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryDao category;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddCatController() {
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
		RequestDispatcher rd=request.getRequestDispatcher("/admin/addCat.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String tentin=request.getParameter("tentin");
		Category obj=new Category(0,tentin);
		if(category.addItem(obj)>0){
			response.sendRedirect(request.getContextPath()+"/admin/cats?mgs=1");
			return;
		}else{
			RequestDispatcher rd=request.getRequestDispatcher("/admin/addCat.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
