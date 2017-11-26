package form;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import entity.Text;

public class QueryBridgeWordsForm {
	
	public QueryBridgeWordsForm(Text sm){
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

}
