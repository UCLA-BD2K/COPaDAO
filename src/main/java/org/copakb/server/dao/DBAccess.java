package org.copakb.server.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by vincekyi on 4/16/15.
 */
public class DBAccess {

    private static MysqlDataSource ds = null;
    private static DBAccess db = null;
    private static Properties prop = null;

    private DBAccess(){
        ds = new MysqlDataSource();

        // read application.properties file
        prop = new Properties();

        InputStream input = null;
        try {
            input = new FileInputStream("./target/classes/application.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        ds.setUrl(prop.getProperty("url"));
        ds.setUser(prop.getProperty("username"));
        ds.setPassword(prop.getProperty("password"));


    }

    public static DBAccess getInstance(){
        if(db == null)
            db = new DBAccess();

        return db;
    }

    public int query(String q){
        Connection conn = null;
        try {
            conn = ds.getConnection();

            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(q);

            if(res.next())
                return res.getInt(1);

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        finally{
            if(conn != null) {
                try {
                    conn.close();
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        }
        return -1;
    }
}
