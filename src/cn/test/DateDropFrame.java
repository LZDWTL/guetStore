package cn.test;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

/**
 * @author LZDWTL
 * @date 2021-05-02 21:46
 */
public class DateDropFrame extends JFrame{
    public static void main(String[] args) {
        new DateDropFrame();
    }
    int start;
    int end;
    public void DateDropFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        final JComboBox year = new JComboBox();
        year.setModel(new DefaultComboBoxModel(getModel(start, end)));
        contentPane.add(year);
        final JComboBox month = new JComboBox();
        month.setModel(new DefaultComboBoxModel(getModel(1, 12)));
        contentPane.add(month);
        final JComboBox day = new JComboBox();
        contentPane.add(day);
        year.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                setDay(year, month, day);
            }
        });
        month.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                setDay(year, month, day);
            }
        });
        setDay(year, month, day);
        setContentPane(contentPane);
    }
    /**
     * calculate days in select month & year
     */
    private void setDay(JComboBox year, JComboBox month, JComboBox day) {
        int y = Integer.parseInt((String) year.getSelectedItem());
        int m = Integer.parseInt((String) month.getSelectedItem());
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, y);
        c.set(Calendar.MONTH, m - 1);
        int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        day.setModel(new DefaultComboBoxModel(getModel(1, days)));
    }
    /**
     * get String array [start, end]
     */
    private String[] getModel(int start, int end) {
        String[] m = new String[end - start + 1];
        for (int i = 0; i < m.length; i++) {
            m[i] = String.valueOf(i + start);
        }
        return m;
    }
}
