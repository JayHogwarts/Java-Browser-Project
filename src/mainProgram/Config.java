/**
 * @author Jay Howarth - B160397129
 * Description:
 * 
 */

package mainProgram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	File config = new File("BrowserFiles/config.txt");
	String home;

	// Uses a BufferedReader to return the first line of text in the config file
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

	// Writes a new config file with the new home
	public void writeHome(String h) {
		try {
			// The file is deleted to prevent multiple home URLs being stored in
			// one config file
			config.delete();
			FileWriter writer = new FileWriter(config, true);
			writer.write(h);
			writer.close();
		} catch (IOException e) {
			File config = new File("BrowserFiles/config.txt");
		}

		this.home = h;

	}

}
