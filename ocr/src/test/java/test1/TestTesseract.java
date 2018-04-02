package test1;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.peerless.ocr.tesseract.Tess4jUtils;
import org.peerless.utils.ScreenUtils;

/**
 * Test Tesseract 
 * 
 * @author yinfeng.feng
 */
public class TestTesseract {

	public static void main(String[] args) {

		// 1、截屏
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmm");
		String yyyyMMdd = sdf.format(date);
		String yyyyMMddHHmm = sdf1.format(date);

		String filePath = "D:/Peerless/imgs/" + yyyyMMdd + "/";
		String fileName = yyyyMMddHHmm + ".png";
		
		int left = 10;
		int top = 360;
		int width = 400;
		int height = 385;

		ScreenUtils.captureScreen(filePath, fileName, left, top, width, height);
		
		// 2、OCR 图像识别
		String imagePath = filePath + fileName;
		String result = Tess4jUtils.readChar(imagePath);
		String result2 = Tess4jUtils.readChar(imagePath, Tess4jUtils.CHI_SIM);
		
		System.out.println(result);
		System.out.println(result2);
		
	}

}
