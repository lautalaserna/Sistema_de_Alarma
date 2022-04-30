package connection;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class TimeOut extends Observable{
	private Timer timer;
	
	public void starTimer() {
		new Thread() {
			public void run() {
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					int i = 10;

					public void run() {
						System.out.println(i);
						i--;
						if (i < 0) {
							timer.cancel();
							setChanged();
							notifyObservers();
						}
					}
				}, 0, 1000);
			}
		}.start();
	}
	
	public void stopTimer() {
		timer.cancel();
	}
}
