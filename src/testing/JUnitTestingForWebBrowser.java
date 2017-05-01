package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import mainProgram.Config;
import mainProgram.SettingsWindow;

public class JUnitTestingForWebBrowser {

	@Test
	public void testConfig() {
		Config c = new Config();
		c.writeHome("https://www.google.co.uk/");
		assertEquals(c.readHome(),c.getHome());
	}
	
	@Test
	public void testSettings(){
		SettingsWindow s = new SettingsWindow();
		s.openWindow();
	}

}
