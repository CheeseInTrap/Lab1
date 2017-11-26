package def;


import java.awt.Container;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.ShowDirectedGraphControl;
import control.TextControl;
import entity.Text;
import form.FindMinDistForm;
import form.GenerateNewTextForm;
import form.QueryBridgeWordsForm;
import form.RandomWalkForm;
import form.ShowDirectedGraphForm;

public class LabOne {
  
  private static JFrame frame = new JFrame("Lab1");                           /*frame窗口*/
  private static JTabbedPane tabPane = new JTabbedPane();              /*选项卡布局*/
  private static Container container = new Container();                       /*容器*/
  private static JLabel label1 = new JLabel("文件目录");                        /*文件目录标签*/
  private static JLabel label2 = new JLabel("选择文件");                        /*选择文件标签*/
  private static JTextField text1 = new JTextField();                              /*第一个文本框框*/
  private static JTextField text2 = new JTextField();                              /*第二个文本框*/
  private static JButton button1 = new JButton("...");                           /*按键1*/
  private static JButton button2 = new JButton("...");                            /*按键2*/
  private static JButton buttonSel = new JButton("确认选择");              /*确认按键*/
  private static JFileChooser jfc = new JFileChooser();                          /*文件选择器*/
  private static JButton button3 = new JButton("展示有向图");               /*展示有向图按键*/
  private static JButton button4 = new JButton("查询巧桥接词");           /*查询桥接词按键*/
  private static JButton button5 = new JButton("根据bridge word生成新文本"); /*桥接词生成新文本按键*/
  private static JButton button6 = new JButton("计算两个单词之间的最短路径");/*最短路径按键*/
  private static JButton button7 = new JButton("随机游走");                  /*随机游走按键*/

  static File file= null;

  static String text;
  public static String DotStr;
  public static Text sm;
  /**
   * main函数入口.
   * @param args main函数参数
   */
  
  public static void main(String[] args) {
    
    init();
    button1.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        jfc.setFileSelectionMode(1);
        final int state = jfc.showOpenDialog(null);
        if (state == 1) {
          return;
        } else {
          file = jfc.getSelectedFile();
          text1.setText(file.getAbsolutePath());
        }
      }
    });

    button2.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent arg0) {
        jfc.setFileSelectionMode(0);
        final int state = jfc.showOpenDialog(null);
        if (state == 1) {
          return;
        } else {
          file = jfc.getSelectedFile();
          text2.setText(file.getAbsolutePath());
        }

      }
    });

    buttonSel.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent event) {
      	  TextControl.deleteFile("dotsource.dot");
          // TODO Auto-generated method stub
          JOptionPane.showMessageDialog(null, "成功", "提示", 2);
          file = jfc.getSelectedFile();
          if (file  != null) {
            sm = new Text();
            text = TextControl.readFile(file);
            text = sm.StringFormat(text);
            DotStr = sm.createDotFormat(text);
            System.out.println(text);
            System.out.println(DotStr);
        }
      }
    });

    button3.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent arg0) {
    	  new ShowDirectedGraphForm(DotStr,file);

      }
    });

    button4.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent event) {

        new QueryBridgeWordsForm(sm);

      }

    });

    button5.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent event) {

    	  new GenerateNewTextForm(sm, frame);


      }

    });

    button6.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent arg0) {
    	  new FindMinDistForm(sm, file);
      }
      
    });

    button7.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent event) {
    	  new RandomWalkForm(sm);
      }

    });
  }


  private static void init() {

    jfc.setCurrentDirectory(new File("d://"));
    final double lengthx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    final double lengthy = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    frame.setLocation(new Point((int)(lengthx / 2) - 150, (int)(lengthy / 2) - 150));
    frame.setSize(400,500);
    frame.setContentPane(tabPane);

    label1.setBounds(10,10,70,20);
    text1.setBounds(75, 10, 120, 20);
    button1.setBounds(210, 10, 50, 20);
    label2.setBounds(10, 35, 70, 20);
    text2.setBounds(75, 10, 120, 20);
    button2.setBounds(210, 35, 50, 20);

    buttonSel.setBounds(30,60,180,20);
    button3.setBounds(30,90,180,20);
    button4.setBounds(30,120,180,20);
    button5.setBounds(30,150,180,20);
    button6.setBounds(30,180,180,20);
    button7.setBounds(30, 210, 180, 20);


    container.add(label1);
    container.add(text1);
    container.add(button1);
    container.add(label2);
    container.add(text2);
    container.add(button2);
    container.add(buttonSel);
    container.add(button3);
    container.add(button4);
    container.add(button5);
    container.add(button6);
    container.add(button7);
   
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    tabPane.add("面板", container);
  }
}

