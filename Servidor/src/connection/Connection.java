package connection;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Connection{
	private IConnection conn;

	public Connection(IConnection conn) throws SocketException, UnknownHostException {
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
	}

}
