package model;

import org.apache.mahout.cf.taste.impl.model.jdbc.ConnectionPoolDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MyDataModel {

	public static JDBCDataModel myDataModel() {
        MysqlDataSource dataSource = new MysqlDataSource();
        JDBCDataModel dataModel = null;
        try {
	        dataSource.setServerName("115.159.184.231");
	        dataSource.setUser("test");
	        dataSource.setPassword("test123456");
	        dataSource.setDatabaseName("movie");


	        ConnectionPoolDataSource connectionPool=new ConnectionPoolDataSource(dataSource);
	        dataModel =  new MySQLJDBCDataModel(connectionPool,"movie_preferences", "userID", "movieID","preference",null);


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return dataModel;
    } 

}
