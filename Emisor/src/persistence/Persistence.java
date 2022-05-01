package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import model.Location;

public class Persistence {
	private final static String PATH = "data/location.bin";
	private static FileOutputStream fileoutput;
	private static FileInputStream fileinput;
	private static ObjectOutputStream objectoutput;
	private static ObjectInputStream objectinput;

	private static void openInput() throws IOException {
		fileinput = new FileInputStream(PATH);
		objectinput = new ObjectInputStream(fileinput);

	}

	private static void openOutput() throws IOException {
		fileoutput = new FileOutputStream(PATH);
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

	public static Location getLocationFromBin() {
		Location loc = null;

		try {
			Persistence.openInput();
			loc = (Location) Persistence.read();
			Persistence.closeInput();
		} catch (Exception e) {
			File f = new File(PATH);
			try {
				f.createNewFile();
				loc = new Location();
			} catch (IOException e1) {
				e.printStackTrace();
			}
		}

		return loc;
	}

	public static void setLocationToBin(Serializable obj) throws IOException {
		Persistence.openOutput();
		Persistence.write(obj);
		Persistence.closeOutput();
	}

}
