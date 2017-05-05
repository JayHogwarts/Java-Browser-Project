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
		// This makes sure the first page that loads is the home page.
		url = config.readHome();
		try {
			setPageUrl();
			history.writeHistory(url);
			iterator.add(url);
			iterator.previous();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"The default home URL is invalid. Please change it using the config file.");
		}
		// When Home is pressed it will set it to the current page and load it.
		homeBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				url = config.readHome();
				try {
					setPageUrl();
					history.writeHistory(url);
					// The purpose of having the iterator jump back and forth it
					// to make sure the current page the user is viewing is
					// always one space next to the iterator
					// and the temporary history is always previous to the
					// iterator
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
		// This is called when the user refreshes the page
		refreshBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				htmlViewer.refreshPage(url);
			}
		});
		// This takes text entered into the addressbar and goes to that url,
		// returning an error if the url is invalid
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
					JOptionPane.showMessageDialog(null, "The follwing URL is invalid: " + url);
				}
			}
		});
		// This will bookmark the url the user is currently viewing
		bookBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookmarks.writeBookmarks(url);
			}
		});
		// This will open the settings window.
		settingsBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsWindow sw = new SettingsWindow();
			}
		});
		// This will check if there are any previous urls in the linked list, if
		// so it will go
		// back one url at a time until it reaches the end of the linked list.
		backBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (iterator.hasPrevious()) {
					url = iterator.previous();
					try {
						setPageUrl();
					} catch (IOException eGo) {
						JOptionPane.showMessageDialog(null, "The follwing URL is invalid: " + url);
					}
				} else {
					JOptionPane.showMessageDialog(null, "You can't go back any further.");
				}
			}
		});
		// This will jump over the current url and check if there are any urls
		// after it. It will return an error if the only url next to the
		// iterator is the current url.
		forBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iterator.next();
				if (iterator.hasNext()) {
					url = iterator.next();
					try {
						setPageUrl();
					} catch (IOException eGo) {
						JOptionPane.showMessageDialog(null, "The follwing URL is invalid: " + url);
					}
					iterator.previous();
				} else {
					iterator.previous();
					JOptionPane.showMessageDialog(null, "You can't go forward any further.");
				}
			}
		});
		// Listens for any hyperlink events and will send the user to the
		// hyperlink they pressed.
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

	// This is used to set the page of the HTMLViewer class. It throws the
	// IOException instead of handling it so I can make sure that history is
	// only added when it is a valid URL
	private void setPageUrl() throws IOException {
		setAddressText(url);
		htmlViewer.setPage(url);
	}

	// Adds all the componenets to the toolbar panel
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

	// This generates the toolbar and returns it so it can be used in the main
	// class.
	public JPanel getToolbar() {
		generateToolBar();
		return toolbar;
	}

	// Used to set and get the address text when setting the URL via user input.
	public void setAddressText(String address) {
		addressbar.setText(address);
	}

	public String getAddressText() {
		return addressbar.getText();
	}

}
