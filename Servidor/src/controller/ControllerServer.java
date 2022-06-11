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
			this.viewServer.refreshList((ArrayList<String>)arg);
		}

		if(!a.isEmpty() && a.get(0).getClass().getName().equals("connection.ReceptorData")) {
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(ReceptorData receptor : Servidor.getInstance().getReceptors()) {
				System.out.println("Receptor:" + receptor.toString());
			}
				
		}
		
//		if(arg.getClass().arrayType().getClass().getName().equals("java.lang.String")) {
//		for(String log : (ArrayList<String>)arg)
//			System.out.println("Controlador:" + log);
//		this.viewServer.refreshList((ArrayList<String>)arg);
//		}
		
		//}
//		if(arg.getClass().arrayType().getName().equals("connection.ReceptorData")) {
//			//Actualizar lista de receptores.
//			this.viewServer.refreshList((ArrayList<ReceptorData>)arg);
//		}
		
		
//		if(arg.getClass().getName().equals("connection.ReceptorData")) {
//			this.viewServer.addLog("Receptor Suscripto: " + arg.toString());			
//		} else if(arg.getClass().getName().equals("model.Message")) {
//			this.viewServer.addLog("Nuevo Mensaje: "+ 
//									"(Desde: " + ((Message) arg).getInetAddress().getHostAddress() + ":" + ((Message) arg).getPort() + 
//									") " + arg.toString());
//		} else if (arg.getClass().getName().equals("java.lang.String")) {
//			this.viewServer.addLog((String) arg);
//		}
	}
	
	public void addObservable(Observable o) {
		this.obs.add(o);
		o.addObserver(this);
	}
}
