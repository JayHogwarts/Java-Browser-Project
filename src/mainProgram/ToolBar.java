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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;

public class ToolBar {
	JPanel toolbar = new JPanel();
	JTextField addressbar = new JTextField(50);
	JButton homeBut = new JButton("Home");
	JButton settingsBut = new JButton("Settings");
	JButton goBut = new JButton("Go");
	JButton bookBut = new JButton("Bookmark");
	JButton backBut = new JButton("<");
	JButton forBut = new JButton(">");
	JButton refreshBut = new JButton("Refresh");
	GridBagConstraints gbc = new GridBagConstraints();
	HTMLViewer htmlViewer;
	String url;
	LinkedList<String> tempHistory = new LinkedList<String>();
	ListIterator<String> iterator = tempHistory.listIterator();
	History history = new History();
	Bookmarks bookmarks = new Bookmarks();
	Config config = new Config();

	ToolBar(HTMLViewer jep) {
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

		refreshBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				htmlViewer.refreshPage(url);
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

		bookBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookmarks.writeBookmarks(url);
			}
		});

		settingsBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsWindow sw = new SettingsWindow();
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

		htmlViewer.addHyperlinkListener(new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					url = e.getURL().toString();
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
			}
		});

	}

	private void setPageUrl() throws IOException {
		setAddressText(url);
		htmlViewer.setPage(url);
	}

	private void generateToolBar() {
		toolbar.setLayout(new GridBagLayout());
		toolbar.add(homeBut);
		toolbar.add(refreshBut);
		toolbar.add(backBut);
		toolbar.add(forBut);
		addressbar.setMinimumSize(new Dimension(400, 20));
		toolbar.add(addressbar);
		toolbar.add(goBut);
		toolbar.add(bookBut);
		toolbar.add(settingsBut);

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
