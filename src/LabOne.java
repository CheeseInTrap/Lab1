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
  
  private static JFrame frame = new JFrame("Lab1");                           /*frame����*/
  private static JTabbedPane tabPane = new JTabbedPane();              /*ѡ�����*/
  private static Container container = new Container();                       /*����*/
  private static JLabel label1 = new JLabel("�ļ�Ŀ¼");                        /*�ļ�Ŀ¼��ǩ*/
  private static JLabel label2 = new JLabel("ѡ���ļ�");                        /*ѡ���ļ���ǩ*/
  private static JTextField text1 = new JTextField();                              /*��һ���ı����*/
  private static JTextField text2 = new JTextField();                              /*�ڶ����ı���*/
  private static JButton button1 = new JButton("...");                           /*����1*/
  private static JButton button2 = new JButton("...");                            /*����2*/
  private static JButton buttonSel = new JButton("ȷ��ѡ��");              /*ȷ�ϰ���*/
  private static JFileChooser jfc = new JFileChooser();                          /*�ļ�ѡ����*/
  private static JButton button3 = new JButton("չʾ����ͼ");               /*չʾ����ͼ����*/
  private static JButton button4 = new JButton("��ѯ���ŽӴ�");           /*��ѯ�ŽӴʰ���*/
  private static JButton button5 = new JButton("����bridge word�������ı�"); /*�ŽӴ��������ı�����*/
  private static JButton button6 = new JButton("������������֮������·��");/*���·������*/
  private static JButton button7 = new JButton("�������");                  /*������߰���*/

  static File file= null;

  static String text;
  public static String DotStr;
  public static StringManager sm;
  /**
   * main�������.
   * @param args main��������
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
        JOptionPane.showMessageDialog(null, "�ɹ�", "��ʾ", 2);
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

        final JLabel labelWord1 = new JLabel("����1");
        final JLabel labelWord2 = new JLabel("����2");
        final JTextField word1 = new JTextField();
        final JTextField word2 = new JTextField();
        final JTextField queryResult = new JTextField();
        final JButton buttonConfirm = new JButton("ȷ��");


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


        paneQuery.add("��ѯ�ŽӴ�",conQuery);

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
              queryResult.setText("û���ŽӴ�");
            }
            queryResult.setText(result);
          }

        });

      }

    });

    button5.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent event) {

        final JFrame frameGenerate = new JFrame("�������ı�");
        final JTabbedPane paneGenerate = new JTabbedPane();
        final Container container = new Container();
        final JTextArea inputText = new JTextArea(8,20);
        final JTextArea outputText = new JTextArea(8,20);

        frameGenerate.setVisible(true);
        frameGenerate.setSize(300, 400);
        frameGenerate.setContentPane(paneGenerate);

        final JButton button1 = new JButton("ȷ��");
        
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
        label1.setText("�����ı�");
        label2.setBounds(10, 150, 80, 20);
        label2.setText("����ı�");
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

        paneGenerate.add("������ı�", container);

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

        final JLabel labelWord1 = new JLabel("����1");
        final JLabel labelWord2 = new JLabel("����2");
        final JTextField word1 = new JTextField();
        final JTextField word2 = new JTextField();
        final JTextField queryResult = new JTextField();
        final JButton buttonConfirm = new JButton("ȷ��");

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


        paneQuery.add("�������������·��",conQuery);

        frameQuery.setVisible(true);
        frameQuery.setSize(300, 400);

        buttonConfirm.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            final String strw1 =  word1.getText();
            final String strw2 = word2.getText();

            final String path = sm.calcShortestPath(strw1, strw2);
            queryResult.setText(path);
            //System.out.println("���·���ǣ�"+path);
            //System.out.println("graphstr �ǣ�"+StringManager.graphstr);

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
        paneQuery.add("�������", conResult);

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
        //System.out.println("������ߵĽ���ǣ�"+result);
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
    tabPane.add("���", container);
  }
}
