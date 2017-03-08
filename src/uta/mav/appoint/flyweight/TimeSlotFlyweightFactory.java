package uta.mav.appoint.flyweight;

import java.util.Hashtable;

import uta.mav.appoint.PrimitiveTimeSlot;

public class TimeSlotFlyweightFactory {
	private static TimeSlotFlyweightFactory fly;
	private Hashtable<String,Hashtable<String,PrimitiveTimeSlot>> flyweights;
	
	private TimeSlotFlyweightFactory(){
		flyweights = new Hashtable<String,Hashtable<String,PrimitiveTimeSlot>>();
	}
	
	public static TimeSlotFlyweightFactory getInstance(){
		if (fly == null){
			fly = new TimeSlotFlyweightFactory();
		}
		return fly;
	}
	
	/*
	 *key is date, key2 is start time 
	 */
	public PrimitiveTimeSlot getFlyweight(String key, String key2){
		if (flyweights.get(key)==null){
			Hashtable<String,PrimitiveTimeSlot> date= new Hashtable<String,PrimitiveTimeSlot>(); //create blank PrimitiveTimeSlot to be filled in by DBM
			flyweights.put(key, date);
		}
		if(flyweights.get(key).get(key2)==null){
			PrimitiveTimeSlot time = new PrimitiveTimeSlot();
			flyweights.get(key).put(key2, time);
		}
		return flyweights.get(key).get(key2);
	}
}
