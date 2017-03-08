package uta.mav.appoint.visitor;

import java.util.ArrayList;

import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.StudentUser;

public class GetNextAppointmentVisitor extends Visitor{

	@Override
	public ArrayList<Object> check(StudentUser user,Object o){
		ArrayList<Object> array = new ArrayList<Object>();
		try{
			DatabaseManager dbm = new DatabaseManager();
			Appointment appointment = dbm.getAppointment((String)o,user.getEmail());
			array.add((Object)appointment);
			return array;
		}
		catch(Exception e){
			System.out.println("Error in check: " + e);
			return null;
		}	
	}
}
