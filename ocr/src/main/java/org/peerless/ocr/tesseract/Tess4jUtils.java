package org.peerless.ocr.tesseract;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

/**
 * tesseract for java， ocr（Optical Character Recognition，光学字符识别）
 * 
 * @author yinfeng.fyf
 */
public class Tess4jUtils {

	public static final String ENG = "eng";

	public static final String CHI_SIM = "chi_sim";

	/**
	 * 从图片中提取文字，默认使用英文字库
	 * 
	 * @param path - 图片路径
	 * @return
	 */
	public static String readChar(String path) {
		return readChar(path, ENG);
	}

	/**
	 * 从图片中提取文字
	 * 
	 * @param path - 图片路径
	 * @param language - 语言字库
	 * @return
	 */
	public static String readChar(String path, String language) {
		String result = null;
		try {
			ITesseract instance = new Tesseract();

			File imageFile = new File(path);
			// 使用classpath目录下的训练库
			File tessDataFolder = LoadLibs.extractTessResources("tessdata");
			// Set the tessdata path
			instance.setDatapath(tessDataFolder.getAbsolutePath());
			// 英文库识别数字比较准确
			instance.setLanguage(ENG);

			result = instance.doOCR(imageFile);
		} catch (TesseractException e) {
			e.printStackTrace();
		}
		return result;
	}

}