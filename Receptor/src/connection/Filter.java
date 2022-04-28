package connection;

import java.io.Serializable;
import java.util.ArrayList;

import model.Event;
import model.Message;

public class Filter implements Serializable {
	private ArrayList<Event> accept = null;
	private int port = 8080;
	
	public Filter(ArrayList<Event> accept, int port) {
		this.accept = accept;
		this.port = port;
	}
	
	public Filter(ArrayList<Event> accept) {
		this.accept = accept;
	}
	
	public ArrayList<Event> getAccept(){
		return this.accept;
	}
	
	public void setAccept(ArrayList<Event> accept){
		this.accept = accept;
	}
	
	public boolean isAccepted(Message m) {
		return accept.contains(m.getEvent());
	}
	
}
