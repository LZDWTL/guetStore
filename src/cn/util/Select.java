package cn.util;

import java.sql.*;

/**
 * @author 10237
 * @date 2021-04-23 20:58
 */
public class Select {
    public void selectUser(String username,String password){
        Connection conn=null;
        String url ="jdbc:oracle:thin:@120.77.80.134:1521:orcl";
        Statement stmt=null;  //SQL语句对象
        String sql="select * from users where username='"+username+"' and password='"+password+"'";
        ResultSet rs=null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn= DriverManager.getConnection(url,"scott","tiger");
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);  //executeQuery会返回一个结果集
            //rs是结果集，又称为游标，就是一个内存区（缓冲区），查询的结果都在缓冲区
            if(rs.next()){
                System.out.println("登陆成功");
            }
            else{
                System.out.println("登陆失败");
            }
        }catch (ClassNotFoundException | SQLException e){
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

    }
}
