package connection;

public interface IConnection {
	
	public void listen();
	
	public void heartbeat();
	
	public void closeConnections();
	
}
