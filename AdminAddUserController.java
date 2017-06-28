package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.AuthUtil;
import common.StringLibrary;
import model.bean.Category;
import model.bean.User;
import model.dao.CategoryDao;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminAddCatController
 */
//@WebServlet("/AdminAddCatController")
public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryDao category;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddUserController() {
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
		RequestDispatcher rd=request.getRequestDispatcher("/admin/addUser.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String name=request.getParameter("name");
		String pass=StringLibrary.md5(request.getParameter("pass"));
		String fullname=request.getParameter("fullname");
		User objUser=new  User(0,name,pass,fullname);
		UserDao userDao=new UserDao();
		
		if(userDao.checkUser(name)!=null){
			//da ton tai user name
			response.sendRedirect(request.getContextPath()+"/admin/adduser?err=1");
			return;
		}else{
			//chua ton tai,cho them
			if(userDao.addUser(objUser)>0){
				response.sendRedirect(request.getContextPath()+"/admin/users?msg=1");
				return;
			}else{
				response.sendRedirect(request.getContextPath()+"/admin/users?err=1");
				return;
			}
		}
		
	}

}
