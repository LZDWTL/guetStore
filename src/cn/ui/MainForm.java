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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        userinfoMenu = new JMenu();
        retriveuserMenu = new JMenuItem();
        updateuserMenu = new JMenuItem();
        Tools = new JMenu();
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
            }
            menuBar1.add(Tools);
        }
        setJMenuBar(menuBar1);

        //---- titleLab ----
        titleLab.setText("\u7535\u5b50\u5546\u94fa");
        titleLab.setFont(titleLab.getFont().deriveFont(titleLab.getFont().getStyle() | Font.BOLD, titleLab.getFont().getSize() + 48f));
        contentPane.add(titleLab);
        titleLab.setBounds(230, 190, 315, 57);

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
        contentPane.add(flashBut);
        flashBut.setBounds(new Rectangle(new Point(145, 405), flashBut.getPreferredSize()));

        //---- addBut ----
        addBut.setText("\u589e\u52a0");
        contentPane.add(addBut);
        addBut.setBounds(new Rectangle(new Point(290, 405), addBut.getPreferredSize()));

        //---- updateBut ----
        updateBut.setText("\u4fee\u6539");
        contentPane.add(updateBut);
        updateBut.setBounds(new Rectangle(new Point(440, 405), updateBut.getPreferredSize()));

        //---- delBut ----
        delBut.setText("\u5220\u9664");
        contentPane.add(delBut);
        delBut.setBounds(new Rectangle(new Point(580, 405), delBut.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(800, 510));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents


        unserinfoLab.setVisible(false);
        userinfoscrollPane.setVisible(false);
        flashBut.setVisible(false);
        addBut.setVisible(false);
        updateBut.setVisible(false);
        delBut.setVisible(false);

        JDBC jdbc=new JDBC();

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
