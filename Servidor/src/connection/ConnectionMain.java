package connection;

import java.net.DatagramSocket;
import java.util.ArrayList;

public class ConnectionMain implements IConnection{

	@Override
	public void listenMessages(DatagramSocket socket) {
		
	}
	
	@Override
	public void listenConfirmations(DatagramSocket socketMessage, DatagramSocket socketConfirmation) {
		
	}
	
	@Override
	public void listenSuscriptions(DatagramSocket socket, ArrayList<ReceptorData> receptors) {
		
	}
	
}
