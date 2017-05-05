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
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SettingsWindow {
	JFrame frame = new JFrame("Settings");
	JTabbedPane mainPane = new JTabbedPane();
	JPanel homeView = new JPanel();
	DefaultListModel<String> model = new DefaultListModel<>();
	JList<String> bookmarksView = new JList<>(model);
	JTextArea historyView = new JTextArea();
	JTextField newHome = new JTextField(30);
	JButton addNewHome = new JButton("Update");
	JLabel homeLabel = new JLabel("Please enter a new home page.");
	JButton clearHistory = new JButton("Clear History");
	JButton deleteBookmark = new JButton("Delete Bookmark");
	JScrollPane bsp = new JScrollPane(bookmarksView);
	JScrollPane hsp = new JScrollPane(historyView);
	JPanel historyTab = new JPanel();
	JPanel bookmarksTab = new JPanel();
	GridBagConstraints gbc = new GridBagConstraints();
	History history = new History();
	Bookmarks bookmarks = new Bookmarks();
	Config config = new Config();
	Stack<String> historyList = new Stack<String>();

	SettingsWindow() {
		generateFrame();
		generateMainPanel();
		frame.setVisible(true);
		// Simply clears the history and makes the text area empty.
		clearHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				history.clearHistory();
				historyView.setText(null);
			}
		});
		// Checks to make sure the user has a bookmark selected. if they do then
		// it will get the index and use that to delete the bookmark.
		deleteBookmark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = bookmarksView.getSelectedIndex();

				if (index == -1) {
					JOptionPane.showMessageDialog(null, "You have not selected a bookmark.");
					return;
				}
				bookmarks.deleteBookmark(index);

				generateBookmarkView();
			}
		});
		// Takes the text from the textField and sets it to be the new default
		// home.
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
		// Will keep looping until all of the elements in the stack have been
		// moved onto the TextField.
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
		// Clears the JList so when the method is called again it doesnt duplicate elements.
		model.removeAllElements();
		ArrayList<String> bookmarksList = new ArrayList<String>();
		bookmarksTab.setLayout(new GridBagLayout());
		bookmarksList = bookmarks.readBookmarks();
		// Loops through the arraylist and adds each element to the JList
		for (int i = 0; i < bookmarksList.size(); i++) {
			model.addElement(bookmarksList.get(i));
		}
		bsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0.01;
		gbc.gridy = 0;
		bookmarksTab.add(deleteBookmark, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridy = 1;
		bookmarksTab.add(bsp, gbc);
	}

}
