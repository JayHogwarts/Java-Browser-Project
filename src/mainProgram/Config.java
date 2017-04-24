package mainProgram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	File config = new File("BrowserFiles/config.txt");
	String home = "";

	public String readHome() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(config));
			String txt;
			if ((txt = reader.readLine()) != null) {
				this.home = txt;
			}
			reader.close();
		} catch (IOException e) {
			File config = new File("BrowserFiles/config.txt");
		}

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

		this.home = h;

	}

	public String getHome() {
		return home;
	}

}
