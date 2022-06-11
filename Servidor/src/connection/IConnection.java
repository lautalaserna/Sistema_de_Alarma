package connection;

import java.util.ArrayList;

public interface IConnection {
	
	public void listen();
	
	public void heartbeat();
	
	public void closeConnections();
	
	public boolean switchConnection();
}
