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

	public static void abrirInput(String nombre) throws IOException {
		fileinput = new FileInputStream(nombre);
		objectinput = new ObjectInputStream(fileinput);

	}

	public static void abrirOutput(String nombre) throws IOException {
		fileoutput = new FileOutputStream(nombre);
		objectoutput = new ObjectOutputStream(fileoutput);

	}

	public static void cerrarOutput() throws IOException {
		if (objectoutput != null)
			objectoutput.close();
	}

	public static void cerrarInput() throws IOException {
		if (objectinput != null)
			objectinput.close();

	}

	public static void escribir(Serializable serializable) throws IOException {
		if (objectoutput != null)
			objectoutput.writeObject(serializable);
	}

	public static Serializable leer() throws IOException, ClassNotFoundException {
		Serializable serializable = null;
		if (objectinput != null)
			serializable = (Serializable) objectinput.readObject();
		return serializable;
	}
	
	public static Location getLocationFromBin(String fileName) throws Exception{
		Location loc = null;	
		
		Persistence.abrirInput(fileName);
		loc = (Location) Persistence.leer();
		Persistence.cerrarInput();
		
		return loc;
	}
	
	public static void setLocationToBin(String fileName, Serializable obj) throws IOException {
		Persistence.abrirOutput(fileName);
		Persistence.escribir(obj);
		Persistence.cerrarOutput();
	}

}
