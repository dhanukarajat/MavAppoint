package uta.mav.appoint.beans;

import java.io.Serializable;

public class CustomizeSettings implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8844414325171698965L;
	private String pName;
	private String email;
	private String notification;
	/**
	 * @return the pName
	 */
	public String getpName() {
		return pName;
	}
	/**
	 * @param pName the pName to set
	 */
	public void setpName(String pName) {
		this.pName = pName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the notification
	 */
	public String getNotification() {
		return notification;
	}
	/**
	 * @param notification the notification to set
	 */
	public void setNotification(String notification) {
		this.notification = notification;
	}

}
