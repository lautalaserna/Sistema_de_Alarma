package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import connection.Connection;
import connection.ReceptorData;
import model.Message;
import model.Servidor;
import view.VServer;

public class ControllerServer implements Observer{
	private VServer viewServer = null;
	private ArrayList<Observable> obs = new ArrayList<Observable>();
	
	public ControllerServer() {
		this.viewServer = new VServer();
		addObservable(Servidor.getInstance());
	}
	
	@Override
	public void update(Observable o, Object arg) {
		ArrayList<?> a = (ArrayList<?>) arg;		
		System.out.println("UPDATE DEL SERVER CONTROLLER:");
		System.out.println("- " + a.get(0));
		System.out.println("- " + a.get(0).getClass().getName());
		if(!a.isEmpty() && a.get(0).getClass().getName().equals("java.lang.String")) {
			System.out.println("Entra al if de Logs");
			this.viewServer.refreshLogs((ArrayList<String>) arg);
		} else if(!a.isEmpty() && a.get(0).getClass().getName().equals("connection.ReceptorData")) {
			this.viewServer.refreshReceptores((ArrayList<ReceptorData>) arg);
				
		}
	}
	
	public void addObservable(Observable o) {
		this.obs.add(o);
		o.addObserver(this);
	}
}
