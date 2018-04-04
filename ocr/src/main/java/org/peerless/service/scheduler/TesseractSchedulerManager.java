package org.peerless.service.scheduler;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.peerless.service.TesseractService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 调度管理
 * 
 * @author yinfeng.fyf
 */
public class TesseractSchedulerManager implements InitializingBean, DisposableBean {

	private static final ScheduledExecutorService SCHEDULER = new ScheduledThreadPoolExecutor(1,
			new BasicThreadFactory.Builder().namingPattern("ocr-pool-%d").daemon(true).build());

	public void afterPropertiesSet() throws Exception {
		SCHEDULER.scheduleWithFixedDelay(new Task(), 60, 30, TimeUnit.SECONDS);
	}

	public void destroy() throws Exception {
		SCHEDULER.shutdown();
	}

	private class Task implements Runnable {

		public void run() {
			TesseractService ts = new TesseractService();
			ts.ocr();
		}
	}

}
