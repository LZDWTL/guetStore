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
    java.util.List<Users> list = new ArrayList<Users>();     //������װ�뵽list�У�Ȼ���ŵ�Jtable

    private ResultSet rs = null;
    private Object[][] data = null;
    private String head[] = {"id", "username", "password"};

    public String[] getHead() {
        return head;
    }

    public void setHead(String[] head) {
        this.head = head;
    }


    public Boolean selectUser(String username, String password) {
        String url = "jdbc:oracle:thin:@120.77.80.134:1521:orcl"; //2���������ݿ�
        Connection conn = null;
        Statement stmt = null;  //3������SQL������
        String sql = "select password from users where username='" + username + "'";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");   //1����������
            conn = DriverManager.getConnection(url, "scott", "tiger");  //2���������ݿ�
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);  //executeQuery�᷵��һ�������     4��ִ��SQL���
            //rs�ǽ�������ֳ�Ϊ�α꣬����һ���ڴ�����������������ѯ�Ľ�����ڻ�����
            rs.next();          //5����������
            String encoderPassword = rs.getString(1);
            Boolean isSuccecc = MD5.checkpassword(password, encoderPassword);  //ǰһ���������ı����еģ���һ�����������ݿ��е�
            if (isSuccecc) {
                System.out.println("��½�ɹ�!");
                return true;
            } else {
                System.out.println("��½ʧ��!");
                return false;
            }
        } catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
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

    public Object[][] selectUserInfo() {

        java.util.List<Users> list = new ArrayList<Users>();
        String url = "jdbc:oracle:thin:@120.77.80.134:1521:orcl"; //2���������ݿ�
        Connection conn = null;
        Statement statement = null;
        String sql = "select * from users";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");   //1����������
            conn = DriverManager.getConnection(url, "scott", "tiger");  //2���������ݿ�
            statement = conn.createStatement();   //3������SQL����
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                Users users = new Users();
                users.setId(rs.getInt(1));
                users.setUsername(rs.getString(2));
                users.setPassword(rs.getString(3));
                list.add(users);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();  //�رս����
                statement.close();   //�ر�SQL����
                conn.close();    //�ر�����
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        data = new Object[list.size()][head.length];
        for (int i = 0; i < list.size(); i++) {
//            for (int j = 0; j < head.length; j++) {
                data[i][0] = list.get(i).getId();
                data[i][1] = list.get(i).getUsername();
                data[i][2] = list.get(i).getPassword();
//            }
        }
        return data;
    }

    public void addData(int id, String name, String passwd){
        String url="jdbc:oracle:thin:@120.77.80.134:1521:orcl";
        Connection conn=null;
        String sql="insert into users values(?,?,?)";   // ?��ռλ��
        PreparedStatement pstmt=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn=DriverManager.getConnection(url,"scott","tiger");
            pstmt=conn.prepareStatement(sql);

            pstmt.setInt(1,id);
            pstmt.setString(2,name);
            pstmt.setString(3,passwd);
            pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delData(String id){
        String url="jdbc:oracle:thin:@120.77.80.134:1521:orcl";
        Connection conn=null;
        String sql="delete from users where id=?";
        PreparedStatement pstmt=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn=DriverManager.getConnection(url,"scott","tiger");
            pstmt=conn.prepareStatement(sql);

            pstmt.setInt(1,Integer.parseInt(id));
            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
