/**
 * @author Jay Howarth - B160397129
 * Description:
 * 
 */

package mainProgram;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	JFrame frame = new JFrame("Settings");
	JTabbedPane mainPane = new JTabbedPane();
	JPanel homeView = new JPanel();
	JTextArea bookmarksView = new JTextArea();
	JTextArea historyView = new JTextArea();
	JTextField newHome = new JTextField(30);
	JButton addNewHome = new JButton("Update");
	JLabel homeLabel = new JLabel("Please enter a new home page.");
	JButton clearHistory = new JButton("Clear History");
	JButton clearBookmarks = new JButton("Clear Bookmarks");
	JScrollPane bsp = new JScrollPane(bookmarksView);
	JScrollPane hsp = new JScrollPane(historyView);
	JPanel historyTab = new JPanel();
	JPanel bookmarksTab = new JPanel();
	GridBagConstraints gbc = new GridBagConstraints();
	History history = new History();
	Bookmarks bookmarks = new Bookmarks();
	Config config = new Config();
	Stack<String> historyList = new Stack<String>();
	Stack<String> bookmarksList = new Stack<String>();

	SettingsWindow() {
		generateFrame();
		generateMainPanel();
		frame.setVisible(true);

		clearHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				history.clearHistory();
				historyView.setText(null);
			}
		});
		
		addNewHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				config.writeHome(newHome.getText());
			}
		});
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
		generateBookmarkView();
		mainPane.addTab("Change Home Page", homeView);
		mainPane.addTab("History", historyTab);
		mainPane.addTab("Bookmarks", bookmarksTab);
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
		historyTab.setLayout(new GridBagLayout());
		historyView.setEditable(false);
		historyList = history.readHistory();
		while (historyList.size() != 0) {
			historyView.append(String.format("%s\n", historyList.pop()));
		}
		hsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0.01;
		gbc.gridy = 0;
		historyTab.add(clearHistory, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridy = 1;
		historyTab.add(hsp, gbc);
	}

	private void generateBookmarkView() {
		bookmarksTab.setLayout(new GridBagLayout());
		bookmarksView.setEditable(false);
		bookmarksList = bookmarks.readBookmarks();
		while (bookmarksList.size() != 0) {
			bookmarksView.append(String.format("%s\n", bookmarksList.pop()));
		}
		bsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0.01;
		gbc.gridy = 0;
		bookmarksTab.add(clearBookmarks, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridy = 1;
		bookmarksTab.add(bsp, gbc);
	}

}
