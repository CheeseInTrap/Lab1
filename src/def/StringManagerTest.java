package def;
import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.Text;
import control.*;

public class StringManagerTest {

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
	public void testGenerateNewText() {
		String result = manager.generateNewText("school");
		Assert.assertEquals("school", result);
		
		result = manager.generateNewText("i like school");
		Assert.assertEquals("i like school",result );
		
		result = manager.generateNewText("i see my shoes");
		Assert.assertEquals("i can see my shoes",result );
		
		result = manager.generateNewText("");
		Assert.assertEquals("", "");
	}
}
