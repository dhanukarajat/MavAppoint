package uta.mav.appoint.beans;

import java.io.Serializable;

public class AdvisingType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8116210396285144618L;
	private String type;
	private int duration;
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
