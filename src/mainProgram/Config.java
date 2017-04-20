package mainProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	File config = new File("c:\\config.txt");
	String home = "";

	public void createFile() {
		try {
			FileReader reader = new FileReader(config);
		} catch (FileNotFoundException e) {
			File config = new File("c:\\config.txt");
		}
	}

	public String readHome() {

		return home;
	}

	public void writeHome(String h) {
		try {
			FileWriter writer = new FileWriter(config, true);
			writer.write(h);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
