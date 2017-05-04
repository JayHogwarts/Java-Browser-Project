package mainProgram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Bookmarks {

	File bookmarksFile = new File("BrowserFiles/bookmarks.txt");
	Stack<String> bookmarks = new Stack<String>();

	// Uses a BufferedReader to get the first line of text in the bookmarks file
	public Stack<String> readBookmarks() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(bookmarksFile));
			String txt;
			while ((txt = reader.readLine()) != null) {
				bookmarks.push(txt);
			}
			reader.close();
		} catch (IOException e) {
			File bookmarksFile = new File("BrowserFiles/bookmarks.txt");
		}

		return bookmarks;
	}

	// Writes a new config file with the new home
	public void writeBookmarks(String h) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(bookmarksFile, true));
			writer.write(h);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			File bookmarksFile = new File("BrowserFiles/bookmarks.txt");
		}

	}

}
