/*
 * Created by JFormDesigner on Sun May 09 10:32:56 CST 2021
 */

package cn.ui;

import java.awt.*;
import javax.swing.*;

/**
 * 该窗体用于生成二维码
 * @author 1
 */
public class PaymentCodeForm extends JFrame {

    public PaymentCodeForm(String path) {
        System.out.println(path);
        initComponents(path);
    }

    private void initComponents(String path) {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        contentPane.setPreferredSize(new Dimension(255, 260));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        imgPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(path);
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, icon.getIconWidth(), icon.getIconHeight(), icon.getImageObserver());
//                imgPanel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());   //不能在创建对象的时候设置位置，这回导致图片不显示
            }
        };
/*
        imgLabel=new JLabel();
        ImageIcon icon=new ImageIcon(path);
        imgLabel.setIcon(icon);
        imgLabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        contentPane.add(imgLabel);
*/

        contentPane.add(imgPanel);
        imgPanel.setBounds(0,0,255,305);

        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public JPanel imgPanel;
//    public JLabel imgLabel;



}
