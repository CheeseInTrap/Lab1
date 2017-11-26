package form;

import java.awt.Container;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import entity.Text;

public class RandomWalkForm {
	
	public RandomWalkForm(Text sm)
	{
        final JFrame frameQuery = new JFrame();
        final JTabbedPane paneQuery = new JTabbedPane();
        frameQuery.setContentPane(paneQuery);
        final Container conResult = new Container();
        paneQuery.add("随机游走", conResult);

        final JTextArea textArea = new JTextArea(8,20);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setBounds(80, 10, 180, 170);
        final JScrollPane jscrollp = new JScrollPane(textArea);
        conResult.add(jscrollp);
        conResult.add(textArea);
        frameQuery.setContentPane(paneQuery);
        frameQuery.setVisible(true);
        frameQuery.setSize(300, 400);


        final String result = sm.randomWalk();
        //System.out.println("随机游走的结果是："+result);
        textArea.setText(result);
        try {
          sm.TextToFile(result);
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
	}
}
