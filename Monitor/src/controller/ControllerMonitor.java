package controller;

import connection.Connection;
import views.VMonitor;

public class ControllerMonitor {
	private VMonitor viewMonitor;
	private Connection conn;

	public ControllerMonitor() {
		this.viewMonitor = new VMonitor();
		this.conn = new Connection();
	}
	
	
}
