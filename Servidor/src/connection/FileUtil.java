package connection;

import java.io.File;
import java.util.Scanner;

public class FileUtil {
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
}
