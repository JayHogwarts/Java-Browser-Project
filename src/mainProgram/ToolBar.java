package mainProgram;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ToolBar {
	JPanel toolbar = new JPanel();
	JTextField addressbar = new JTextField();
	JButton homeBut = new JButton("Home");
	JButton settingsBut = new JButton("Settings");
	JButton goBut = new JButton("Go");
	JButton backBut = new JButton("<");
	JButton forBut = new JButton(">");
	GridBagConstraints gbc = new GridBagConstraints();

	private void generateAddressBar() {
		addressbar.setMaximumSize(new Dimension(10,100));
		addressbar.setMinimumSize(new Dimension(10,100));
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 2;

		toolbar.add(addressbar, gbc);
	}
	private void generateNavigationButtons() {
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 1;

		toolbar.add(forBut, gbc);
		toolbar.add(backBut, gbc);
	}
	private void generateGoButton() {
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 3;

		toolbar.add(goBut, gbc);
	}

	private void generateHomeButton() {
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		toolbar.add(homeBut);
	}

	private void generateSettingsButton() {
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 4;
		toolbar.add(settingsBut);
	}

	private void generateToolBar() {
		toolbar.setLayout(new GridBagLayout());
		generateHomeButton();
		generateAddressBar();
		generateSettingsButton();
		generateNavigationButtons();
		generateGoButton();
	}

	public JPanel getToolbar() {
		generateToolBar();
		return toolbar;
	}
}
