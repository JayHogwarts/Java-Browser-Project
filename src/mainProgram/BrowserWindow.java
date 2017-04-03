package mainProgram;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

public class BrowserWindow {
	private static JFrame frame = new JFrame("Web Browser");
	JEditorPane jep = new JEditorPane();
	
	public BrowserWindow(){
		generateWindow();
		
	}
	
	private void generateWindow(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jep.setSize(400,400);
		frame.add(jep);
		frame.pack();
		frame.setVisible(true);
	}
}
