/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.system.model.DbConfig;
import library.system.util.DbConfigLoader;

/**
 *
 * @author Sithu
 */
public class Database {
    
    private Connection conn;
    
    private static Database db;
    private String host = "localhost";
    private int port = 3306;
    private String userName = "root";
    private String password = "letmein";
    
    private Database() throws SQLException{
         connect();
         createDatabase();
         createTable();
    }
    
    public static Database getInstance() throws SQLException{
        if(db==null){
            db = new Database();
        }
        return db;
    }
    
    public void connect() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DbConfig dbConfig =  DbConfigLoader.loadDbConfig();
        
        if(dbConfig!=null){
            host = dbConfig.getHost();
            port = dbConfig.getPort();
            userName = dbConfig.getUser();
            password = dbConfig.getPassword();
        }
        
        String url = "jdbc:mysql://"+host+":"+port+"/";
        
        conn = DriverManager.getConnection(url,userName,password);
        
    }
    
    public void disconnect() throws SQLException{
        if(conn!=null){
            conn.close();
        }
    }
    
    public void createDatabase() throws SQLException{
        
        String sql = "create database if not exists lbdb";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
       
        System.out.println("Database created.");
    }
    
    public void createTable() throws SQLException{
        
        String sql1 = "create table if not exists lbdb.books (id int primary key auto_increment,title varchar(255),author varchar(40),publisher varchar(40),available boolean default true)";
        Statement stmt1 = conn.createStatement();
        stmt1.execute(sql1);
        
        String sql2 = "create table if not exists lbdb.members (id int primary key auto_increment,name varchar(50),mobile varchar(40),address varchar(255))";
        Statement stmt2 = conn.createStatement();
        stmt2.execute(sql2);
        
        String sql3 = "create table if not exists lbdb.issue (book_id int,member_id int,issue_date date,renew_count int,foreign key (book_id) references books(id),foreign key (member_id) references members(id))";
        Statement stmt3 = conn.createStatement();
        stmt3.execute(sql3);
        
        System.out.println("Table created.");
        
    }
    
    public Connection getConnection(){
        return conn;
    }

    
}
