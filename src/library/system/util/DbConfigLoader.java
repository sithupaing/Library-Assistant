/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import library.system.model.DbConfig;

/**
 *
 * @author Sithu
 */
public class DbConfigLoader {
    
    public static DbConfig loadDbConfig(){
        
         Properties prop = new Properties();
         
         DbConfig dbConfig = null;

        try (InputStream os = new FileInputStream("dbconfig.properties")) {

            prop.load(os);

            String host = prop.getProperty("host");
            String port = prop.getProperty("port");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            
            dbConfig = new DbConfig(host, Integer.parseInt(port), user, password);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return dbConfig;
    }
    
    public static void saveDbConfig(DbConfig dbConfig){
        
        Properties prop = new Properties();

        try (OutputStream os = new FileOutputStream("dbconfig.properties")) {

            prop.setProperty("host", dbConfig.getHost());
            prop.setProperty("port", Integer.toString(dbConfig.getPort()));
            prop.setProperty("user", dbConfig.getUser());
            prop.setProperty("password", dbConfig.getPassword());

            prop.store(os, "User Properties");

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
