package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import uta.mav.appoint.beans.AllocateTime;

public class DeleteTimeSlot extends SQLCmd{
	String date;
	String start;
	String end;
	String pname;
	Boolean b = false;
	
	public DeleteTimeSlot(AllocateTime at){
		date=at.getDate();
		start=at.getStartTime();
		end=at.getEndTime();
		pname=at.getEmail();
	}
	
	public void queryDB(){
		try{
			String command = "DELETE a FROM advising_schedule a JOIN advisor_settings b ON a.userid=b.userid WHERE advising_date=? AND advising_starttime >=? AND advising_endtime <=?"
							+"AND b.pname=?";
			PreparedStatement statement = conn.prepareStatement(command);
			statement.setString(1,date);
			statement.setString(2,start);
			statement.setString(3,end);
			statement.setString(4,pname);
			statement.executeUpdate();
			b = true;
		}
		catch(SQLException sqe){
			System.out.println(sqe.toString());
		}
	}
	
	public void processResult(){
		result.add(b);
	}

}
