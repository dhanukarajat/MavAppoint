package uta.mav.appoint.db.command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
/*
 * SQLCmd -> implements command and template patterns
 */
public abstract class SQLCmd {
	ArrayList<Object> result = new ArrayList<Object>();
	ResultSet res;
	Connection conn;
	public ArrayList<Object> getResult(){return result;};
	public abstract void queryDB();
	public abstract void processResult();
	
	public void execute(){
		try{
			connectDB();
			queryDB();
			processResult();
			disconnectDB(); 
		}
		catch(Exception e){
			disconnectDB();
		}
	}
	
	public void connectDB(){
		try
	    {
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	    String jdbcUrl = "jdbc:mysql://localhost:3306/MavAppointDB";
	    String userid = "team3";
	    String password = "er1ja@18xs@33";
	    conn = DriverManager.getConnection(jdbcUrl,userid,password);
	    }
	    catch (Exception e){
	        System.out.println(e.toString());
	    }
	}
	
	public void disconnectDB(){
		try{
			conn.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
