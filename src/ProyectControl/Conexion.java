/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectControl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.sqlite.SQLiteDataSource;

/**
 *
 * @author jsarmiento
 */

public class Conexion {
     public static DataSource getSQLLiteDataSource() {
        Properties props = new Properties();
        FileInputStream fis = null;
        SQLiteDataSource datasource = null;
        try {
            fis = new FileInputStream("dbConection.properties");
            props.load(fis);
            datasource = new SQLiteDataSource();
            datasource.setUrl(props.getProperty("SQLLITE_DB_URL"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datasource;
     }
    
}
