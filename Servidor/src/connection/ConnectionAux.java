package connection;

import java.net.DatagramSocket;
import java.util.ArrayList;

public class ConnectionAux implements IConnection {
	
	@Override
	public void listenMessages(DatagramSocket socket) {
		System.out.println("No hace nada");
	}

	@Override
	public void listenConfirmations(DatagramSocket socketMessage, DatagramSocket socketConfirmation) {
		System.out.println("No hace nada");
	}

	@Override
	public void listenSuscriptions(DatagramSocket socket, ArrayList<ReceptorData> receptors) {
		System.out.println("No hace nada");
	}

}
