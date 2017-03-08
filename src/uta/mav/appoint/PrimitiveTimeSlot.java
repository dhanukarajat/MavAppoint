package uta.mav.appoint;

import java.util.ArrayList;

public class PrimitiveTimeSlot extends TimeSlotComponent{
	private String name;
	private String date;
	private String starttime;
	private String endtime;
	private int uniqueid;
	
	
	@Override
	public String getEvent(int m){
	return "{\n"
			+	"title:\'Available\',\n"
			+	"start:\'"+this.getDate()+"T"+this.getStartTime()+"\',\n"
			+	"end:\'"+this.getDate()+"T"+this.getEndTime()+"\',\n"
			+	"id:"+this.getUniqueId()+",\n"
			+"}\n";
	}
	
	@Override
	public ArrayList<TimeSlotComponent> expandTimeSlots(ArrayList<TimeSlotComponent> ts){
		ts.add(this);
		return ts;
	
	}
	
	@Override
	public String getName(){
		return this.name;
	}
	
	@Override 
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public String getDate() {
		return date;
	}
	
	@Override
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the starttime
	 */
	@Override
	public String getStartTime() {
		return starttime;
	}
	
	@Override
	public void setStartTime(String starttime) {
		this.starttime = starttime;
	}
	
	@Override
	public String getEndTime() {
		return endtime;
	}
	
	@Override
	public void setEndTime(String endtime) {
		this.endtime = endtime;
	}
	
	
	@Override
	public int getUniqueId() {
		return uniqueid;
	}
	
	@Override
	public void setUniqueId(int uniqueid) {
		this.uniqueid = uniqueid;
	}
	
}
