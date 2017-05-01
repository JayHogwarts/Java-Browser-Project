/**
 * @author Jay Howarth - B160397129
 * Description:The purpose of this class it to assemble all of the 
 * GUI components into a toolbar; ready to add to the BrowserWindow class.
 */

package mainProgram;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ToolBar{
	JPanel toolbar = new JPanel();
	JTextField addressbar = new JTextField(50);
	JButton homeBut = new JButton("Home");
	JButton settingsBut = new JButton("Settings");
	JButton goBut = new JButton("Go");
	JButton backBut = new JButton("<");
	JButton forBut = new JButton(">");
	GridBagConstraints gbc = new GridBagConstraints();

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
	
	public String getAddressText(){
		return addressbar.getText();
	}

	public JTextField getAddressbar() {
		return addressbar;
	}

	public void setAddressbar(JTextField addressbar) {
		this.addressbar = addressbar;
	}

	public JButton getHomeBut() {
		return homeBut;
	}

	public void setHomeBut(JButton homeBut) {
		this.homeBut = homeBut;
	}

	public JButton getSettingsBut() {
		return settingsBut;
	}

	public void setSettingsBut(JButton settingsBut) {
		this.settingsBut = settingsBut;
	}

	public JButton getGoBut() {
		return goBut;
	}

	public void setGoBut(JButton goBut) {
		this.goBut = goBut;
	}

	public JButton getBackBut() {
		return backBut;
	}

	public void setBackBut(JButton backBut) {
		this.backBut = backBut;
	}

	public JButton getForBut() {
		return forBut;
	}

	public void setForBut(JButton forBut) {
		this.forBut = forBut;
	}

	public GridBagConstraints getGbc() {
		return gbc;
	}

	public void setGbc(GridBagConstraints gbc) {
		this.gbc = gbc;
	}
	
	
}
