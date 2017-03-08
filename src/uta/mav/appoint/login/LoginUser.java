package uta.mav.appoint.login;

import java.util.ArrayList;

import uta.mav.appoint.visitor.Visitor;

public class LoginUser {
	String email;
	String msg;
	
	public LoginUser(){
		email = "";
		msg = "";
	}
	
	public void accept(Visitor v){
		v.check(this);
	}
	
	public String getPname(){
		return "";
	}
	
	public ArrayList<Object> accept(Visitor v, Object o){//allow javabean to be passed in
		return v.check(this,o);
	}
		
	
	public LoginUser(String em) {
		email = em;
	}
	
	/*
	 * @return the header - override in extended classes for proper header display
	 */
	public String getHeader(){
		return "header";
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	public String getMsg() {
		return msg;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setMsg(String s){
		this.msg = s;
	}
}
