package homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/28
 * Time: 16:38
 */
public class TestInsert {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb", "test", "123456");

        try{
            conn.setAutoCommit(false);

            String sql = "INSERT INTO `order`(user_id,goods_id,total_amount,status,create_time) VALUES(?,?,?,1,?);";
            PreparedStatement ptmt = conn.prepareStatement(sql);

            int i=1;
            while(i<1000000){
                ptmt.setLong(1, i);
                ptmt.setLong(2, i);
                ptmt.setLong(3, i);
                ptmt.setLong(4, i);
                ptmt.addBatch();
                i++;
            }
            ptmt.executeBatch();

            conn.commit();
        }catch (Exception e){
            conn.rollback();
        }
    }
}
