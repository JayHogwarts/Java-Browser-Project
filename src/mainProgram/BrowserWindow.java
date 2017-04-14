package mainProgram;

import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JFrame;

public class BrowserWindow {
	 static JFrame frame = new JFrame("Web Browser");


	public BrowserWindow() {
		generateWindow();
	}

	private void generateWindow() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void addToFrame(Component o){
		frame.add(o);
	}
}
