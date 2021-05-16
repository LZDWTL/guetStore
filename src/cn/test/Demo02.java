package cn.test;

import javax.swing.*;
import java.awt.*;

public class Demo02 {

    JFrame jframe;

    public static JPanel imgPanel;

    public Demo02(String path) {
        initFrame(path);
    }

    // 初始化窗口
    public void initFrame(String path) {
        // 利用JPanel添加背景图片
        jframe = new JFrame();
        //String path="D:\\临时文件夹\\蓝桥视频及Markdown笔记\\PracticeCode\\2021-4\\支付宝沙盒\\qr-tradeprecreate16205451445333127861.png";
        imgPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(path);
                System.out.println(path);
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, icon.getIconWidth(), icon.getIconHeight(), icon.getImageObserver());
            }
        };

        jframe.setTitle("测试jpanel图片");
        jframe.add(imgPanel);
       // jframe.pack();
        jframe.setBounds(900,320,270,320);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

//    public static void main(String[] args) {
//        new Demo02();
//    }
}
