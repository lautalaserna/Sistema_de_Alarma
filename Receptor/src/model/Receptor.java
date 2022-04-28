package model;

import java.util.ArrayList;

import connection.Filter;

public class Receptor {
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
	
}
