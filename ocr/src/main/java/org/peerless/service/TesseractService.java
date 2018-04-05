package org.peerless.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.peerless.db.MySqlDBHandler;
import org.peerless.ocr.tesseract.Tess4jUtils;
import org.peerless.utils.ScreenUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

/**
 * Tesseract Service
 * 
 * @author yinfeng.fyf
 */
public class TesseractService {

	/**
	 * ocr 光学字符识别
	 */
	public void ocr() {
		// 1、截屏
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmm");
		String yyyyMMdd = sdf.format(date);
		String yyyyMMddHHmm = sdf1.format(date);

		String filePath = "D:/Peerless/imgs/" + yyyyMMdd + "/";
		String fileName = yyyyMMddHHmm + ".png";

		int left = 10;
		int top = 360;
		int width = 400;
		int height = 385;

		ScreenUtils.captureScreen(filePath, fileName, left, top, width, height);

		// 2、OCR 图像识别
		String imagePath = filePath + fileName;
		String result = Tess4jUtils.readChar(imagePath);
		System.out.println(result);

		// 3、解析文本
		Map<Integer, Integer> percentMap = readText(result);
		System.out.println(yyyyMMddHHmm + ": " + JSON.toJSONString(percentMap));

		// 4、保存入库
		MySqlDBHandler m = new MySqlDBHandler();
		String tableName = "cmt_txffc_5_2";
		Map<String, Object> map = Maps.newHashMap();
		map.put("biz_day", yyyyMMdd);
		map.put("biz_date", yyyyMMddHHmm);
		map.put("col100", percentMap.get(100) == null ? 0 : percentMap.get(100));
		map.put("col95", percentMap.get(95) == null ? 0 : percentMap.get(95));
		map.put("col90", percentMap.get(90) == null ? 0 : percentMap.get(90));
		map.put("col85", percentMap.get(85) == null ? 0 : percentMap.get(85));
		map.put("col80", percentMap.get(80) == null ? 0 : percentMap.get(80));
		m.insert(tableName, map);

	}

	/**
	 * 解析文本
	 * 
	 * @param str
	 * @return
	 */
	private Map<Integer, Integer> readText(String str) {
		Map<Integer, Integer> percentMap = Maps.newHashMap();
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(
					new ByteArrayInputStream(str.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));
			String line;
			int count = 0;
			while ((line = br.readLine()) != null) {
				if (!line.trim().equals("")) {
					if (count % 2 == 0) {
//						System.out.println("========>" + line);
					} else {
						String[] pArr = line.split("%");
						for (String p : pArr) {
							p = p.trim();
							p = (StringUtils.equals("55", p)) ? "85" : p;
							p = (StringUtils.equals("50", p)) ? "80" : p;
							int key = 0;
							try {
								key = Integer.parseInt(p);
							} catch (Exception e) {
								System.out.println("error: " + e.getMessage());
								continue;
							}
							Integer percentCount = percentMap.get(key);
							if (percentCount == null) {
								percentMap.put(key, 1);
							} else {
								percentMap.put(key, ++percentCount);
							}

						}
					}
					count++;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return percentMap;
	}

}
