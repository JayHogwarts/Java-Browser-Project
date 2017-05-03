/**
 * @author Jay Howarth - B160397129
 * Description: The purpose of this class is to assemble the main window for 
 * the web browser and bring all of the GUI components together in one frame.
 */

package mainProgram;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Stack;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class BrowserWindow {
	JFrame frame = new JFrame("Web Browser");
	JEditorPane jep = new JEditorPane();
	GridBagConstraints gbc = new GridBagConstraints();

	String url = "https://www.google.co.uk/";
	String home = "";
	Stack<String> tempHistory = new Stack<String>();

	ToolBar tb = new ToolBar(jep);
	Config config = new Config();
	

	public BrowserWindow() {
		generateFrame();
		generateToolBar();
		generateEditorPane();
		goHome();
		frame.setVisible(true);

		tb.getBackBut().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = tempHistory.pop();
				tempHistory.push(url);
				setUrl(s);
			}
		});

		tb.getForBut().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

	}

	// Creates the frame with its default settings (does not make the frame
	// visible).
	private void generateFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		frame.setSize(new Dimension(900, 600));
		frame.setMinimumSize(new Dimension(900, 600));

	}

	// Creates the scroll pane and puts the editor pane inside it, then adds it
	// to the frame.
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

	public void setUrl(String url) {
		this.url = url;
		refresh();
	}

	public String getUrl() {
		return this.url;
	}

	public void loadUrl() {
		url = tb.getAddressText();
		tempHistory.push(url);
		try {
			jep.setPage(url);
		} catch (IOException e) {
			System.err.println("URL error with the following URL:" + url);
		}
	}

	// Reloads the current URL
	public void refresh() {
		try {
			jep.setPage(url);
		} catch (IOException e) {
			System.err.println("URL error with the following URL:" + url);
		}
	}

	// Returns the user to their home URL by getting the URL from the config
	// file
	public void goHome() {
		home = config.getHome();
		tempHistory.push(home);
		try {
			jep.setPage(home);
		} catch (IOException e) {
			System.err.println("URL error with the following URL:" + home);
		}
		setUrl(home);
		tb.setAddressText(home);
	}

	// Adds the toolbar from the ToolBar class to the frame.
	private void generateToolBar() {
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		frame.add(tb.getToolbar());
	}
}
