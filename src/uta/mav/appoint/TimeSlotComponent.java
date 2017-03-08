package uta.mav.appoint;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class TimeSlotComponent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6887419263982982266L;
	public void add(TimeSlotComponent ts){}
	public void remove(TimeSlotComponent ts){}
	public TimeSlotComponent get(TimeSlotComponent ts){return null;}
	public void setStartTime(String starttime){};
	public void setEndTime(String endtime){};
	public void setDate(String date){};
	public void setUniqueId(int id){};
	public int getUniqueId(){return -1;};
	public String getName(){return null;};
	public void refactorTimeSlots(int m){}
	public void setName(String name){};
	public abstract String getStartTime();
	public abstract String getEndTime();
	public abstract String getDate();
	public abstract String getEvent(int m);
	public abstract ArrayList<TimeSlotComponent> expandTimeSlots(ArrayList<TimeSlotComponent> ts);
	
	
}
