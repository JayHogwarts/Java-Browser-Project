package mainProgram;

import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.text.Document;

public class HTMLViewer extends JEditorPane {
	private static final long serialVersionUID = 1L;
	String url;
	
	public void setPageUrl(String url) throws IOException{
		this.url = url;
		setPage(url);
	}
	
	public void refreshPage(String url) {
		Document doc = getDocument();
		doc.putProperty(Document.StreamDescriptionProperty, null);
		try {
			setPageUrl(url);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "The follwing URL is invalid:" + url);
		}
	}

}
