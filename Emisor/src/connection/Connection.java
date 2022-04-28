package connection;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import model.Message;

public class Connection {
	
	public static void sendMsg(Message msg) {
		new Thread() {
			public void run() {
				try {
					Socket socket = new Socket(msg.getLoc().getIp(), msg.getLoc().getPort());
					OutputStream outputStream = socket.getOutputStream();
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
					
					objectOutputStream.writeObject(msg);

					// socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.run();
	}
}
