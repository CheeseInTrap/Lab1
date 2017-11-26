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
  public static Text sm;
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
      	  TextControl.deleteFile("dotsource.dot");
          // TODO Auto-generated method stub
          JOptionPane.showMessageDialog(null, "�ɹ�", "��ʾ", 2);
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
    tabPane.add("���", container);
  }
}

