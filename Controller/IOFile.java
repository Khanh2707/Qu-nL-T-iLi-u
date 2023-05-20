package Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
	public static <T> List<T> read(String fname) {
		List<T> list = new ArrayList<>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fname));
			list = (List<T>)ois.readObject();
			ois.close();
		} catch(IOException e) {
			System.err.println(e);
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}
		return list;
	}
	
	public static <T> void write(String fname, List<T> arr) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fname));
			oos.writeObject(arr);
			oos.close();
		} catch(IOException e) {
			System.err.println(e);
		}
	}
}
