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
import common.StringLibrary;
import model.bean.User;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminAddCatController
 */
//@WebServlet("/AdminAddCatController")
public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditUserController() {
        super();
        userDao=new UserDao();
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
			response.sendRedirect(request.getContextPath()+"/admin/users?err=2");
			return;
		}
		User objUser=userDao.getItem(id);
		if(objUser==null){
			response.sendRedirect(request.getContextPath()+"/admin/users?err=3");
			return;
		}else{
			request.setAttribute("objUser", objUser);
			RequestDispatcher rd=request.getRequestDispatcher("/admin/editUser.jsp");
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
		String idText=(String)request.getParameter("id");
		String pass=request.getParameter("pass");
		String fullname=request.getParameter("fullname");		
		int id=0;
		try {
			 id=Integer.parseInt(idText);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/users?err=2");
			return;
		}
		//check id url-ko cho doi
		HttpSession session=request.getSession();
		
		User userinfo=(User)session.getAttribute("userLogin");
		if("admin".equals(userDao.getItem(userinfo.getID()).getUsername()) || (id==userinfo.getID())){
		
			if("".equals(pass)){
				pass=userDao.getItem(id).getPassword();
			}else{
				//cập nhật mật khẩu mới
				pass=StringLibrary.md5(request.getParameter("pass"));
			}
			User item=new User(id,pass,fullname) ;
			if(userDao.editItem(item)>0){
				response.sendRedirect(request.getContextPath()+"/admin/users?msg=2");
				return;
			}else{
				RequestDispatcher rd=request.getRequestDispatcher("/admin/editUser.jsp?err=1");
				rd.forward(request, response);
				return;
			}
		}else{
			response.sendRedirect(request.getContextPath()+"/admin/users?err=6");
			return;
		}
	}

}
