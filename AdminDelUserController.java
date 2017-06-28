package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.AuthUtil;

import model.bean.Category;
import model.bean.User;
import model.dao.CategoryDao;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminDelCatController
 */
//@WebServlet("/AdminDelCatController")
public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDao userDao;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelUserController() {
        super();
        userDao =new UserDao();
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
			response.sendRedirect(request.getContextPath()+"/admin/users?err=1");
			return;
		}
		HttpSession session=request.getSession();
		User userinfo=(User)session.getAttribute("userLogin");
		User objUser=userDao.getItem(id);
		//kiem tra neu la admin vs doi id tren url thi k dc xoa
		if("admin".equals(objUser.getUsername())){
			response.sendRedirect(request.getContextPath()+"/admin/users?err=5");
			return;
		}else{
			if("admin".equals(userinfo.getUsername())){
				if(userDao.delItem(id)>0){
					response.sendRedirect(request.getContextPath()+"/admin/users?msg=3");
					return;
				}else{
					response.sendRedirect(request.getContextPath()+"/admin/users?err=4");
					return;
				}
			}else{
				response.sendRedirect(request.getContextPath()+"/admin/users?err=5");
				return;
			}
		}
	}

}
