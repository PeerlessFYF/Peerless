package org.peerless.ocr.tesseract;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

/**
 * tesseract for java�� ocr��Optical Character Recognition����ѧ�ַ�ʶ�� 
 * 
 * @author yinfeng.feng
 */
public class Tess4jUtils {

	private static final String ENG = "eng";

	private static final String CHI_SIM = "chi_sim";

	/**
	 * ��ͼƬ����ȡ����,Ĭ������Ӣ���ֿ�,ʹ��classpathĿ¼�µ�ѵ����
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
		// ��������ʹ��classpathĿ¼�µ�ѵ������
		File tessDataFolder = LoadLibs.extractTessResources("tessdata");
		// Set the tessdata path
		instance.setDatapath(tessDataFolder.getAbsolutePath());
		// Ӣ�Ŀ�ʶ�����ֱȽ�׼ȷ
		instance.setLanguage(ENG);

		return getOCRText(instance, imageFile);
	}

	/**
	 * ��ͼƬ����ȡ����
	 * 
	 * @param path
	 *            ͼƬ·��
	 * @param dataPath
	 *            ѵ����·��
	 * @param language
	 *            �����ֿ�
	 * @return
	 */
	public static String readChar(String path, String dataPath, String language) {
		ITesseract instance = new Tesseract();

		File imageFile = new File(path);
		instance.setDatapath(dataPath);
		// Ӣ�Ŀ�ʶ�����ֱȽ�׼ȷ
		instance.setLanguage(language);

		return getOCRText(instance, imageFile);
	}

	/**
	 * ʶ��ͼƬ�ļ��е�����
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