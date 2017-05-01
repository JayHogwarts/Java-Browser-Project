/**
 * @author Jay Howarth - B160397129
 * Description:
 * 
 */

package mainProgram;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class SettingsWindow {
	JFrame frame = new JFrame("Web Browser");
	JTabbedPane mainPane = new JTabbedPane();
	JPanel homeMenu = new JPanel();
	JScrollPane bookmarksMenu = new JScrollPane();
	JScrollPane historyMenu = new JScrollPane();
	GridBagConstraints gbc = new GridBagConstraints();

	public void openWindow() {
		generateFrame();
		generateMainPanel();
		frame.setVisible(true);
	}

	private void generateFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		frame.setSize(new Dimension(600, 300));
		frame.setMinimumSize(new Dimension(600, 300));
	}

	private void generateMainPanel() {
		mainPane.addTab("Change Home Page", homeMenu);
		mainPane.addTab("Bookmarks", bookmarksMenu);
		mainPane.addTab("History", historyMenu);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;

		frame.add(mainPane);
	}

	private void generateHistoryView() {

	}

	private void generateBookmarkView() {

	}

}
