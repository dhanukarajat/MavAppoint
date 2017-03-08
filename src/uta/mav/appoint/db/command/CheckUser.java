package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import uta.mav.appoint.login.AdminUser;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.FacultyUser;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.login.StudentUser;

public class CheckUser extends SQLCmd{
	String email;
	String password;
	String pname;
	
	public CheckUser(String e, String p){
		email = e;
		password = p;
	}
	
	@Override
	public void queryDB(){
		try{
			String command = "SELECT COUNT(*),ROLE,pname FROM USER,advisor_settings WHERE user.userid=advisor_settings.userid AND user.EMAIL=? AND user.PASSWORD=?";
			PreparedStatement statement = conn.prepareStatement(command); 
			statement.setString(1,email);
			statement.setString(2,password);
			res = statement.executeQuery();
			while(res.next()){
				pname = res.getString(3);
			}
			command = "SELECT COUNT(*),ROLE,pname FROM USER,advisor_settings WHERE user.EMAIL=? AND user.PASSWORD=?";
			statement = conn.prepareStatement(command); 
			statement.setString(1,email);
			statement.setString(2,password);
			res = statement.executeQuery();
			}
		catch (Exception e){
			System.out.println(e);	
		}
		
	}
	
	@Override
	public void processResult(){
		LoginUser user = null;
		try{
			while(res.next()){
				if (!(res.getInt(1) == 0)){
					if (res.getString(2).toLowerCase().equals("advisor")){
						user = new AdvisorUser(email,pname);
					}
					else if (res.getString(2).toLowerCase().equals("student")){
						user = new StudentUser(email);
					}
					else if (res.getString(2).toLowerCase().equals("admin")){
						user = new AdminUser(email);
					} 
					else {
						user = new FacultyUser(email);
					}
				}	
			}
			result.add(user);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}	
}
