package org.peerless.service.scheduler;

import java.util.Timer;
import java.util.TimerTask;

import org.peerless.service.TesseractService;

/**
 * 调度管理
 * 
 * @author yinfeng.fyf
 */
public class TesseractSchedulerMain {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			public void run() {
				TesseractService ts = new TesseractService();
				ts.ocr();
			}
			
		}, 1000 * 10, 1000 * 60);

	}

}
