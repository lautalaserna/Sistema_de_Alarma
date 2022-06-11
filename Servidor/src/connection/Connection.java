package connection;

public class Connection{
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
	}
	
}
