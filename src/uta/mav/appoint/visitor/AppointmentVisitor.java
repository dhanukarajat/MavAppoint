package uta.mav.appoint.visitor;

import java.util.ArrayList;

import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.StudentUser;

public class AppointmentVisitor extends Visitor{
	
	@Override
	public ArrayList<Object> check(AdvisorUser user,Object o){
		try{
			DatabaseManager dbm = new DatabaseManager();
			ArrayList<Object> appointments = dbm.getAppointments(user);
			return appointments;
		}
		catch(Exception e){
			return null;
		}
	}
	
	@Override
	public ArrayList<Object> check(StudentUser user,Object o){
		try{
			DatabaseManager dbm = new DatabaseManager();
			ArrayList<Object> appointments = dbm.getAppointments(user);
			return appointments;
		}
		catch(Exception e){
			return null;
		}
	}
}
