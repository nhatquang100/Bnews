package common;

import javax.servlet.http.Part;

public class StringNameFile {
	public  static String getFileName(String fileName) {
		String[] arrImg =  fileName.split("\\.");
		String duoiFileImg = arrImg[arrImg.length - 1];
		String nameFile = "";
		for (int i  = 0;i< (arrImg.length - 1) ; i++) {
			if(i == 0){
				nameFile = arrImg[i];
			}else{
				nameFile += "-"+arrImg[i];
			}
		}
		//ten file duoc luu
		return nameFile + "-"+System.nanoTime() +"."+duoiFileImg;
	}
}
