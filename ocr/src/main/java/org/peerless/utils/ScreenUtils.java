package org.peerless.utils;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 截屏
 * 
 * @author yinfeng.fyf
 */
public class ScreenUtils {

	/**
	 * 截屏 全桌面
	 * 
	 * @param filePath
	 * @param fileName
	 */
	public static void captureScreen(String filePath, String fileName) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rect = new Rectangle(screenSize);

		captureScreen(filePath, fileName, rect);
	}

	/**
	 * 截屏 指定区域
	 * 
	 * @param filePath
	 * @param fileName
	 * @param left
	 * @param top
	 * @param width
	 * @param height
	 */
	public static void captureScreen(String filePath, String fileName, int left, int top, int width, int height) {
		Rectangle rect = new Rectangle(left, top, width, height);

		captureScreen(filePath, fileName, rect);
	}

	/**
	 * 截屏
	 * 
	 * @param filePath
	 * @param fileName
	 * @param rect
	 */
	private static void captureScreen(String filePath, String fileName, Rectangle rect) {
		try {
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(rect);

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
