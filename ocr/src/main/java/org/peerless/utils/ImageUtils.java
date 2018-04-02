package org.peerless.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * 图片剪裁
 * 
 * @author yinfeng.feng
 */
public class ImageUtils {

	/**
	 * 图片剪裁
	 * 
	 * @param left
	 * @param top
	 * @param width
	 * @param height
	 * @param imagePath
	 * @param cutImagePath
	 */
	public static void imageCut(int left, int top, int width, int height, String imagePath, String cutImagePath) {
		FileInputStream fis = null;
		ImageInputStream iis = null;
		try {
			String fileSuffix = imagePath.substring(imagePath.lastIndexOf(".") + 1);
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(fileSuffix);
			ImageReader reader = it.next();

			fis = new FileInputStream(imagePath);
			iis = ImageIO.createImageInputStream(fis);
			reader.setInput(iis, true);

			ImageReadParam param = reader.getDefaultReadParam();
			param.setSourceRegion(new Rectangle(left, top, width, height));

			BufferedImage bi = reader.read(0, param);

			File sescFile = new File(cutImagePath);
			if (!sescFile.getParentFile().exists()) {
				sescFile.getParentFile().mkdirs();
			}

			ImageIO.write(bi, fileSuffix, sescFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
					fis = null;
				}
				if (iis != null) {
					iis.close();
					iis = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
