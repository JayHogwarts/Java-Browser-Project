package mainProgram;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class BrowserWindow {
	JFrame frame = new JFrame("Web Browser");
	JEditorPane jep = new JEditorPane();
	String url = "https://www.google.co.uk/";
	String home = "https://www.google.co.uk/";
	GridBagConstraints gbc = new GridBagConstraints();
	ToolBar tb = new ToolBar();

	public BrowserWindow() {
		generateWindow();
		generateEditorPane();
		frame.add(tb.getToolbar());
		goHome();
		frame.setVisible(true);
	}

	private void generateWindow() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		frame.setSize(new Dimension(900, 600));
		frame.setMinimumSize(new Dimension(900, 600));

	}

	private void generateEditorPane() {
		JScrollPane editorScrollPane = new JScrollPane(jep);
		editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jep.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		jep.setEditable(false);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridy = 1;

		frame.add(editorScrollPane, gbc);
	}

	public void setURL(String url) {
		this.url = url;
	}

	public String getURL() {
		return this.url;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	// Reloads the current URL
	public void refresh() {
		try {
			jep.setPage(url);
		} catch (IOException e) {
			System.err.println("URL error with the following URL:" + url);
		}
	}

	// Returns the user to their home URL
	public void goHome() {
		try {
			jep.setPage(home);
		} catch (IOException e) {
			System.err.println("URL error with the following URL:" + url);
		}
	}
	
	

}
