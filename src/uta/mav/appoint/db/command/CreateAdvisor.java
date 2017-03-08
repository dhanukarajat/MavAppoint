package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;

import uta.mav.appoint.beans.CreateAdvisorBean;

public class CreateAdvisor extends SQLCmd{
	
	String email;
	String password;
	String role;
	String pname;
	Boolean b;
	
	public CreateAdvisor(CreateAdvisorBean ca){
		email = ca.getEmail();
		password = ca.getPassword();
		pname = ca.getPname();
		b = false;
	}
	
	@Override
	public void queryDB() {
		try{
			String command = "INSERT INTO user (email,password,role,validated)"
							+" values(?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(command);
			statement.setString(1,email);
			statement.setString(2,"newadvisor!@3");
			statement.setString(3,"advisor");
			statement.setInt(4,1);
			statement.executeUpdate();
			b = true;
			}
		catch(Exception e){
			System.out.println(e);
		}
	}

	@Override
	public void processResult() {
		result.add(b);	
	}

		
}
