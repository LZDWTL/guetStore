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
