package common;

import java.text.Normalizer;
import java.util.regex.Pattern;

import javax.servlet.http.Part;

public class StringUntil {
	public  static String getFileName(Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
		  public static  String createSlug(String title) {
				String slug = Normalizer.normalize(title, Normalizer.Form.NFD);
				Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
				slug = pattern.matcher(slug).replaceAll("");
				slug = slug.toLowerCase();
				// Thay đ thành d
				slug = slug.replaceAll("đ", "d");
				// Xóa các ký tự đặt biệt
				slug = slug.replaceAll("([^0-9a-z-\\s])", "");
				// Thay space thành dấu gạch ngang
				slug = slug.replaceAll("[\\s]", "-");
				// Đổi nhiều ký tự gạch ngang liên tiếp thành 1 ký tự gạch ngang
				slug = slug.replaceAll("(-+)", "-");
				// Xóa các ký tự gạch ngang ở đầu và cuối
				slug = slug.replaceAll("^-+", "");
				slug = slug.replaceAll("-+$", "");
				return slug;
			}

}
