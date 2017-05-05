package mainProgram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Bookmarks {

	File bookmarksFile = new File("BrowserFiles/bookmarks.txt");
	ArrayList<String> bookmarks = new ArrayList<String>();

	// Uses a BufferedReader to get the first line of text in the bookmarks file
	public ArrayList<String> readBookmarks() {
		bookmarks.clear();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(bookmarksFile));
			String txt;
			while ((txt = reader.readLine()) != null) {
				bookmarks.add(txt);
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

	public void deleteBookmark(int index) {
		bookmarks.remove(index);
		bookmarksFile.delete();
		File bookmarksFile = new File("BrowserFiles/bookmarks.txt");
		for (int i = 0; i < bookmarks.size(); i++) {
			System.out.print(bookmarks.get(i));
			writeBookmarks(bookmarks.get(i));
		}
	}

}
