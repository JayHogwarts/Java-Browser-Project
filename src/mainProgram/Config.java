package mainProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Config {
	File config = new File("c:\\config.txt");

	public void createFile() {
		try {
			FileReader reader = new FileReader(config);
		} catch (FileNotFoundException e) {
			File config = new File("c:\\config.txt");

		}

	}

}
