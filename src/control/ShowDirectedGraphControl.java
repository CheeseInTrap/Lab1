package control;



import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import entity.GraphViz;

public class ShowDirectedGraphControl {
  /**
   * չʾ·��ͼƬ.
   * @param dotFormat ��ʾָ��������·���ĸ�ʽ
   * @param fileName ��ʾ�ļ�������
   */
  public static void showDirectedGraph(final String dotFormat, final String fileName) {
    GraphViz gviz = new GraphViz();
    gviz.clearGraph();
    gviz.addln(gviz.start_graph());
    gviz.add(dotFormat);
    gviz.addln(gviz.end_graph());
    // String type = "gif";
    String type = "jpg";
    // gv.increaseDpi();
    //gv.decreaseDpi();
    gviz.decreaseDpi();
    File out = new File(fileName + "." + type);
    gviz.writeGraphToFile(gviz.getGraph(gviz.getDotSource(), type), out);
  }
  /**
   * ��java GUI��չʾͼƬ.
   * @param path ��ʾ·��
   * @throws IOException �׳�����
   */

  public static void showImage(final String path) throws IOException {
    final File file = new File(path);
    final JLabel image = new JLabel(new ImageIcon(ImageIO.read(file)));
    final JPanel mainPanel = new JPanel(new BorderLayout());

    final JScrollPane scrollPane = new JScrollPane(image);
    mainPanel.add(scrollPane);
    final JFrame frame = new JFrame();
    frame.setSize(400,500);
    frame.add(mainPanel);
    frame.setVisible(true);
  }

}
