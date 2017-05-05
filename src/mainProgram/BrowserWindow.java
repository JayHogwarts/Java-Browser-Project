/**
 * @author Jay Howarth - B160397129
 * Description: The purpose of this class is to assemble the main window for 
 * the web browser and bring all of the GUI components together in one frame.
 */

package mainProgram;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class BrowserWindow {
	JFrame frame = new JFrame("Web Browser");
	HTMLViewer jep = new HTMLViewer();
	GridBagConstraints gbc = new GridBagConstraints();

	public BrowserWindow() {
		generateFrame();
		generateEditorPane();
		generateToolBar();
		frame.setVisible(true);

	}

	// Creates the frame with its default settings (does not make the frame
	// visible).
	private void generateFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		frame.setSize(new Dimension(900, 600));
		frame.setMinimumSize(new Dimension(900, 600));

	}

	// Creates the scroll pane and puts the editor pane inside it, then adds it
	// to the frame.
	private void generateEditorPane() {
		JScrollPane editorScrollPane = new JScrollPane(jep);
		editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jep.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		jep.setEditable(false);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridy = 1;

		frame.add(editorScrollPane, gbc);
	}

	// Adds the toolbar from the ToolBar class to the frame.
	private void generateToolBar() {
		ToolBar tb = new ToolBar(jep);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.gridx = 0;
		frame.add(tb.getToolbar());
	}
}
