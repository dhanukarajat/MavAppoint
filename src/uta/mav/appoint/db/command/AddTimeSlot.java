package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.helpers.TimeSlotHelpers;


public class AddTimeSlot extends SQLCmd{
	String date;
	String starttime;
	String endtime;
	int userid;
	int count;
	String msg;
	
	
	public AddTimeSlot(AllocateTime at,int id){
		date = at.getDate();
		starttime = at.getStartTime();
		endtime = at.getEndTime();
		userid = id;
		count = TimeSlotHelpers.count(at.getStartTime(),at.getEndTime());
		msg = "Unable to add time slot.";
	}
	
	@Override
	public void queryDB(){
		try{
			String command = "INSERT INTO ADVISING_SCHEDULE (advising_date,advising_starttime,advising_endtime,studentid,userid) VALUES(?,?,?,null,?)";
			PreparedStatement statement = conn.prepareStatement(command);
			for (int i=0;i<count;i++){
				statement.setString(1,date);
				statement.setString(2,TimeSlotHelpers.add(starttime,i));
				statement.setString(3,TimeSlotHelpers.add(starttime,i+1));
				statement.setInt(4,userid);
				statement.executeUpdate();
			}
			msg = "Time slot has been added.";
		}
		catch(Exception e){
			System.out.printf("Add Time Slot error : " + e.toString());
		}
	}
	
	@Override
	public void processResult(){
		try{
			result.add((Object)msg);
		}
		catch(Exception e){
			System.out.printf("Add Time Slot processResult error: " + e.toString());
		}
	}	

}
