import java.awt.Container;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


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
  public static StringManager sm;
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

        FileManager.deleteFile("dotsource.dot");
        // TODO Auto-generated method stub
        JOptionPane.showMessageDialog(null, "成功", "提示", 2);
        file = jfc.getSelectedFile();
        if (file  != null) {
          sm = new StringManager();
          text = FileManager.readFile(file);
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
        GraphManager.showDirectedGraph(DotStr, "DotGraph");
        try {
          GraphManager.showImage(file.getParent() + "\\DotGraph.jpg");
        } catch (IOException e) {
          e.printStackTrace();
        }

      }
    });

    button4.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent event) {

        final JFrame frameQuery = new JFrame();

        final JTabbedPane paneQuery = new JTabbedPane();
        frameQuery.setContentPane(paneQuery);
        final Container conQuery = new Container();

        final JLabel labelWord1 = new JLabel("单词1");
        final JLabel labelWord2 = new JLabel("单词2");
        final JTextField word1 = new JTextField();
        final JTextField word2 = new JTextField();
        final JTextField queryResult = new JTextField();
        final JButton buttonConfirm = new JButton("确定");


        labelWord1.setBounds(10,10,70,20);
        labelWord2.setBounds(10,40,70,20);
        word1.setBounds(70,10,150,20);
        word2.setBounds(70,40,150,20);
        queryResult.setBounds(60,80,150,20);
        buttonConfirm.setBounds(60, 110, 50, 20);
        
        conQuery.add(labelWord1);
        conQuery.add(labelWord2);
        conQuery.add(word1);
        conQuery.add(word2);
        conQuery.add(queryResult);
        conQuery.add(buttonConfirm);


        paneQuery.add("查询桥接词",conQuery);

        frameQuery.setVisible(true);
        frameQuery.setSize(300, 400);

        buttonConfirm.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(final ActionEvent arg0) {
            // TODO Auto-generated method stub
            final String wd1 = word1.getText().toString();
            final String wd2 = word2.getText().toString();
            //System.out.println(wd1+" "+wd2);
            final String result = sm.queryBridgeWords(wd1, wd2);
            if (result == null) {
              queryResult.setText("没有桥接词");
            }
            queryResult.setText(result);
          }

        });

      }

    });

    button5.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent event) {

        final JFrame frameGenerate = new JFrame("生成新文本");
        final JTabbedPane paneGenerate = new JTabbedPane();
        final Container container = new Container();
        final JTextArea inputText = new JTextArea(8,20);
        final JTextArea outputText = new JTextArea(8,20);

        frameGenerate.setVisible(true);
        frameGenerate.setSize(300, 400);
        frameGenerate.setContentPane(paneGenerate);

        final JButton button1 = new JButton("确定");
        
        final JLabel label1 = new JLabel();
        final JLabel label2 = new JLabel();
        final JScrollPane scrollPane1 = new JScrollPane(inputText);
        final JScrollPane scrollPane2 = new JScrollPane(outputText);

        inputText.setLineWrap(true);
        outputText.setLineWrap(true);
        inputText.setWrapStyleWord(true);
        outputText.setWrapStyleWord(true);
        final double lengthx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        final double lengthy = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setLocation(new Point((int)(lengthx / 2) - 150, (int)(lengthy / 2) - 150));
        //container.add(inputText);
        //container.add(outputText);

        label1.setBounds(10,10,80,20);
        label1.setText("输入文本");
        label2.setBounds(10, 150, 80, 20);
        label2.setText("输出文本");
        inputText.setBounds(80, 10, 180, 170);
        outputText.setBounds(80, 150, 180, 170);
        scrollPane1.setBounds(80, 10, 180, 100);
        scrollPane2.setBounds(80, 150, 180, 100);
        button1.setBounds(80, 300, 80, 20);

        container.add(button1);
        container.add(label1);
        container.add(label2);
        container.add(scrollPane1);
        container.add(scrollPane2);

        paneGenerate.add("输出新文本", container);

        button1.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(final ActionEvent e) {
            final String input = inputText.getText().toString();
            System.out.println(input);
            final String output = sm.generateNewText(input);
            outputText.setText(output);
          }
        });


      }

    });

    button6.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent arg0) {
        final JFrame frameQuery = new JFrame();


        final JTabbedPane paneQuery = new JTabbedPane();
        frameQuery.setContentPane(paneQuery);
        final Container conQuery = new Container();

        final JLabel labelWord1 = new JLabel("单词1");
        final JLabel labelWord2 = new JLabel("单词2");
        final JTextField word1 = new JTextField();
        final JTextField word2 = new JTextField();
        final JTextField queryResult = new JTextField();
        final JButton buttonConfirm = new JButton("确定");

        labelWord1.setBounds(10,10,70,20);
        labelWord2.setBounds(10,40,70,20);
        word1.setBounds(70,10,150,20);
        word2.setBounds(70,40,150,20);
        queryResult.setBounds(60,80,150,20);
        buttonConfirm.setBounds(60, 110, 50, 20);

        conQuery.add(labelWord1);
        conQuery.add(labelWord2);
        conQuery.add(word1);
        conQuery.add(word2);
        conQuery.add(queryResult);
        conQuery.add(buttonConfirm);


        paneQuery.add("计算两单词最短路径",conQuery);

        frameQuery.setVisible(true);
        frameQuery.setSize(300, 400);

        buttonConfirm.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            final String strw1 =  word1.getText();
            final String strw2 = word2.getText();

            final String path = sm.calcShortestPath(strw1, strw2);
            queryResult.setText(path);
            //System.out.println("最短路径是："+path);
            //System.out.println("graphstr 是："+StringManager.graphstr);

            GraphManager.showDirectedGraph(StringManager.graphstr, "newDotGraph");
            try {
              GraphManager.showImage(file.getParent() + "\\newDotGraph.jpg");
            } catch (IOException e1) {
              // TODO Auto-generated catch block
              e1.printStackTrace();
            }
          }

        });
      }
      
    });

    button7.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent event) {
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
