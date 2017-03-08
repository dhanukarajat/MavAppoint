package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;

import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.helpers.TimeSlotHelpers;

public class CheckTimeSlot extends SQLCmd{
	
	String date;
	String starttime;
	String endtime;
	int userid;
	int count;
	
	public CheckTimeSlot(AllocateTime at,int id){
		date = at.getDate();
		starttime = at.getStartTime();
		endtime = at.getEndTime();
		userid = id;
		count = TimeSlotHelpers.count(at.getStartTime(),at.getEndTime());
	}

	public void queryDB(){
	try{
		String command = "SELECT COUNT(*) FROM  ADVISING_SCHEDULE WHERE advising_date=? AND advising_starttime >=? AND advising_endtime <=? AND userid=?";
		PreparedStatement statement = conn.prepareStatement(command);
		statement.setString(1,date);
		statement.setString(2,starttime);
		statement.setString(3,endtime);
		statement.setInt(4,userid);
		res = statement.executeQuery();
	}
	catch(Exception e){
		System.out.printf("Check Time Slot error : " + e.toString());
	}
	}
	
	public void processResult(){
		Boolean b;
		try{
		while(res.next()){
			if (res.getInt(1)>=1){
				b = false;
				result.add((Object)b);
			}
			else{
				b = true;
				result.add((Object)b);
			}
		}
		}
		catch(Exception e){
			System.out.printf("Check Time Slot processResult error : " + e.toString() + "\n");
		}
	}
}
