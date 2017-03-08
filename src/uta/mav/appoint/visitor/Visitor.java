package uta.mav.appoint.visitor;

import java.util.ArrayList;

import uta.mav.appoint.login.AdminUser;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.FacultyUser;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.login.StudentUser;

public class Visitor {
	public void check(AdvisorUser user){}
	public void check(StudentUser user){}
	public void check(FacultyUser user){}
	public void check(AdminUser user){}
	public void check(LoginUser user){}
	public ArrayList<Object> check(AdvisorUser user,Object o){return null;}
	public ArrayList<Object> check(StudentUser user,Object o){return null;}
	public ArrayList<Object> check(FacultyUser user,Object o){return null;}
	public ArrayList<Object> check(AdminUser user,Object o){return null;}
	public ArrayList<Object> check(LoginUser user,Object o){return null;}
		
}
