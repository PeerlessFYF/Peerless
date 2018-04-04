package test1;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.peerless.utils.ScreenUtils;

/**
 * test ScreenUtils
 * 
 * @author yinfeng.fyf
 */
public class TestScreenUtils {

	public static void main(String[] args) {

		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmm");
		String yyyyMMddHHmm = sdf1.format(date);

		String filePath = "D:/Peerless/imgs/orgin/";
		String fileName = yyyyMMddHHmm + ".png";
		
		ScreenUtils.captureScreen(filePath, fileName);
		
		int left = 10;
		int top = 360;
		int width = 400;
		int height = 385;
		ScreenUtils.captureScreen(filePath, "f_" + fileName, left, top, width, height);
		
	}

}
