package model;

import java.util.ArrayList;

public class Receptor {
	private static Receptor instance = null;
	ArrayList<Message> reg;
	
	
	private Receptor() {
		
	}

	public static Receptor getInstance() {
		if (instance == null) 
			instance = new Receptor();
		
		return instance;
	}
	
	
}
