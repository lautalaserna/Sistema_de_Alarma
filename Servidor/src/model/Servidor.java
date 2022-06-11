package model;

import java.util.ArrayList;
import java.util.Observable;

import connection.ReceptorData;
import controller.ControllerSelection;

public class Servidor extends Observable{
	private ArrayList<String> logs = new ArrayList<String>();
	private ArrayList<ReceptorData> receptors = new ArrayList<ReceptorData>();
	private static Servidor instance = null;
	
	private Servidor() {
	}
	
	public static Servidor getInstance() {
		if (instance == null) {
			instance = new Servidor();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		try {
			new ControllerSelection();
		} catch (Exception e) {
			System.out.println("Error al conectar");
			e.printStackTrace();
		} 
	}
	
	public void addLog(String log) {
		this.logs.add(log);
		setChanged();
		notifyObservers(this.logs);
	}
	
	public void addReceptor(ReceptorData rd) {
		this.receptors.add(rd);
		setChanged();
		notifyObservers(this.receptors);
	}

	public ArrayList<ReceptorData> getReceptors() {
		return receptors;
	}

	public void setReceptors(ArrayList<ReceptorData> receptors) {
		this.receptors = receptors;
		setChanged();
		notifyObservers(this.receptors);
	}

	public ArrayList<String> getLogs() {
		return logs;
	}
	
	public void setLogs(ArrayList<String> logs) {
		this.logs = logs;
		System.out.println("LOGS DEL SERVIDOR:");
		for(String log: this.logs) {
			System.out.println("- " + log);
		}
		setChanged();
		notifyObservers(this.logs);
	}

}
