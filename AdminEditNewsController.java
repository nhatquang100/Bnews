package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import common.AuthUtil;
import common.StringNameFile;
import common.StringUntil;
import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDao;
import model.dao.NewsDao;

@MultipartConfig
public class AdminEditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDao category;
	private NewsDao newsDao;

	public AdminEditNewsController() {
		super();
		category = new CategoryDao();
		newsDao = new NewsDao();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("txt/html");
		String idText = (String) request.getParameter("nid");
		int idnews = 0;
		try {
			idnews = Integer.parseInt(idText);
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()
					+ "/admin/news?err=2");
		}

		News obj = newsDao.getItem(idnews);
		if (obj == null) {
			response.sendRedirect(request.getContextPath()
					+ "/admin/news?err=1");
			return;
		} else {
			ArrayList<Category> list = category.getItems();
			request.setAttribute("obj", obj);
			request.setAttribute("list", list);
			RequestDispatcher rd = request
					.getRequestDispatcher("/admin/editNews.jsp");
			rd.forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("txt/html");

		String idText = (String) request.getParameter("nid");
		int idnews = 0;
		try {
			idnews = Integer.parseInt(idText);
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/news");
		}
		int catID = 0;
		// get du lieu
		String name = request.getParameter("tentin");
		try {
			catID = Integer.parseInt(request.getParameter("danhmuc"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/news");
		}
		String preview = request.getParameter("mota");
		String detail = request.getParameter("chitiet");
		PrintWriter outw = response.getWriter();
		// lấy thông tin file
		final Part part = request.getPart("hinhanh");
		// lấy tên file
		String fileName = StringUntil.getFileName(part);

		String nameFile = "";
		// duong dan luu file
		final String path = request.getServletContext().getRealPath("/files");
		System.out.println("đường dẫn ảnh nè:" + path);
		File dirPath = new File(path);
		if (!dirPath.exists()) {
			dirPath.mkdir();
		}
		if (!"".equals(fileName)) {
			if (!"".equals(newsDao.getItem(idnews).getPicture())) {
				// xoa hinh anh cu(xoa file)
				String urlFileDel = path + File.separator
						+ newsDao.getItem(idnews).getPicture();
				File delFile = new File(urlFileDel);
				delFile.delete();
			}

			// doi ten file theo time
			nameFile = StringNameFile.getFileName(fileName);
		} else {
			nameFile = newsDao.getItem(idnews).getPicture();
		}
		// SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		// String date=sdf.format(new Date());
		Timestamp dateCreate = new Timestamp(new Date().getTime());
		News item = new News(idnews, name, preview, detail, dateCreate, catID,
				nameFile, null);
		if (newsDao.editItem(item) > 0) {
			// sua thanh cong
			// ghi file
			String filePath = path + File.separator + nameFile;
			part.write(filePath);
			response.sendRedirect(request.getContextPath()
					+ "/admin/news?msg=2");
			return;
		} else {
			RequestDispatcher rd = request
					.getRequestDispatcher("/admin/editNews.jsp?err=1");
			rd.forward(request, response);
		}
	}

}
