package persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PersistenceXML {
	private static XMLEncoder xmlEncoder;
	private static XMLDecoder xmlDecoder;
	private static FileOutputStream fileoutput;
	private static FileInputStream fileinput;

	public static void openInput(String name) throws IOException {
		fileinput = new FileInputStream(name);
		xmlDecoder = new XMLDecoder(fileinput);
	}

	public static void openOutput(String name) throws IOException {
		fileoutput = new FileOutputStream(name);
		xmlEncoder = new XMLEncoder(fileoutput);
	}

	public static void closeInput() throws IOException {
		xmlDecoder.close();
	}
	
	public static void closeOutput() throws IOException {
		xmlEncoder.close();
	}

	public static void write(Object object) throws IOException {
		xmlEncoder.writeObject(object);

	}

	public static Object read() throws IOException, ClassNotFoundException {
		Object object = null;
		if (xmlDecoder != null)
			object = xmlDecoder.readObject();

		return object;
	}
}
