package persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import connection.Filter;

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

	public static Filter getFilterFromBin(String fileName) throws Exception {
		Filter f = null;		
		
		Persistence.abrirInput(fileName);
		f = (Filter) Persistence.leer();
		Persistence.cerrarInput();
		
		return f;	
	}
	
	

}
