package model;

import java.io.Serializable;

public class Filter implements Serializable {
	private boolean acceptAM;
	private boolean acceptFI;
	private boolean acceptPS;
	private int port = 8080;
	
	public Filter(boolean acceptAM, boolean acceptFI, boolean acceptPS, int port) {
		this.acceptAM = acceptAM;
		this.acceptFI = acceptFI;
		this.acceptPS = acceptPS;
		this.port = port;
	}

	public boolean isAcceptAM() {
		return acceptAM;
	}

	public void setAcceptAM(boolean acceptAM) {
		this.acceptAM = acceptAM;
	}

	public boolean isAcceptFI() {
		return acceptFI;
	}

	public void setAcceptFI(boolean acceptFI) {
		this.acceptFI = acceptFI;
	}

	public boolean isAcceptPS() {
		return acceptPS;
	}

	public void setAcceptPS(boolean acceptPS) {
		this.acceptPS = acceptPS;
	}

	public int getPort() {
		return this.port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public boolean isAccepted(Message msg) {
		if(msg.getEvent().getEventType().equals("Asistencia Médica"))
			return acceptAM;
		if(msg.getEvent().getEventType().equals("Foco de Incendio"))
			return acceptFI;
		if(msg.getEvent().getEventType().equals("Personal de Seguridad"))
			return acceptPS;
		return false; 
	}
	
}
