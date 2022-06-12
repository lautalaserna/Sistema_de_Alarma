package connection;

import java.util.Observable;

public class Connection extends Observable{
	private IConnection conn;
	
	public void setConnection(IConnection conn) {
		this.conn = conn;
		conn.listen();
		conn.heartbeat();
	}
	
	public IConnection getConnection() {
		return this.conn;
	}

	public void changeToMain() {
		this.conn.closeConnections();
		this.conn = new ConnectionMain();
		this.conn.listen();
		this.conn.heartbeat();	
		setChanged();
		notifyObservers("Servidor Primario");
	}
	
}
