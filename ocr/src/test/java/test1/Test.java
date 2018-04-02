package test1;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.peerless.ocr.tesseract.Tess4jUtils;
import org.peerless.utils.ImageUtils;
import org.peerless.utils.ScreenUtils;

/**
 * test 
 * 
 * @author yinfeng.feng
 */
public class Test {

	public static void main(String[] args) {

		// 1¡¢½ØÆÁ
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmm");
		String yyyyMMdd = sdf.format(date);
		String yyyyMMddHHmm = sdf1.format(date);

		String filePath = "D:/Peerless/imgs/orgin/";
		String fileName = yyyyMMddHHmm + ".png";
		
		ScreenUtils.captureScreen(filePath, fileName);
		
		// 2¡¢Í¼Æ¬¼ô²Ã
		int left = 10;
		int top = 360;
		int width = 400;
		int height = 385;
		String imagePath = filePath + fileName;
		String cutImagePath = "D:/Peerless/imgs/" + yyyyMMdd + "/cut_" + fileName;

		ImageUtils.imageCut(left, top, width, height, imagePath, cutImagePath);
		
		// 3¡¢OCR Í¼ÏñÊ¶±ð
		String ocrImagePath = cutImagePath;
		String result = Tess4jUtils.readChar(ocrImagePath);
		
		System.out.println(result);
		
	}

}
