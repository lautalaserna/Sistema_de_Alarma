package connection;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class TimeOut extends Observable{
	private String value;
	private Timer timer;
	
	public TimeOut(String value) {
		this.value = value;
	}
	
	public void starTimer(int seg) {
		new Thread() {
			public void run() {
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					int i = seg;

					public void run() {
						i--;
						if (i < 0) {
							timer.cancel();
							System.out.println("Timer Terminado: "+ value);
							setChanged();
							notifyObservers(value + " OFFLINE"); 
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
