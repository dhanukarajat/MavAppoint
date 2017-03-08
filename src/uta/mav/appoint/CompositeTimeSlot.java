package uta.mav.appoint;

import java.util.ArrayList;

public class CompositeTimeSlot extends TimeSlotComponent{
	ArrayList<TimeSlotComponent> children;
	
	public CompositeTimeSlot(){
		super();
		children = new ArrayList<TimeSlotComponent>();
	}
	
	@Override
	public String getName(){
		return children.get(0).getName();
	}
	
	@Override
	public void add(TimeSlotComponent ts){
		children.add(ts);
	}
	
	@Override
	public void remove(TimeSlotComponent ts){
		children.remove(ts);
	}
	
	@Override
	public TimeSlotComponent get(TimeSlotComponent ts){
		return ts;
	}
	
	@Override
	public String getStartTime(){
		return children.get(0).getStartTime();
	}
	
	@Override
	public String getEndTime(){
		return children.get(children.size()-1).getEndTime();
	}
	
	/*
	 * return date of first primitive time slot
	 */
	@Override
	public String getDate(){
		return children.get(0).getDate();
	}
	
	
	@Override
	public String getEvent(int m){
		String cat = "";
		if (m < 1)
			m = 1;
		for (int i=0;i<children.size()+1-m;i=i+m){
			cat += children.get(i).getEvent(0);
			if (i != children.size()-1){
				cat += ",";
			}
			cat += "\n";
		}
		
		return cat;
	}
	
	@Override
	public void refactorTimeSlots(int m){
		children = this.expandTimeSlots(children);
		ArrayList<TimeSlotComponent> ts = null;
		CompositeTimeSlot cts = null;
		for (int i=0;i<children.size();i=i+m){
			for (int j=i;j<i+m;j++){
				cts = new CompositeTimeSlot();
				cts.add(children.get(j));
			}
			ts.add(cts);
		}
		children = ts;
	}
	
	@Override
	public ArrayList<TimeSlotComponent> expandTimeSlots(ArrayList<TimeSlotComponent> ts){
		for (int i=0;i<children.size();i++){
			ts = children.get(i).expandTimeSlots(ts);
		}
		return ts;
	
	}
}
