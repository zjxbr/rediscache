package com.jarvis.rediscache;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.protobuf.InvalidProtocolBufferException;
import com.jarvis.rediscache.dao.ForTestDao;
import com.jarvis.rediscache.protobuf.ReportPB.ReportBean;

/**
 * Hello world!
 *
 */
public class App {
	static volatile int cnt = 0;

	public static void main(String[] args)
			throws InvalidProtocolBufferException {
		System.out.println("Hello World!");

		AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext();
		springContext.scan("com.jarvis.rediscache*");
		springContext.refresh();

		final ForTestDao forTestDao = springContext.getBean(ForTestDao.class);

		final ReportBean reportBean = ReportBean.newBuilder().setTaskId("1")
				.setStatus(1).setMessage("haha").setReportTime(1l).build();

		forTestDao.put(reportBean, reportBean);


		for (int i = 0; i < 6; i++) {
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						while (true) {

							if(forTestDao.get(reportBean) == null){
								System.err.println("NULL ERROR");
								
								break;
							}

							cnt++;
							if (cnt % 10000 == 0) {
								System.out.println(System.currentTimeMillis());
							}
						}
					} catch (InvalidProtocolBufferException e) {
						e.printStackTrace();
					}
				}
			});
			thread.start();
		}

		// System.out.println(reportBean2);

		// springContext.close();
	}
}
