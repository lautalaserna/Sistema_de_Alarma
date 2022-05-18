package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import connection.Connection;
import view.VServer;

public class ControllerServer implements Observer{

	private VServer viewServer = null;
	private ArrayList<Observable> obs = new ArrayList<Observable>();
	
	public ControllerServer(Connection connection) {
		this.viewServer = new VServer();
		addObservable(connection);
		
	}
	
	public void addLog(String log) {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("LLEGÓ AL UPDATE = " + arg.toString());
		this.viewServer.addLog(arg.toString());
		
	}
	
	public void addObservable(Observable o) {
		this.obs.add(o);
		o.addObserver(this);
	}
}
