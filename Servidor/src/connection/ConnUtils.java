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
	public static final String PATH_PRIMARIO = "data/dirSvPrimario.txt";
	public static final String PATH_SECUNDARIO = "data/dirSvSecundario.txt";

	public static int[] readPorts(String filename) {
		File file = new File(filename);
		int[] res = null;
		
		if(filename.equals(PATH_PRIMARIO)) {
			try(Scanner sc = new Scanner(file)){
			       int portHeartbeat = sc.nextInt();
			       int portE = sc.nextInt();
			       int portR = sc.nextInt();
			       int portC = sc.nextInt();
			       int portM = sc.nextInt();
			       int portSecundarioL = sc.nextInt();
			       int portSecundarioR = sc.nextInt();
			       res = new int[]{portHeartbeat,portE,portR,portC,portM,portSecundarioL,portSecundarioR};
				}
				catch(Exception e){
					e.printStackTrace();
				}
		}
		else if(filename.equals(PATH_SECUNDARIO)){
			try(Scanner sc = new Scanner(file)){
			       int portHeartbeat = sc.nextInt();
			       int portL = sc.nextInt();
			       int portS = sc.nextInt();
			       int portM = sc.nextInt();
			       res = new int[]{portHeartbeat,portL,portS,portM};
				}
				catch(Exception e){
					e.printStackTrace();
				}
		}
		return res;
	}
	
	public static String[] readIPs(String filename) {
		File file = new File(filename);
		String[] res = null;
		
		if(filename.equals(PATH_PRIMARIO)) {
			try(Scanner sc = new Scanner(file)){
			       sc.nextInt();
			       sc.nextInt();
			       sc.nextInt();
			       sc.nextInt();
			       sc.nextInt();
			       sc.nextInt();
			       sc.nextInt();
			       String monitorIP = sc.nextLine();
			       String secundarioIP = sc.nextLine();
			       res = new String[]{monitorIP,secundarioIP};
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(filename.equals(PATH_SECUNDARIO)){
			try(Scanner sc = new Scanner(file)){
			       sc.nextInt();
			       sc.nextInt();
			       sc.nextInt();
			       sc.nextInt();
			       String monitorIP = sc.nextLine();
			       res = new String[]{monitorIP};
				}
				catch(Exception e){
					e.printStackTrace();
				}
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
