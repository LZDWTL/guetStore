/*
 * Created by JFormDesigner on Sun May 09 09:24:05 CST 2021
 */

package cn.ui;

import cn.pay.Main;
import cn.test.Demo02;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

/**
 * @author 1
 */
public class PayForm extends JFrame {
    public PayForm() {
        initComponents();
    }

    /**
     * 付款码支付
     * @param e
     */
    private void paycodeButtonActionPerformed(ActionEvent e) {
        String authCode =accounttextField.getText();
        accounttextField.setText("");
        Main main=new Main();
        main.test_trade(authCode);
    }


    /**
     * 扫码支付
     * @param e
     */
    private void scancodepayButtonActionPerformed(ActionEvent e) {
        final File[] file = new File[1];
        final String[] path = new String[1];
        Thread t = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Main main = new Main();
//                        file[0] = new File(main.test_trade_precreate());    //此处为什么要使用final修饰？
                        path[0] =main.test_trade_precreate();
                    }
                }
        );
        t.start();     //启动线程生成二维码
        try {
            t.join();      //让二维码生成之后再把二维码放入窗口中
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
//                        PaymentCodeForm paymentCodeForm = new PaymentCodeForm(file[0].getAbsolutePath());
                        PaymentCodeForm paymentCodeForm = new PaymentCodeForm(path[0]);
                        paymentCodeForm.setVisible(true);
//                        Demo02 demo02=new Demo02(file[0].getAbsolutePath());
                    }
                }
        ).start();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        accountLabel = new JLabel();
        paymentLabel = new JLabel();
        label3 = new JLabel();
        accounttextField = new JTextField();
        paytextField = new JTextField();
        changetextField = new JTextField();
        cashpayButton = new JButton();
        paycodeButton = new JButton();
        scancodepayButton = new JButton();
        alertPanel = new JPanel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- accountLabel ----
        accountLabel.setText("\u603b\u989d");
        accountLabel.setFont(accountLabel.getFont().deriveFont(accountLabel.getFont().getSize() + 10f));
        contentPane.add(accountLabel);
        accountLabel.setBounds(165, 60, 55, 30);

        //---- paymentLabel ----
        paymentLabel.setText("\u652f\u4ed8");
        paymentLabel.setFont(paymentLabel.getFont().deriveFont(paymentLabel.getFont().getSize() + 10f));
        contentPane.add(paymentLabel);
        paymentLabel.setBounds(165, 105, 55, 30);

        //---- label3 ----
        label3.setText("\u627e\u96f6");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 10f));
        contentPane.add(label3);
        label3.setBounds(165, 150, 55, 30);
        contentPane.add(accounttextField);
        accounttextField.setBounds(230, 65, 175, 30);
        contentPane.add(paytextField);
        paytextField.setBounds(230, 110, 175, 30);
        contentPane.add(changetextField);
        changetextField.setBounds(230, 155, 175, 30);

        //---- cashpayButton ----
        cashpayButton.setText("\u73b0\u91d1\u652f\u4ed8");
        cashpayButton.setFont(cashpayButton.getFont().deriveFont(cashpayButton.getFont().getSize() + 5f));
        contentPane.add(cashpayButton);
        cashpayButton.setBounds(75, 210, 110, 40);

        //---- paycodeButton ----
        paycodeButton.setText("\u4ed8\u6b3e\u7801\u652f\u4ed8");
        paycodeButton.setFont(paycodeButton.getFont().deriveFont(paycodeButton.getFont().getSize() + 5f));
        paycodeButton.addActionListener(e -> paycodeButtonActionPerformed(e));
        contentPane.add(paycodeButton);
        paycodeButton.setBounds(225, 210, 130, 40);

        //---- scancodepayButton ----
        scancodepayButton.setText("\u626b\u7801\u652f\u4ed8");
        scancodepayButton.setFont(scancodepayButton.getFont().deriveFont(scancodepayButton.getFont().getSize() + 5f));
        scancodepayButton.addActionListener(e -> scancodepayButtonActionPerformed(e));
        contentPane.add(scancodepayButton);
        scancodepayButton.setBounds(395, 210, 110, 40);

        //======== alertPanel ========
        {
            alertPanel.setLayout(null);
        }
        contentPane.add(alertPanel);
        alertPanel.setBounds(130, 60, 295, 125);

        contentPane.setPreferredSize(new Dimension(595, 320));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        /*setVisible(true);
        alertPanel.setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);*/
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel accountLabel;
    private JLabel paymentLabel;
    private JLabel label3;
    private JTextField accounttextField;
    private JTextField paytextField;
    private JTextField changetextField;
    private JButton cashpayButton;
    private JButton paycodeButton;
    private JButton scancodepayButton;
    private JPanel alertPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
   /* public static void main(String[] args) {
        new PayForm();
    }*/
}
