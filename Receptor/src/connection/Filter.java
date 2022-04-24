package connection;

import java.util.ArrayList;

import model.Event;
import model.Message;

public class Filter {
	private ArrayList<Event> accept = null;
	
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
