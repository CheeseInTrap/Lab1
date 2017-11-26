package form;

import java.awt.Container;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import entity.Text;

public class GenerateNewTextForm {

	public GenerateNewTextForm(Text sm,JFrame frame)
	{
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

	}
