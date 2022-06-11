package connection;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

public class ConnectionUtils {
	public static final String PATH = "data/dir.txt";

	public static int[] readPorts(String filename) {
		File file = new File(filename);
		int[] res = null;
		
		try{
	       Scanner sc = new Scanner(file);
	       int portE = sc.nextInt();
	       int portR = sc.nextInt();
	       int portC = sc.nextInt();
	       res = new int[]{portE,portR,portC};
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static DatagramPacket buildPetition(Object obj, InetAddress address, int port) {
		byte[] buffer = new byte[2048];
		try {
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			ObjectOutput output;
			output = new ObjectOutputStream(bStream);
			output.writeObject(obj);
			output.close();
			buffer = bStream.toByteArray();
		} catch (IOException e) {
		}
		return new DatagramPacket(buffer, buffer.length,address,port);
	}
}
