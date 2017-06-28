package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.StringLibrary;
import common.StringUntil;
import model.bean.User;
import model.dao.UserDao;

//@WebServlet("/AdminLoginController")
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private UserDao userDao;  

    public AdminLoginController() {
        super();
        userDao=new UserDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		//neu login roi thi no se sendrecdi qua trang hien thi lun
		User userLogin=(User) session.getAttribute("userLogin");
		if(userLogin!=null){
			response.sendRedirect(request.getContextPath()+"/admin");
			return;
		}
		RequestDispatcher rd=request.getRequestDispatcher("/admin/Login.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("txt/html");
		
		//neu login roi thi no se sendrecdi qua trang hient hi lun
		HttpSession session=request.getSession();
		User userLogin=(User) session.getAttribute("userLogin");
		if(userLogin!=null){
			response.sendRedirect(request.getContextPath()+"/admin");
			return;
		}
		
		String username=request.getParameter("username");
		String password=StringLibrary.md5(request.getParameter("password"));
		User user=userDao.existUser(username,password);
		if(user!=null){
			session.setAttribute("userLogin", user);
			response.sendRedirect(request.getContextPath()+"/admin");
			return;
		}else{
			response.sendRedirect(request.getContextPath()+"/admin/login?err=1");
			return;
		}
	}

}
