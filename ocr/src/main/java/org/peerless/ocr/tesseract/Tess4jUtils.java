package org.peerless.ocr.tesseract;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

/**
 * tesseract for java， ocr（Optical Character Recognition，光学字符识别） 
 * 
 * @author yinfeng.feng
 */
public class Tess4jUtils {

	private static final String ENG = "eng";

	private static final String CHI_SIM = "chi_sim";

	/**
	 * 从图片中提取文字,默认设置英文字库,使用classpath目录下的训练库
	 * 
	 * @param path
	 * @return
	 */
	public static String readChar(String path) {
		// JNA Interface Mapping
		ITesseract instance = new Tesseract();

		// ITesseract instance = new Tesseract1();
		File imageFile = new File(path);
		// In case you don't have your own tessdata, let it also be extracted for you
		// 这样就能使用classpath目录下的训练库了
		File tessDataFolder = LoadLibs.extractTessResources("tessdata");
		// Set the tessdata path
		instance.setDatapath(tessDataFolder.getAbsolutePath());
		// 英文库识别数字比较准确
		instance.setLanguage(ENG);

		return getOCRText(instance, imageFile);
	}

	/**
	 * 从图片中提取文字
	 * 
	 * @param path
	 *            图片路径
	 * @param dataPath
	 *            训练库路径
	 * @param language
	 *            语言字库
	 * @return
	 */
	public static String readChar(String path, String dataPath, String language) {
		ITesseract instance = new Tesseract();

		File imageFile = new File(path);
		instance.setDatapath(dataPath);
		// 英文库识别数字比较准确
		instance.setLanguage(language);

		return getOCRText(instance, imageFile);
	}

	/**
	 * 识别图片文件中的文字
	 * 
	 * @param instance
	 * @param imageFile
	 * @return
	 */
	private static String getOCRText(ITesseract instance, File imageFile) {
		String result = null;
		try {
			result = instance.doOCR(imageFile);
		} catch (TesseractException e) {
			e.printStackTrace();
		}
		return result;
	}

}