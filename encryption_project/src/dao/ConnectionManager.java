package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

/*
 * 	this class provide the database connection properties with useing DataSource
 * 
 * */

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionManager {


	public DataSource getMySqlDatasource() {
		
		Properties properties = new Properties();
		// properties dosyamýz src nýn altýnda oldugu için bu sekilde onu tanýmlayýp okuyabiliyoruz.
		InputStream is =  getClass().getClassLoader().getResourceAsStream("db_connection.properties");
		
		MysqlDataSource mySQLDataSource = null;
		try {
			
			properties.load(is);
			
			mySQLDataSource = new MysqlDataSource();
			mySQLDataSource.setUrl(properties.getProperty("url"));
			mySQLDataSource.setUser(properties.getProperty("username"));
			mySQLDataSource.setPassword(properties.getProperty("password"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mySQLDataSource;
		
	}
}
