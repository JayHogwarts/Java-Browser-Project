package mainProgram;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ToolBar {
	JPanel toolbar = new JPanel();
	JTextField addressbar = new JTextField(30);
	JButton homeBut = new JButton("Home");
	JButton historyBut = new JButton("History");
	JButton bookmarkBut = new JButton("Bookmarks");
	GridBagConstraints gbc = new GridBagConstraints();

	private void generateAddressBar() {
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 1;

		toolbar.add(addressbar, gbc);
	}

	private void generateHomeButton() {
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		toolbar.add(homeBut);
	}

	private void generateHistoryButton() {
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 3;
		toolbar.add(historyBut);
	}

	private void generateBookmarkButton() {
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 4;
		toolbar.add(bookmarkBut);
	}

	private void generateToolBar() {
		toolbar.setLayout(new GridBagLayout());
		generateHomeButton();
		generateAddressBar();
		generateHistoryButton();
		generateBookmarkButton();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.03;
		gbc.weighty = 0.03;
		gbc.gridy = 0;
	}

	public JPanel getToolbar() {
		generateToolBar();
		return toolbar;
	}
}
