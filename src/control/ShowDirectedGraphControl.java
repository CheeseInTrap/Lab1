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
   * 展示路径图片.
   * @param dotFormat 表示指定的生成路径的格式
   * @param fileName 表示文件的名字
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
   * 在java GUI中展示图片.
   * @param path 表示路径
   * @throws IOException 抛出错误
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
