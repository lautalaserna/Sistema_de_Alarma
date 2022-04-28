package persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import model.Location;

public class Persistence {
	private static FileOutputStream fileoutput;
	private static FileInputStream fileinput;
	private static ObjectOutputStream objectoutput;
	private static ObjectInputStream objectinput;

	private static void openInput(String name) throws IOException {
		fileinput = new FileInputStream(name);
		objectinput = new ObjectInputStream(fileinput);

	}

	private static void openOutput(String name) throws IOException {
		fileoutput = new FileOutputStream(name);
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

	public static Location getLocationFromBin(String fileName) throws Exception {
		Location loc = null;

		Persistence.openInput(fileName);
		loc = (Location) Persistence.read();
		Persistence.closeInput();

		return loc;
	}

	public static void setLocationToBin(String fileName, Serializable obj) throws IOException {
		Persistence.openOutput(fileName);
		Persistence.write(obj);
		Persistence.closeOutput();
	}

}
