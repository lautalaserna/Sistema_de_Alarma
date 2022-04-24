package connection;

import java.util.Timer;
import java.util.TimerTask;

public class TimeOut {
	Timer timer;
	
	public TimeOut() {
		this.timer = new Timer();
	}
	
	public void startTimer() {
		this.timer.scheduleAtFixedRate(new TimerTask() {
            int i = 10;
            
            public void run() {
            	System.out.println(i);
                i--;
                if (i < 0) {
                    timer.cancel();
                    System.out.println("NO SE RECIBIO");
                    // Mensaje no recibido
                    // Se apaga la alarma
                }
            }
        }, 0, 1000);
    }
	
	public void stopTimer() {
		this.timer.cancel();
		System.out.println("SE RECIBIO");
		// Mensaje recibido
		// Se apaga la alarma
	}
	
	public static void main(String[] args) {
		TimeOut to = new TimeOut();
		to.startTimer();
		to.stopTimer();
	}
	
}
