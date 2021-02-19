package Bresenham;
import java.awt.EventQueue;

import UI.GUI;

public class Demo {

	private static long sleepTime = 0;
	private static long lastSimTime = 0;	
	
	public static void main(String[] args) throws InterruptedException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI.initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		while (true) {
			long executionSimTime=0;
			long preSimTime = System.currentTimeMillis();
			if ( preSimTime > lastSimTime + sleepTime) {
				lastSimTime = System.currentTimeMillis();
				GUI.paint();
				executionSimTime = System.currentTimeMillis() - preSimTime;
			}
			sleepTime = 100 - executionSimTime;
			sleepTime = sleepTime < 1 ? 1 : sleepTime;
			Thread.sleep(20);
		}
	}
}