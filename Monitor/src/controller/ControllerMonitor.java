package controller;

import java.util.Observable;
import java.util.Observer;

import connection.Connection;
import connection.TimeOut;
import views.VMonitor;

public class ControllerMonitor implements Observer{
	private VMonitor viewMonitor;
	private Connection conn;

	public ControllerMonitor() {
		this.viewMonitor = new VMonitor();
		
		TimeOut timerMain = new TimeOut("MAIN");
		timerMain.addObserver(this);
		TimeOut timerAux = new TimeOut("AUX");
		timerAux.addObserver(this);
		this.conn = new Connection(timerMain, timerAux);
		this.conn.addObserver(this);
	}
	
	public void setMain(boolean isAlive) {
		
	}
	
	public void setAux(boolean isAlive) {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg.getClass().getName().equals("java.lang.String")) {
			if(((String) arg).equals("MAIN OFFLINE")) {
				viewMonitor.setPrincipalOffline();
			} else if(((String) arg).equals("AUX OFFLINE")) {
				viewMonitor.setSecundarioOffline();
			} else if(((String) arg).equals("MAIN ONLINE")) {
				viewMonitor.setPrincipalOnline();
			} else if(((String) arg).equals("AUX ONLINE")) {
				viewMonitor.setSecundarioOnline();
			}
		}
	}
	
}
