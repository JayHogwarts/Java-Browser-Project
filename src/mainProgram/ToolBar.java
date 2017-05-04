/**
 * @author Jay Howarth - B160397129
 * Description:The purpose of this class it to assemble all of the 
 * GUI components into a toolbar; ready to add to the BrowserWindow class. This class contains most 
 * of the action listener functionality as the toolbar is what the user will be using most.
 */

package mainProgram;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ToolBar {
	JPanel toolbar = new JPanel();
	JTextField addressbar = new JTextField(50);
	JButton homeBut = new JButton("Home");
	JButton settingsBut = new JButton("Settings");
	JButton goBut = new JButton("Go");
	JButton backBut = new JButton("<");
	JButton forBut = new JButton(">");
	GridBagConstraints gbc = new GridBagConstraints();
	JEditorPane htmlViewer;

	String url;
	LinkedList<String> tempHistory = new LinkedList<String>();
	ListIterator<String> iterator = tempHistory.listIterator();
	History history = new History();
	Config config = new Config();
	SettingsWindow sw = new SettingsWindow();

	ToolBar(JEditorPane jep) {
		htmlViewer = jep;
		url = config.getHome();
		try {
			setPageUrl();
			history.writeHistory(url);
			iterator.add(url);
			iterator.previous();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"The default home URL is invalid. Please change it using the config file.");
		}
		homeBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				url = config.getHome();
				try {
					setPageUrl();
					history.writeHistory(url);
					if (iterator.hasNext()) {
						iterator.next();
					}
					iterator.add(url);
					iterator.previous();
				} catch (IOException eHome) {
					JOptionPane.showMessageDialog(null,
							"The default home URL is invalid. Please change it using the config file.");
				}
			}
		});

		goBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				url = getAddressText();
				try {
					setPageUrl();
					history.writeHistory(url);
					if (iterator.hasNext()) {
						iterator.next();
					}
					iterator.add(url);
					iterator.previous();

				} catch (IOException eGo) {
					JOptionPane.showMessageDialog(null, "The follwing URL is invalid:" + url);
				}
			}
		});

		settingsBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sw.openWindow();
			}
		});

		backBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (iterator.hasPrevious()) {
					url = iterator.previous();
					try {
						setPageUrl();
					} catch (IOException eGo) {
						JOptionPane.showMessageDialog(null, "The follwing URL is invalid:" + url);
					}
				} else {
					JOptionPane.showMessageDialog(null, "You can't go back any further.");
				}
			}
		});

		forBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iterator.next();
				if (iterator.hasNext()) {
					url = iterator.next();
					try {
						setPageUrl();
					} catch (IOException eGo) {
						JOptionPane.showMessageDialog(null, "The follwing URL is invalid:" + url);
					}
					iterator.previous();
				} else {
					iterator.previous();
					JOptionPane.showMessageDialog(null, "You can't go forward any further.");
				}
			}
		});

	}

	private void setPageUrl() throws IOException {
		setAddressText(url);
		System.out.println(url);
		htmlViewer.setPage(url);

	}

	private void generateAddressBar() {
		addressbar.setMinimumSize(new Dimension(500, 20));
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 3;

		toolbar.add(addressbar, gbc);
	}

	private void generateNavigationButtons() {
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 1;
		gbc.gridx = 1;
		toolbar.add(backBut, gbc);

		gbc.gridx = 2;
		toolbar.add(forBut, gbc);
	}

	private void generateGoButton() {
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 4;

		toolbar.add(goBut, gbc);
	}

	private void generateHomeButton() {

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		toolbar.add(homeBut);
	}

	private void generateSettingsButton() {
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 5;
		toolbar.add(settingsBut);
	}

	private void generateToolBar() {
		toolbar.setLayout(new GridBagLayout());
		generateHomeButton();
		generateNavigationButtons();
		generateAddressBar();
		generateGoButton();
		generateSettingsButton();

	}

	public JPanel getToolbar() {
		generateToolBar();
		return toolbar;
	}

	public void setAddressText(String address) {
		addressbar.setText(address);
	}

	public String getAddressText() {
		return addressbar.getText();
	}

}
