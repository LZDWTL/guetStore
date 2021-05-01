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
    java.util.List<Users> list=new ArrayList<Users>();     //������װ�뵽list�У�Ȼ���ŵ�Jtable
    private String url="jdbc:oracle:thin:@120.77.80.134:1521:orcl"; //2���������ݿ�
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
            Class.forName("oracle.jdbc.driver.OracleDriver");   //1����������
            conn = DriverManager.getConnection(url,"scott","tiger");  //2���������ݿ�
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Boolean selectUser(String username,String password){
        Statement stmt=null;  //3������SQL������
        String sql="select password from users where username='"+username+"'";
        try{
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);  //executeQuery�᷵��һ�������     4��ִ��SQL���
            //rs�ǽ�������ֳ�Ϊ�α꣬����һ���ڴ�����������������ѯ�Ľ�����ڻ�����
            rs.next();          //5����������
            String encoderPassword=rs.getString(1);
            Boolean isSuccecc=MD5.checkpassword(password,encoderPassword);  //ǰһ���������ı����еģ���һ�����������ݿ��е�
            if(isSuccecc){
                System.out.println("��½�ɹ�!");
                return true;
            }
            else{
                System.out.println("��½ʧ��!");
                return false;
            }
        }catch ( SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
        } finally{
            try {
                rs.close();              //6���ر����ӣ��ͷ���Դ
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
                rs.close();  //�رս����
                statement.close();   //�ر�SQL����
                conn.close();    //�ر�����
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
