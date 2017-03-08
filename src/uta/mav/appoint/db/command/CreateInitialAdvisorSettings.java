package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import uta.mav.appoint.beans.CreateAdvisorBean;

public class CreateInitialAdvisorSettings extends SQLCmd{

	int userid;
	String pname;
	String email;
	Boolean b;
	
	public CreateInitialAdvisorSettings(int a,CreateAdvisorBean ca){
		userid = a;
		pname = ca.getPname();
		email = ca.getEmail();
		b = false;
	}
	
	@Override
	public void queryDB() {
		try{
			String command = "INSERT INTO advisor_settings (userid,pname,email,notification) "
								+"values(?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(command);
			statement.setInt(1,userid);
			statement.setString(2,pname);
			statement.setString(3,email);
			statement.setString(4,"Day");
			statement.executeUpdate();
			b = true;
		}
		catch(SQLException sqe){
			System.out.println(sqe.toString());
		}
		
	}

	@Override
	public void processResult() {
		result.add(b);
	}

}
