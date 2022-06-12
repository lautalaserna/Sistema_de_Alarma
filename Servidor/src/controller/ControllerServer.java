package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import connection.ReceptorData;
import model.Servidor;
import view.VServer;

public class ControllerServer implements Observer{
	private VServer viewServer = null;
	private ArrayList<Observable> obs = new ArrayList<Observable>();
	
	public ControllerServer(String type) {
		this.viewServer = new VServer();
		addObservable(Servidor.getInstance());
		setServerType(type);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o.getClass().getName().equals("connection.Connection")) {
			setServerType((String) arg);
		} else {
			ArrayList<?> a = (ArrayList<?>) arg;
			if(!a.isEmpty() && a.get(0).getClass().getName().equals("java.lang.String")) {
				this.viewServer.refreshLogs((ArrayList<String>) arg);
			} else if(!a.isEmpty() && a.get(0).getClass().getName().equals("connection.ReceptorData")) {
				this.viewServer.refreshReceptores((ArrayList<ReceptorData>) arg);
			} else if(a.isEmpty()) {
				this.viewServer.refreshReceptores((ArrayList<ReceptorData>) arg);
			}
		}
	}
	
	public void addObservable(Observable o) {
		this.obs.add(o);
		o.addObserver(this);
	}
	
	public void setServerType(String str) {
		this.viewServer.setLblServidor(str);
	}
}
