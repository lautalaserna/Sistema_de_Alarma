package connection;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import model.Message;

public class Connection {
	
	public void connect(String id, int port, Message msg) {
		new Thread() {
			public void run() {
				try {
					Socket socket = new Socket(id, port);
					OutputStream outputStream = socket.getOutputStream();
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

					objectOutputStream.writeObject(msg);

					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.run();
	}
}
