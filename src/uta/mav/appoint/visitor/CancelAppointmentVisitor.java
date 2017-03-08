package uta.mav.appoint.visitor;

import java.util.ArrayList;

import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.StudentUser;

public class CancelAppointmentVisitor extends Visitor{

	@Override
	public ArrayList<Object> check(AdvisorUser user,Object o){
		try{
		DatabaseManager dbm = new DatabaseManager();
		int id = (int)o;
		Boolean result = dbm.cancelAppointment(id);
		if (result == true){
			user.setMsg("Appointment has been cancelled.");
		}
		else{
			user.setMsg("Unable to cancel appointment.");
		}
		}
		catch(Exception e){

			user.setMsg("Unable to cancel appointment. Something went wrong...");
		}
		return null;
	}

	@Override
	public ArrayList<Object> check(StudentUser user,Object o){
		try{
		DatabaseManager dbm = new DatabaseManager();
		int id = (int)o;
		Boolean result = dbm.cancelAppointment(id);
		if (result == true){
			user.setMsg("Appointment has been cancelled.");
		}
		else{
			user.setMsg("Unable to cancel appointment.");
		}
		}
		catch(Exception e){
			System.out.println("Error in check: " + e);
			user.setMsg("Unable to cancel appointment. Something went wrong...");
		}
		return null;
	}

}
