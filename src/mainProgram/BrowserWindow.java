package mainProgram;

import javax.swing.JFrame;

public class BrowserWindow {
	private static JFrame frame = new JFrame("Web Browser");
	
	public BrowserWindow(){
		generateWindow();
		
	}
	
	private void generateWindow(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
