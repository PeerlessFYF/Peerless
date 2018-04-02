package org.peerless.utils;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * ½ØÆÁ
 * 
 * @author yinfeng.feng
 */
public class ScreenUtils {

	/**
	 * ½ØÆÁ
	 * 
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 */
	public static void captureScreen(String filePath, String fileName) {

		try {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screenRectangle = new Rectangle(screenSize);
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenRectangle);

			File screenFile = new File(filePath);
			if (!screenFile.getParentFile().exists()) {
				screenFile.getParentFile().mkdirs();
			}
			if (!screenFile.exists() && !screenFile.isDirectory()) {
				screenFile.mkdir();
			}

			File f = new File(screenFile, fileName);
			ImageIO.write(image, "png", f);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
