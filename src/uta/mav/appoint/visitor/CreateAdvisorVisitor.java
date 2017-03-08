package uta.mav.appoint.visitor;

import java.util.ArrayList;

import uta.mav.appoint.beans.CreateAdvisorBean;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.AdminUser;

public class CreateAdvisorVisitor extends Visitor{

	@Override
	public ArrayList<Object> check(AdminUser user,Object at){
		try{
			CreateAdvisorBean ca = (CreateAdvisorBean)at; //cast javabean
			DatabaseManager dbm = new DatabaseManager();
			Boolean result = dbm.createAdvisor(ca);
			if (result == true){
				user.setMsg("Advisor account created.");
			}
			else{
				user.setMsg("Error: Cannot create account.");
			}
		}
		catch(Exception e){
			user.setMsg("Unable to create advisor..");
		}
		return null;
	}
}
