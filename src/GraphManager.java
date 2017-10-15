<<<<<<< HEAD
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
public class GraphManager {
	

	
	public static void showDirectedGraph(String dotFormat,String fileName)
	{
	    GraphViz gv=new GraphViz();
	    gv.clearGraph();
	    gv.addln(gv.start_graph());
	    gv.add(dotFormat);
	    gv.addln(gv.end_graph());
	   // String type = "gif";
	    String type = "jpg";
	  // gv.increaseDpi();
	    //gv.decreaseDpi();
	    gv.decreaseDpi();
	    File out = new File(fileName+"."+ type); 
	    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	    
	}

	
	public static void showImage(String path) throws IOException {
		File f = new File(path);
		JLabel image = new JLabel(new ImageIcon(ImageIO.read(f)));
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		
		JScrollPane scrollPane = new JScrollPane(image);
		mainPanel.add(scrollPane);
		JFrame frame = new JFrame();
		frame.setSize(400,500);
		frame.add(mainPanel);
		frame.setVisible(true);
	}
	
}
=======
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
public class GraphManager {
	

	//ÏÔÊ¾Â·¾¶
	public static void showDirectedGraph(String dotFormat,String fileName)
	{
	    GraphViz gv=new GraphViz();
	    gv.clearGraph();
	    gv.addln(gv.start_graph());
	    gv.add(dotFormat);
	    gv.addln(gv.end_graph());
	   // String type = "gif";
	    String type = "jpg";
	  // gv.increaseDpi();
	    //gv.decreaseDpi();
	    gv.decreaseDpi();
	    File out = new File(fileName+"."+ type); 
	    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	    
	}

	//ÏÔÊ¾Í¼Æ¬
	public static void showImage(String path) throws IOException {
		File f = new File(path);
		JLabel image = new JLabel(new ImageIcon(ImageIO.read(f)));
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		
		JScrollPane scrollPane = new JScrollPane(image);
		mainPanel.add(scrollPane);
		JFrame frame = new JFrame();
		frame.setSize(400,500);
		frame.add(mainPanel);
		frame.setVisible(true);
	}
	
}
>>>>>>> dd819bf506d8c0223765ad40987d7f4aa1827448
