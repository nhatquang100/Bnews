package common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;

public class AuthUtil {
	public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		User userLogin=(User) session.getAttribute("userLogin");
		if(userLogin==null){
			try {
				response.sendRedirect(request.getContextPath()+"/admin/login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		return true;
	}
}
