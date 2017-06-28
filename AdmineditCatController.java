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
 * Servlet implementation class AdmineditCatController
 */
//@WebServlet("/AdmineditCatController")
public class AdmineditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CategoryDao category;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdmineditCatController() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String idText=(String)request.getParameter("id");
		int id=0;
		try {
			 id=Integer.parseInt(idText);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/cats?err=1");
			return;
		}
		Category obj=category.getItem(id);
		if(obj==null){
			response.sendRedirect(request.getContextPath()+"/admin/cats?err=2");
			return;
		}else{
			request.setAttribute("obj", obj);
			RequestDispatcher rd=request.getRequestDispatcher("/admin/editCat.jsp");
			rd.forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String idText=request.getParameter("id");
		String name=(String) request.getParameter("tentin");
		int id=0;
		try {
			 id=Integer.parseInt(idText);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/cats?err=1");
			return;
		}
		Category item=new Category(id,name);
		if(category.editItem(item)>0){
			response.sendRedirect(request.getContextPath()+"/admin/cats?mgs=2");
			return;
		}else{
			RequestDispatcher rd=request.getRequestDispatcher("/admin/editCat.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
