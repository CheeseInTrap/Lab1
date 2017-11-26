package def;
import java.io.File;

import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;

import control.TextControl;
import entity.Text;

public class BridgeWordsTest {

	private static Text manager;

	@Before
	public void setUp() throws Exception {
		File file = new File("demo.txt");
		manager = new Text();
		String text = TextControl.readFile(file);
		text = manager.StringFormat(text);
		text = manager.createDotFormat(text);
	}
	
	@Test
	public void testBridgeWords1() {
		String result=manager.BridgeWords("with", "friends");
		Assert.assertEquals("The bridge word from with to friends is:my",result);
	}

	@Test
	public void testBridgeWords2() {
		String result=manager.BridgeWords("to", "school");
		Assert.assertEquals("The bridge words from to to school are:primary and my",result);
	}
	
	@Test
	public void testBridgeWords3() {
		String result=manager.BridgeWords("your", "school");
		Assert.assertEquals("No your in the graph!",result);
	}
	
	@Test
	public void testBridgeWords4() {
		String result=manager.BridgeWords("to", "church");
		Assert.assertEquals("No church in the graph!",result);
	}
	
	@Test
	public void testBridgeWords5() {
		String result=manager.BridgeWords("your", "church");
		Assert.assertEquals("No your and church in the graph!",result);
	}
	
	@Test
	public void testBridgeWords6() {
		String result=manager.BridgeWords("also", "school");
		Assert.assertEquals("No bridge words from also to school!",result);
	}
	
	@Test
	public void testBridgeWords7() {
		String result=manager.BridgeWords("in", "school");
		Assert.assertEquals("The bridge words from in to school are:primary,my and middle",result);
	}
}
