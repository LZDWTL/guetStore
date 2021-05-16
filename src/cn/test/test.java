package cn.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author LZDWTL
 * @date 2021-05-09 20:14
 */
public class test {
    public static void main(String[] args) {
        JFrame jf=new JFrame();
        JPanel panel=new JPanel();
        JButton jb=new JButton("testButton");
        jb.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(panel, "二维码生成失败，请重新生成", "错误！",JOptionPane.ERROR_MESSAGE);
                    }
                }
        );
        jf.add(jb);
        jf.add(panel);
        jf.setLayout(null);
        jb.setVisible(true);
        panel.setVisible(true);
        jf.setVisible(true);
        jb.setBounds(100,100,80,40);
        panel.setBounds(186,157,300,200);
        jf.setBounds(300,200,700,500);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
