package test1;

import org.peerless.utils.ImageUtils;

/**
 * Test ImageUtils 
 * 
 * @author yinfeng.feng
 */
public class TestImageUtils {

	public static void main(String[] args) {

		int left = 10;
		int top = 360;
		int width = 400;
		int height = 385;
		String imagePath = "D:/Peerless/imgs/orgin/201804022239.png";
		String cutImagePath = "D:/Peerless/imgs/20180402/cut_201804022239.png";

		ImageUtils.imageCut(left, top, width, height, imagePath, cutImagePath);
		
	}

}
