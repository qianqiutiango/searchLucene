package cn.swufe.Util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*

 */
public class dbconnection {
    //1、执行静态方法,加载数据库驱动
    static {
        try {
            System.out.println("正在加载数据库驱动...");
            System.out.println("Class.forName('com.mysql.jdbc.Driver');");

            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("已加载数据库驱动！！！\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //2、创建数据库连接的方法
    public Connection getConnection() {
        Connection connection = null;
        try {
            System.out.println("正在连接到数据库...");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lucene", "root", "root");

            System.out.println("已连接到lucene数据库！！！\n");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;


    }
    //3、关闭数据库连接，释放JDBC资源的方法
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                System.out.println("准备释放jdbc资源，断开数据库连接...");
                System.out.println("connection.close();");

                connection.close();//立即释放jdbc资源，而不是等自动释放

                System.out.println("已断开数据库连接并且释放了jdbc资源\n");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
