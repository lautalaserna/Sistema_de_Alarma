package model;

import java.util.ArrayList;
import java.util.Observable;

import controller.ControllerSelection;

public class Servidor extends Observable{
	private ArrayList<String> logs = new ArrayList<String>();
	private static Servidor instance = null;
	
	private Servidor() {
	}
	
	public static Servidor getInstance() {
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

}
