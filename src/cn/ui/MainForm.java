/*
 * Created by JFormDesigner on Fri Apr 23 23:24:59 CST 2021
 */

package cn.ui;

import cn.util.JDBC;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author 1
 */
public class MainForm extends JFrame {
    JDBC jdbc = new JDBC();

    public MainForm() {
        initComponents();
    }

    private void retriveuserMenuMousePressed(MouseEvent e) {
        // TODO add your code here
        unserinfoLab.setVisible(true);
        userinfoscrollPane.setVisible(true);
        titleLab.setVisible(false);
        flashBut.setVisible(true);
        addBut.setVisible(true);
        updateBut.setVisible(true);
        delBut.setVisible(true);
    }

    private void addButActionPerformed(ActionEvent e) {
        unserinfoLab.setVisible(false);
        panel1.setVisible(true);
        titleLab.setVisible(false);
        userinfoscrollPane.setVisible(false);
    }

    private void flashButActionPerformed(ActionEvent e) {

        unserinfoLab.setVisible(true);
        userinfoscrollPane.setVisible(true);
        panel1.setVisible(false);
        DefaultTableModel tableModel = new DefaultTableModel(jdbc.selectUserInfo(), jdbc.getHead()) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        userinfoTab.setModel(tableModel);
    }

    private void returnButtonActionPerformed(ActionEvent e) {
        unserinfoLab.setVisible(true);
        userinfoscrollPane.setVisible(true);
        panel1.setVisible(false);
    }

    private void saveButtonActionPerformed(ActionEvent e) {
        String id=idtextField.getText();
        String name=nametextField.getText();
        String passwd=passwdtextField.getText();
        jdbc.addData(Integer.parseInt(id),name,passwd);
        idtextField.setText("");
        nametextField.setText("");
        passwdtextField.setText("");
    }

    private void delButActionPerformed(ActionEvent e) {
        int rownum=userinfoTab.getSelectedRow();        //获取所选中的行
        String id =userinfoTab.getValueAt(rownum,0).toString();  //获取选中的行中的第一列
        jdbc.delData(id);
    }

    private void paymenuItemActionPerformed(ActionEvent e) {
        PayForm payForm=new PayForm();
        payForm.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        userinfoMenu = new JMenu();
        retriveuserMenu = new JMenuItem();
        updateuserMenu = new JMenuItem();
        Tools = new JMenu();
        paymenuItem = new JMenuItem();
        panel1 = new JPanel();
        idLabel = new JLabel();
        idtextField = new JTextField();
        nameLabel = new JLabel();
        nametextField = new JTextField();
        passwdLabel = new JLabel();
        passwdtextField = new JTextField();
        saveButton = new JButton();
        returnButton = new JButton();
        titleLab = new JLabel();
        userinfoscrollPane = new JScrollPane();
        userinfoTab = new JTable();
        unserinfoLab = new JLabel();
        flashBut = new JButton();
        addBut = new JButton();
        updateBut = new JButton();
        delBut = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //======== userinfoMenu ========
            {
                userinfoMenu.setText("\u7528\u6237\u4fe1\u606f");

                //---- retriveuserMenu ----
                retriveuserMenu.setText("\u67e5\u770b\u7528\u6237");
                retriveuserMenu.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        retriveuserMenuMousePressed(e);
                    }
                });
                userinfoMenu.add(retriveuserMenu);

                //---- updateuserMenu ----
                updateuserMenu.setText("\u4fee\u6539\u7528\u6237");
                userinfoMenu.add(updateuserMenu);
            }
            menuBar1.add(userinfoMenu);

            //======== Tools ========
            {
                Tools.setText("Tools");

                //---- paymenuItem ----
                paymenuItem.setText("\u652f\u4ed8");
                paymenuItem.addActionListener(e -> paymenuItemActionPerformed(e));
                Tools.add(paymenuItem);
            }
            menuBar1.add(Tools);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- idLabel ----
            idLabel.setText("\u7528\u6237ID");
            idLabel.setFont(idLabel.getFont().deriveFont(idLabel.getFont().getSize() + 5f));
            panel1.add(idLabel);
            idLabel.setBounds(new Rectangle(new Point(60, 40), idLabel.getPreferredSize()));
            panel1.add(idtextField);
            idtextField.setBounds(135, 40, 155, idtextField.getPreferredSize().height);

            //---- nameLabel ----
            nameLabel.setText("\u7528\u6237\u540d");
            nameLabel.setFont(nameLabel.getFont().deriveFont(nameLabel.getFont().getSize() + 5f));
            panel1.add(nameLabel);
            nameLabel.setBounds(new Rectangle(new Point(60, 80), nameLabel.getPreferredSize()));
            panel1.add(nametextField);
            nametextField.setBounds(135, 80, 155, nametextField.getPreferredSize().height);

            //---- passwdLabel ----
            passwdLabel.setText("\u5bc6   \u7801");
            passwdLabel.setFont(passwdLabel.getFont().deriveFont(passwdLabel.getFont().getSize() + 5f));
            panel1.add(passwdLabel);
            passwdLabel.setBounds(new Rectangle(new Point(60, 120), passwdLabel.getPreferredSize()));
            panel1.add(passwdtextField);
            passwdtextField.setBounds(135, 120, 155, passwdtextField.getPreferredSize().height);

            //---- saveButton ----
            saveButton.setText("\u4fdd\u5b58");
            saveButton.addActionListener(e -> saveButtonActionPerformed(e));
            panel1.add(saveButton);
            saveButton.setBounds(new Rectangle(new Point(80, 175), saveButton.getPreferredSize()));

            //---- returnButton ----
            returnButton.setText("\u8fd4\u56de");
            returnButton.addActionListener(e -> returnButtonActionPerformed(e));
            panel1.add(returnButton);
            returnButton.setBounds(new Rectangle(new Point(205, 175), returnButton.getPreferredSize()));
        }
        contentPane.add(panel1);
        panel1.setBounds(215, 125, 360, 225);

        //---- titleLab ----
        titleLab.setText("\u7535\u5b50\u5546\u94fa");
        titleLab.setFont(titleLab.getFont().deriveFont(titleLab.getFont().getStyle() | Font.BOLD, titleLab.getFont().getSize() + 48f));
        contentPane.add(titleLab);
        titleLab.setBounds(205, 180, 315, 57);

        //======== userinfoscrollPane ========
        {
            userinfoscrollPane.setViewportView(userinfoTab);
        }
        contentPane.add(userinfoscrollPane);
        userinfoscrollPane.setBounds(115, 95, 575, 285);

        //---- unserinfoLab ----
        unserinfoLab.setText("\u7528\u6237\u4fe1\u606f");
        unserinfoLab.setFont(unserinfoLab.getFont().deriveFont(unserinfoLab.getFont().getSize() + 21f));
        contentPane.add(unserinfoLab);
        unserinfoLab.setBounds(335, 40, 165, 35);

        //---- flashBut ----
        flashBut.setText("\u5237\u65b0");
        flashBut.addActionListener(e -> flashButActionPerformed(e));
        contentPane.add(flashBut);
        flashBut.setBounds(new Rectangle(new Point(145, 405), flashBut.getPreferredSize()));

        //---- addBut ----
        addBut.setText("\u589e\u52a0");
        addBut.addActionListener(e -> addButActionPerformed(e));
        contentPane.add(addBut);
        addBut.setBounds(new Rectangle(new Point(290, 405), addBut.getPreferredSize()));

        //---- updateBut ----
        updateBut.setText("\u4fee\u6539");
        contentPane.add(updateBut);
        updateBut.setBounds(new Rectangle(new Point(440, 405), updateBut.getPreferredSize()));

        //---- delBut ----
        delBut.setText("\u5220\u9664");
        delBut.addActionListener(e -> delButActionPerformed(e));
        contentPane.add(delBut);
        delBut.setBounds(new Rectangle(new Point(580, 405), delBut.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(800, 510));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        panel1.setVisible(false);
        unserinfoLab.setVisible(false);
        userinfoscrollPane.setVisible(false);
        flashBut.setVisible(false);
        addBut.setVisible(false);
        updateBut.setVisible(false);
        delBut.setVisible(false);



        /**
         * 下面这几行代码可以让JTable显示数据
         */
        DefaultTableModel tableModel = new DefaultTableModel(jdbc.selectUserInfo(), jdbc.getHead()) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        userinfoTab.setModel(tableModel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu userinfoMenu;
    private JMenuItem retriveuserMenu;
    private JMenuItem updateuserMenu;
    private JMenu Tools;
    private JMenuItem paymenuItem;
    private JPanel panel1;
    private JLabel idLabel;
    private JTextField idtextField;
    private JLabel nameLabel;
    private JTextField nametextField;
    private JLabel passwdLabel;
    private JTextField passwdtextField;
    private JButton saveButton;
    private JButton returnButton;
    private JLabel titleLab;
    private JScrollPane userinfoscrollPane;
    private JTable userinfoTab;
    private JLabel unserinfoLab;
    private JButton flashBut;
    private JButton addBut;
    private JButton updateBut;
    private JButton delBut;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
