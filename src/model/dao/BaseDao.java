package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {
    private String url = "jdbc:mysql://127.0.0.1:3306/smart_closet";
    private String user = "root";
    private String password = "1234";

    protected Connection conn;

    // 3. 연동 메소드
    protected void connect( ){
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) { System.out.println("데이터베이스 연동실패" + e);}
    }
    protected BaseDao(){ connect(); }
}
