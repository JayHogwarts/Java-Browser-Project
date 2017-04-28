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

public class SettingsWindow {
	JFrame frame = new JFrame("Web Browser");
	GridBagConstraints gbc = new GridBagConstraints();
	
	SettingsWindow(){
		
	}
	
	private void generateFrame(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		frame.setSize(new Dimension(600, 300));
		frame.setMinimumSize(new Dimension(600, 300));
	}

}
