package uta.mav.appoint.visitor;

import java.util.ArrayList;

import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.AdvisorUser;

public class ManageTimeSlotVisitor extends Visitor{
	
	@Override
	public ArrayList<Object> check(AdvisorUser user,Object at){
		try{
			AllocateTime a = (AllocateTime)at;
			DatabaseManager dbm = new DatabaseManager();
			dbm.deleteTimeSlot(a);
			user.setMsg("Time slot deleted.");
		}
		catch(Exception e){
			user.setMsg("Unable to delete time slot.");
		}
		return null;
	}
}
