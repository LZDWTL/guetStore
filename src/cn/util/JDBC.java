package cn.util;

import cn.bean.Users;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author 10237
 * @date 2021-04-23 20:58
 */
public class JDBC {
    java.util.List<Users> list=new ArrayList<Users>();     //将数据装入到list中，然后存放到Jtable
    private String url="jdbc:oracle:thin:@120.77.80.134:1521:orcl"; //2、连接数据库
    private Connection conn;
    private ResultSet rs=null;
    private Object[][] data=null;
    private String head[] = {"id", "username", "password"};

    public String[] getHead() {
        return head;
    }

    public void setHead(String[] head) {
        this.head = head;
    }

    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");   //1、创建驱动
            conn = DriverManager.getConnection(url,"scott","tiger");  //2、连接数据库
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Boolean selectUser(String username,String password){
        Statement stmt=null;  //3、创建SQL语句对象
        String sql="select password from users where username='"+username+"'";
        try{
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);  //executeQuery会返回一个结果集     4、执行SQL语句
            //rs是结果集，又称为游标，就是一个内存区（缓冲区），查询的结果都在缓冲区
            rs.next();          //5、处理结果集
            String encoderPassword=rs.getString(1);
            Boolean isSuccecc=MD5.checkpassword(password,encoderPassword);  //前一个数据是文本框中的，后一个数据是数据库中的
            if(isSuccecc){
                System.out.println("登陆成功!");
                return true;
            }
            else{
                System.out.println("登陆失败!");
                return false;
            }
        }catch ( SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
        } finally{
            try {
                rs.close();              //6、关闭链接，释放资源
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Object[][] selectUserInfo(){

        java.util.List<Users> list=new ArrayList<Users>();
        Statement statement=null;
        String sql="select * from users";
        try {
            statement=conn.createStatement();
            rs=statement.executeQuery(sql);
            while(rs.next()){
                Users users=new Users();
                users.setId(rs.getInt(1));
                users.setUsername(rs.getString(2));
                users.setPassword(rs.getString(1));
                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();  //关闭结果集
                statement.close();   //关闭SQL对象
                conn.close();    //关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        data =new Object[list.size()][head.length];
        for(int i=0;i<list.size();i++){
            for(int j =0;j<head.length;j++){
                data[i][0] = list.get(i).getId();
                data[i][1] = list.get(i).getUsername();
                data[i][2] = list.get(i).getPassword();
            }
        }
        return data;
    }
}
