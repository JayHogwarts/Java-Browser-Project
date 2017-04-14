package mainProgram;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class BrowserWindow {
	 static JFrame frame = new JFrame("Web Browser");
	 JEditorPane jep = new JEditorPane();
	String url = "https://www.google.co.uk/";

	public BrowserWindow() {
		generateWindow();
	}

	private void generateWindow() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.pack();
		frame.setVisible(true);
	}
	
	private void generateEditorPane() {
		JScrollPane editorScrollPane = new JScrollPane(jep);
		editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setPreferredSize(new Dimension(500, 500));
		editorScrollPane.setMinimumSize(new Dimension(100, 100));
		jep.setPreferredSize(new Dimension(500, 500));
		jep.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		jep.setEditable(false);
		frame.add(editorScrollPane);
	}

	public void setURL(String url) {
		this.url = url;
	}

	public String getURL() {
		return this.url;
	}

	public void refresh() {
		try {
			jep.setPage(url);
		} catch (IOException e) {
			System.err.println("URL error with the following URL:" + url);
		}
	}
	
}
