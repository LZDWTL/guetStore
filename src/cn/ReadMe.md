# 电子小卖部步骤：

## 1、登陆模块

1.  安装插件

2.  创建LoginForm

3.  创建USERS表

    ```sql
    CREATE TABLE USER(
        ID        NUMBER(6)   PRIMARY KEY,
        USERNAME  VARCHAR2(20),
        PASSWORD  VARCHAR2(100)   --MD5加密（符合企业项目）
    );
    
    
    INSERT INTO USERS VALUES(18003,'蓝雪瑞','123456');
    
    SELECT * FROM USERS WHERE USERNAME='蓝雪瑞' AND PASSWORD='123456';
    ```

4.  对登陆按钮添加监听器

    ```java
    package cn.ui;
    
    import cn.util.Select;
    
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import javax.swing.*;
    
    public class LoginForm extends JFrame {
    
        public LoginForm() {
            initComponents();
        }
    
        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            label1 = new JLabel();
            label2 = new JLabel();
            textField1 = new JTextField("蓝雪瑞"); //用户名
            textField2 = new JTextField("123456"); //密码
            button1 = new JButton();  //登陆按钮
            button2 = new JButton();  //重置按钮
            Select select = new Select();
            String username=textField1.getText();
            String password=textField2.getText();
    
            button1.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            select.selectUser(username,password);
                            //if(select.selectUser(username,password)){
                                //System.out.println(username+password);
                                //setVisible(false);
                            //}
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
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
    
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
    ```



5.  修改验证方式：通过MD5对密码进行加密

    1. 把密码进行转换

        ```java
        public static void main(String args[]) throws Exception{
        		System.out.println(encoderByMd5("123456"));
        	}
        ```

    2. 把转换后的密码在数据库进行更新

        ```java
        update users set password='4QrcOUm6Wau+VuBX8g+IPg==' where username='蓝雪瑞';
        ```

    3. JDBC类中调用修改如下

        ```java
        package cn.util;
        
        import java.io.UnsupportedEncodingException;
        import java.security.NoSuchAlgorithmException;
        import java.sql.*;
        
        /**
         * @author 10237
         * @date 2021-04-23 20:58
         */
        public class JDBC {
            public Boolean selectUser(String username,String password){
                Connection conn=null;
                String url ="jdbc:oracle:thin:@120.77.80.134:1521:orcl";
                Statement stmt=null;  //SQL语句对象
                String sql="select password from users where username='"+username+"'";
                ResultSet rs=null;
                try{
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn= DriverManager.getConnection(url,"scott","tiger");
                    stmt=conn.createStatement();
                    rs=stmt.executeQuery(sql);  //executeQuery会返回一个结果集
                    //rs是结果集，又称为游标，就是一个内存区（缓冲区），查询的结果都在缓冲区
                    rs.next();
                    String encoderPassword=rs.getString(1);
                    Boolean isSuccecc=MD5.checkpassword(password,encoderPassword);  //前一个数据是文本框中的，后一个数据是数据库中的
                    if(isSuccecc){
                        return true;
                    }
                    else{
                        return false;
                    }
                }catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e){
                    e.printStackTrace();
                } finally{
                    try {
                        rs.close();
                        stmt.close();
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        
        }
        
        ```

        