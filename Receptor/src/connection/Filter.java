package connection;

import java.io.Serializable;
import java.util.ArrayList;

import model.Event;
import model.Message;

public class Filter implements Serializable {
	private boolean acceptAM = false;
	private boolean acceptFI = false;
	private boolean acceptPS = false;
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
	
}
