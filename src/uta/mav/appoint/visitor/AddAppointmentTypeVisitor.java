package uta.mav.appoint.visitor;

import java.util.ArrayList;

import uta.mav.appoint.beans.AppointmentType;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.AdvisorUser;

public class AddAppointmentTypeVisitor extends Visitor{
	
	@Override
	public ArrayList<Object> check(AdvisorUser user, Object o){
		ArrayList<Object> array = null;
		String msg = "";
		try{
		DatabaseManager dbm = new DatabaseManager();
		user.setMsg(dbm.addAppointmentType(user, (AppointmentType)o));
		}
		catch(Exception e){
			System.out.printf(e.toString());
		}
		return array;
	}

}
