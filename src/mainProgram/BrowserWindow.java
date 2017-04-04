package mainProgram;

import java.awt.BorderLayout;
import javax.swing.JEditorPane;
import javax.swing.JFrame;

public class BrowserWindow {
	private static JFrame frame = new JFrame("Web Browser");
	JEditorPane jep = new JEditorPane();
	
	public BrowserWindow(){
		generateEditorPane();
		generateWindow();
		
	}
	
	private void generateWindow(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.pack();
		frame.setVisible(true);
	}
	private void generateEditorPane(){
		jep.setSize(400,400);
		frame.add(jep,BorderLayout.CENTER);
	}


}
