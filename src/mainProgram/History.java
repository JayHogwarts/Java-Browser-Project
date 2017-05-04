package mainProgram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class History {
	File historyFile = new File("BrowserFiles/history.txt");
	Stack<String> history = new Stack<String>();

	// Uses a BufferedReader to get the first line of text in the config file
	public Stack<String> readHistory() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(historyFile));
			String txt;
			while((txt = reader.readLine()) != null) {
				history.push(txt);
			}
			reader.close();
		} catch (IOException e) {
			File historyFile = new File("BrowserFiles/config.txt");
		}

		return history;
	}

	// Writes a new config file with the new home
	public void writeHistory(String h) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(historyFile, true));
			writer.write(h);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			File historyFile = new File("BrowserFiles/config.txt");
		}

	}
}
