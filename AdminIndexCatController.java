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
import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDao;

/**
 * Servlet implementation class AdminÍndexCatController
 */
public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDao categryDAO;

	public AdminIndexCatController() {
		categryDAO = new CategoryDao();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			return;
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		// tong so tin
		int sumCat = categryDAO.countCat();
		int sumPage = (int) Math.ceil((float) sumCat / Define.ROW_COUNT_ADMIN);
		// lay trang hien tai=curent_page
		int current_page = 1;
		if (request.getParameter("page") != null) {
			current_page = Integer.parseInt(request.getParameter("page"));
		}
		int offset = (current_page - 1) * Define.ROW_COUNT_ADMIN;
		ArrayList<Category> listCat = categryDAO.getItemsPagenation(offset);
		request.setAttribute("sumPage", sumPage);
		request.setAttribute("current_page", current_page);
		
		request.setAttribute("list", listCat);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/indexCat.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			return;
		}
		doGet(request, response);
	}

}
