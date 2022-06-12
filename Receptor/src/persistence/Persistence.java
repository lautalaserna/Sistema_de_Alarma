package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;

import connection.ServerData;
import model.Filter;

public class Persistence {
	private final static String FILTER_PATH = "data/filter.bin";
	private final static String SERVER_DATA_PATH = "data/server.bin";
	private static FileOutputStream fileoutput;
	private static FileInputStream fileinput;
	private static ObjectOutputStream objectoutput;
	private static ObjectInputStream objectinput;

	private static void openInput(String path) throws IOException {
		fileinput = new FileInputStream(path);
		objectinput = new ObjectInputStream(fileinput);

	}

	private static void openOutput(String path) throws IOException {
		fileoutput = new FileOutputStream(path);
		objectoutput = new ObjectOutputStream(fileoutput);

	}

	private static void closeOutput() throws IOException {
		if (objectoutput != null)
			objectoutput.close();
	}

	private static void closeInput() throws IOException {
		if (objectinput != null)
			objectinput.close();

	}

	private static void write(Serializable serializable) throws IOException {
		if (objectoutput != null)
			objectoutput.writeObject(serializable);
	}

	private static Serializable read() throws IOException, ClassNotFoundException {
		Serializable serializable = null;
		if (objectinput != null)
			serializable = (Serializable) objectinput.readObject();
		return serializable;
	}

	public static Filter getFilterFromBin(String path) {
		Filter filter = null;

		try {
			Persistence.openInput(path);
			filter = (Filter) Persistence.read();
			Persistence.closeInput();
		} catch (Exception e) {
			File f = new File(FILTER_PATH);
			try {
				f.createNewFile();
				filter = new Filter(false, false, false, 4000);
			} catch (IOException e1) {
				e.printStackTrace();
			}
		}

		return filter;
	}

	public static void setFilterToBin(String path, Filter filter) throws Exception {
		Persistence.openOutput(path);
		Persistence.write(filter);
		Persistence.closeOutput();
	}
	
	public static ServerData getServerDataFromBin(String path) {
		ServerData svd = null;

		try {
			Persistence.openInput(path);
			svd = (ServerData) Persistence.read();
			Persistence.closeInput();
		} catch (Exception e) {
			File f = new File(SERVER_DATA_PATH);
			try {
				f.createNewFile();
				System.out.println(InetAddress.getLocalHost().getHostAddress());
				svd = new ServerData();
			} catch (IOException e1) {
				e.printStackTrace();
			}
		}

		return svd;
	}
	
	public static void setServerDataToBin(String path, ServerData svd) throws Exception {
		Persistence.openOutput(path);
		Persistence.write(svd);
		Persistence.closeOutput();
	}

}
