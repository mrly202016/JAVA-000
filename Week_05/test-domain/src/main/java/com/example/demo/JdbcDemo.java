package com.example.demo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/18
 * Time: 22:33
 */
public class JdbcDemo {
    public static void main(String[] args) throws Exception {
        //JDBC原生
        Class.forName("com.mysql.jdbc.Driver");
//        Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb", "root", "123456");

        //Hikari连接池
        HikariConfig config = new HikariConfig("/hikari.properties");
        HikariDataSource ds = new HikariDataSource(config);
        Connection conn = ds.getConnection();

        //增删改查
        add(conn);
        update(conn);
        del(conn);
        query(conn);
        //事务+批处理
        transtion(conn);
    }

    private static void transtion(Connection conn) throws SQLException {
        try{
            conn.setAutoCommit(false);

            String sql = "INSERT INTO student(name,age) values(?,?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);

            ptmt.setString(1, "tom");
            ptmt.setInt(2, 30);
            ptmt.addBatch();

            ptmt.setString(1, "kate");
            ptmt.setInt(2, 32);
            ptmt.addBatch();

            int[] count = ptmt.executeBatch();
            if(count.length==2){
                conn.commit();
            }

            conn.rollback();
        }catch (Exception e){
            conn.rollback();
        }
    }

    private static void query(Connection conn) throws SQLException {
        String sql = "SELECT * FROM  student WHERE id=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);

        ptmt.setInt(1, 2);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            System.out.println(rs.getInt("id"));
            System.out.println(rs.getString("name"));
            System.out.println(rs.getInt("age"));
        }
    }

    private static void del(Connection conn) throws SQLException {
        String sql = "DELETE FROM student WHERE id=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);

        ptmt.setInt(1, 2);
        ptmt.execute();
    }

    private static void update(Connection conn) throws SQLException {
        String sql = "UPDATE student SET age=? WHERE id=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);

        ptmt.setInt(1, 20);
        ptmt.setInt(2, 2);
        ptmt.execute();
    }

    static void add(Connection conn) throws SQLException {
        String sql = "INSERT INTO student(name,age) values(?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);

        ptmt.setString(1, "jack");
        ptmt.setInt(2, 30);
        ptmt.execute();
    }
}
