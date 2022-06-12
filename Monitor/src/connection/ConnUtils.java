package connection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

public class ConnUtils {
	public static final String PATH = "data/dirMonitor.txt";

	public static int[] readPorts(String filename) {
		File file = new File(filename);
		int[] res = null;
		
		try(Scanner sc = new Scanner(file)){
	       int portPri = sc.nextInt();
	       int portSec = sc.nextInt();
	       int portSync = sc.nextInt();
	       int portChange = sc.nextInt();
	       res = new int[]{portPri,portSec,portSync,portChange};
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static String[] readIPs(String filename) {
		File file = new File(filename);
		String[] res = null;
		
		try(Scanner sc = new Scanner(file)){
	       sc.nextInt();
	       sc.nextInt();
	       sc.nextInt();
	       sc.nextInt();
	       String priIP = sc.nextLine();
	       String auxIP = sc.nextLine();
	       res = new String[]{priIP,auxIP};
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
	
	public static DatagramPacket buildPetition() {
		byte[] buffer = new byte[2048];
		return new DatagramPacket(buffer, buffer.length);
	}
	
	public static Object openPetition(DatagramPacket petition) {
		Object obj = null;
		try {
			ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(petition.getData()));
			obj = iStream.readObject();
			iStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return obj;
	}
}
