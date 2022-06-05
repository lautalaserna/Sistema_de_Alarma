package connection;

import java.net.DatagramSocket;
import java.util.ArrayList;

public interface IConnection {
	
	public void listenMessages(DatagramSocket socket);

	public void listenConfirmations(DatagramSocket socketMessage, DatagramSocket socketConfirmation);

	public void listenSuscriptions(DatagramSocket socket, ArrayList<ReceptorData> receptors);
	
}
