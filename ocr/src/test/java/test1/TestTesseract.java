package test1;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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
//		String result2 = Tess4jUtils.readChar(imagePath, Tess4jUtils.CHI_SIM);

		System.out.println(result);
//		System.out.println(result2);

		readText(result);
	}

	public static void readText(String str) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new ByteArrayInputStream(str.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));
			String line;
			int count = 0;
			while ((line = br.readLine()) != null) {
				if (!line.trim().equals("")) {
					if (count % 2 == 0) {
						System.out.println("========>" + line);
					} else {
						System.out.println("====%====>" + line);
					}
					count++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
