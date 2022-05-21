package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import connection.Connection;
import model.Message;
import view.VServer;

public class ControllerServer implements Observer{
	private VServer viewServer = null;
	private ArrayList<Observable> obs = new ArrayList<Observable>();
	
	public ControllerServer(Connection connection) {
		this.viewServer = new VServer();
		addObservable(connection);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg.getClass().getName().equals("connection.ReceptorData")) {
			this.viewServer.addLog("Receptor Suscripto: " + arg.toString());			
		} else if(arg.getClass().getName().equals("model.Message")) {
			this.viewServer.addLog("Nuevo Mensaje: "+ 
									"(Desde: " + ((Message) arg).getInetAddress().getHostAddress() + ":" + ((Message) arg).getPort() + 
									") " + arg.toString());
		} else if (arg.getClass().getName().equals("java.lang.String")) {
			this.viewServer.addLog((String) arg);
		}
	}
	
	public void addObservable(Observable o) {
		this.obs.add(o);
		o.addObserver(this);
	}
}
