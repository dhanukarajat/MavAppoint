package uta.mav.appoint.beans;

import java.io.Serializable;

public class AdvisingSchedule implements Serializable{

	/**
	 * JavaBean for advising_schedule table in mavappointDB
	 */
	private static final long serialVersionUID = 4621256166700915638L;
	private String name;
	private String date;
	private String starttime;
	private String endtime;
	private int uniqueid;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the starttime
	 */
	public String getStarttime() {
		return starttime;
	}
	/**
	 * @param starttime the starttime to set
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	/**
	 * @return the endtime
	 */
	public String getEndtime() {
		return endtime;
	}
	/**
	 * @param endtime the endtime to set
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the uniqueid
	 */
	public int getUniqueid() {
		return uniqueid;
	}
	/**
	 * @param uniqueid the uniqueid to set
	 */
	public void setUniqueid(int uniqueid) {
		this.uniqueid = uniqueid;
	}
}
