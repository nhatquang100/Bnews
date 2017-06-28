package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.AuthUtil;

import constant.Define;
import model.bean.News;
import model.bean.User;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminIndexUserController
 */
// @WebServlet("/AdminIndexUserController")
public class AdminIndexUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public AdminIndexUserController() {
		super();
		userDao = new UserDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		HttpSession session = request.getSession();

		// tong so tin
		int sumuser = userDao.countUser();
		int sumPage = (int) Math.ceil((float) sumuser / Define.ROW_COUNT_ADMIN);
		// lay trang hien tai=curent_page
		int current_page = 1;
		if (request.getParameter("page") != null) {
			current_page = Integer.parseInt(request.getParameter("page"));
		}
		int offset = (current_page - 1) * Define.ROW_COUNT_ADMIN;
		ArrayList<User> list = userDao.getItemsPagenation(offset);

		request.setAttribute("sumPage", sumPage);
		request.setAttribute("current_page", current_page);
		request.setAttribute("listUser", list);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/indexUser.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
	}

}
