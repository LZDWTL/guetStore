package cn.ui;

import cn.util.JDBC;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/*
 * Created by JFormDesigner on Fri Apr 23 20:09:56 CST 2021
 */


/**
 * @author unknown
 */
public class LoginForm extends JFrame {

    public LoginForm() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents

        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField("蓝雪瑞"); //用户名
        textField2 = new JTextField("123456"); //密码
        button1 = new JButton();  //登陆按钮
        button2 = new JButton();  //重置按钮
        JDBC jdbc = new JDBC();
        String username=textField1.getText();
        String password=textField2.getText();

        button1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(jdbc.selectUser(username,password)){
                            MainForm mainForm=new MainForm();
                            mainForm.setVisible(true);

                            setVisible(false);
                        }
                    }
                }
        );
        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u7528\u6237\u540d\uff1a");
        contentPane.add(label1);
        label1.setBounds(125, 170, 70, label1.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u5bc6\u7801\uff1a");
        contentPane.add(label2);
        label2.setBounds(125, 200, 40, label2.getPreferredSize().height);
        contentPane.add(textField1);
        textField1.setBounds(195, 165, 220, 30);
        contentPane.add(textField2);
        textField2.setBounds(195, 195, 220, 30);

        //---- button1 ----
        button1.setText("\u767b\u9646");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(105, 260), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(315, 260), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(500, 390));
        pack();
        setLocationRelativeTo(getOwner());

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  //关闭窗口的时候关闭进程

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
