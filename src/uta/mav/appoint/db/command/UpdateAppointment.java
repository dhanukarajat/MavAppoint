package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import uta.mav.appoint.beans.Appointment;

public class UpdateAppointment extends SQLCmd{
	String description;
	String studentid;
	int id;
	Boolean b = false;
	
	public UpdateAppointment(Appointment a){
		description = a.getDescription();
		studentid = a.getStudentid();
		id = a.getAppointmentId();
	}
	
	public void queryDB(){
		try{
			String command = "UPDATE appointments SET description=?,studentid=? where id=?";
			PreparedStatement statement = conn.prepareStatement(command);
			statement.setString(1, description);
			statement.setString(2, studentid);
			statement.setInt(3, id);
			statement.executeUpdate();
			b=true;
		}
		catch(SQLException sq){
			System.out.println(sq.toString());
		}
	}
	
	public void processResult(){
		result.add(b);
	}

}
