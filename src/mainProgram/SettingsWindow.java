/**
 * @author Jay Howarth - B160397129
 * Description:
 * 
 */

package mainProgram;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SettingsWindow {
	JFrame frame = new JFrame("Web Browser");
	JTabbedPane mainPane = new JTabbedPane();
	JPanel homeView = new JPanel();
	JTextArea bookmarksView = new JTextArea();
	JTextArea historyView = new JTextArea();
	JScrollPane bsp = new JScrollPane(bookmarksView);
	JScrollPane hsp = new JScrollPane(historyView);
	JTextField newHome = new JTextField(30);
	JButton addNewHome = new JButton("Update");
	JLabel homeLabel = new JLabel("Please enter a new home page.");
	GridBagConstraints gbc = new GridBagConstraints();
	History history = new History();
	Stack<String> historyList = new Stack<String>();
	
	public void openWindow() {
		generateFrame();
		generateMainPanel();
		frame.setVisible(true);
	}

	private void generateFrame() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		frame.setSize(new Dimension(400, 600));
		frame.setMinimumSize(new Dimension(400, 600));
	}

	private void generateMainPanel() {
		generateHomeView();
		generateHistoryView();
		mainPane.addTab("Change Home Page", homeView);
		mainPane.addTab("History", hsp);
		mainPane.addTab("Bookmarks", bsp);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		frame.add(mainPane, gbc);
	}

	private void generateHomeView() {
		homeView.add(homeLabel);
		homeView.add(newHome);
		homeView.add(addNewHome);
	}
	
	private void generateHistoryView() {
		historyList = history.readHistory();
		for(int i = 0; i < historyList.size(); i++){
			historyView.setText(historyList.pop());
		}
		hsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		hsp.add(historyView);
	}

	private void generateBookmarkView() {
		
	}

}
