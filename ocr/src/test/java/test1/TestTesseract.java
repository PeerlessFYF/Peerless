package test1;

import org.peerless.service.TesseractService;

/**
 * Test Tesseract
 * 
 * @author yinfeng.fyf
 */
public class TestTesseract {

	public static void main(String[] args) {

		TesseractService ts = new TesseractService();
		ts.ocr();
	}

}
