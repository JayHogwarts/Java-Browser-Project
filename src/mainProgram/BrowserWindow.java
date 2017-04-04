package mainProgram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class BrowserWindow {
	private static JFrame frame = new JFrame("Web Browser");
	JEditorPane jep = new JEditorPane();
	JButton back = new JButton("<");
	JButton forward = new JButton(">");
	JTextField addressBar = new JTextField();
	
	public BrowserWindow(){
		generateButtons();
		generateTextField();
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
	private void generateButtons(){
		back.setBackground(new Color(33,123,175));
		forward.setBackground(new Color(33,123,175));
		frame.add(back,BorderLayout.PAGE_START);
		frame.add(forward,BorderLayout.PAGE_START);
	}
	private void generateTextField(){
		//addressBar.setSize(20,200);
		frame.add(addressBar,BorderLayout.PAGE_START);
	}
}
