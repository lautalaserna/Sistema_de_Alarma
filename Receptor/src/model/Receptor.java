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
	
	public static void main(String[] args) {
		Receptor receptor = Receptor.getInstance();
		Filter filter = Persistence.getFilterFromBin();
		receptor.setFilter(filter);
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
	
	
}
