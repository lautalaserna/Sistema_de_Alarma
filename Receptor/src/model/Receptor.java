package model;

import java.util.ArrayList;
import java.util.Observable;

import connection.ServerData;
import controller.ControllerAccept;
import persistence.Persistence;

public class Receptor extends Observable {
	private static Receptor instance = null;
	ArrayList<Message> reg;
	Filter filter;
	ServerData serverData;
	
	public static void main(String[] args) {
		Receptor receptor = Receptor.getInstance();
		
		Filter filter = Persistence.getFilterFromBin("data/filter.bin");
		receptor.setFilter(filter);
		
		ServerData svd = Persistence.getServerDataFromBin("data/server.bin");
		receptor.setServerData(svd);
		
		new ControllerAccept();
	}
	
	private Receptor() {
		this.reg = new ArrayList<Message>();
	}

	public static Receptor getInstance() {
		if (instance == null) {
			instance = new Receptor();
		}
		return instance;
	}
	
	public ArrayList<Message> getReg() {
		return reg;
	}

	public void setReg(ArrayList<Message> reg) {
		this.reg = reg;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	
	public void addMessage(Message msg) {
		this.reg.add(msg);
		setChanged();
		notifyObservers(this.reg);
	}
	
	public void setServerData(ServerData serverData) {
		this.serverData = serverData;
	}
	
	public ServerData getServerData() {
		return serverData;
	}
}
