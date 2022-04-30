package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import connection.Filter;
import controller.ControllerAccept;
import persistence.Persistence;

public class Receptor extends Observable {
	private static Receptor instance = null;
	ArrayList<Message> reg;
	Filter filter;
	
	private Receptor() {
		this.reg = new ArrayList<Message>();
	}

	public static Receptor getInstance() {
		if (instance == null) {
			instance = new Receptor();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		Receptor receptor = Receptor.getInstance();
		Filter filter = null;
		
		try {
			filter = Persistence.getFilterFromBin("filter.bin");
		} catch (Exception e) {
			File file = new File("filter.bin");
			try {
				file.createNewFile();
				filter = new Filter(false,false,false,8080);
			} catch (IOException e1) {
				// O no encontró la IP o hay un problema con el archivo.
				e.printStackTrace();
			}
		}
		
		receptor.setFilter(filter);
		new ControllerAccept();
		
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
	
}
