package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Message;

public class Connection {
	private Filter filter;
	
	public void listen(int port) {
		new Thread() {
			public void run() {
				try {
					ServerSocket ss = new ServerSocket(port);
					while (true) {
                        Socket socket = ss.accept();
                        InputStream inputStream = socket.getInputStream();
                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                        Message m = (Message) objectInputStream.readObject();
                        
                        // Chekear filtro
                        
                        System.out.println("Evento: " + m.getEvent().getEventType());
					}
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}
		}.run();
	}
	
}
