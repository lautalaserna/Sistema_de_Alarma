package connection;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import model.Message;

public class TimeOut extends Observable{
	private Timer timer;
	
	public void starTimerFromMsg(Message msg) {
		new Thread() {
			public void run() {
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					int i = 30;

					public void run() {
						System.out.println(i);
						i--;
						if (i < 0) {
							timer.cancel();
							// Aca puede que falte algo
							setChanged();
							notifyObservers(msg);
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
