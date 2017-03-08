package uta.mav.appoint.helpers;

import java.util.ArrayList;

import uta.mav.appoint.CompositeTimeSlot;
import uta.mav.appoint.TimeSlotComponent;

public class TimeSlotHelpers {

	public static int count(String startTime, String endTime){
		int count = 0;
		String[] start = startTime.split(":");
		String[] end = endTime.split(":");
		
		int st_h = Integer.parseInt(start[0]);
		int st_m = Integer.parseInt(start[1]);
		int et_h = Integer.parseInt(end[0]);
		int et_m = Integer.parseInt(end[1]);
		
		if (st_h == et_h){
			for(int j=st_m;j<et_m;j=j+5){
					count += 1;
				}
			return count;
		}
		for (int i=st_h;i<=et_h;i++){
			if (i == et_h){
				for(int j=0;j<et_m;j=j+5){
					count += 1;
				}
			}
			else if (i != et_h && count != 0){
				for (int j=0;j<60;j=j+5){
					count += 1;
				}
			}
			else{
				for (int j=st_m;j<60;j=j+5){
					count += 1;
				}
			}
		}
		return count;
	}

	public static String add(String time, int amount){
		String newString;
		String[] start = time.split(":");		
		int st_h = Integer.parseInt(start[0]);
		int st_m = Integer.parseInt(start[1]);
		
		st_m = st_m + (5*amount);
		while (st_m >= 60){
			st_m = st_m -60;
			st_h++;
		}
		newString = st_h+":"+st_m;
		return newString;
	}
	
	public static String addDate(String date, int amount){
		String newString;
		String[] start = date.split("-");		
		int st_y = Integer.parseInt(start[0]);
		int st_m = Integer.parseInt(start[1]);
		int st_d = Integer.parseInt(start[2]);
		Boolean leapyear = false;
		int count = 7*amount;
		if ((st_y % 4 == 0)&&!((st_y % 100 == 0)&&!(st_y % 400 == 0))){
			leapyear = true;
		}
		while (count > 0){
			while ((st_m==2)&&count>0){
				if(st_d == 28&&!leapyear){
					st_d = 1;
					st_m++;
					count--;
					break;
				}
				else if(st_d == 29&&leapyear){
					st_d = 1;
					st_m++;
					count--;
					break;
				}
				else{
					st_d++;
					count--;
				}
			}
			while ((st_m==1||st_m==3||st_m==5||st_m==7||st_m==8||st_m==10)&&count>0){
				if (st_d == 31){
					st_d = 1;
					st_m++;
					count--;
					break;
				}
				else{
					st_d++;
					count--;
				}
			}
			while ((st_m==4||st_m==6||st_m==9||st_m==11)&&count>0){
				if (st_d == 30){
					st_d = 1;
					st_m++;
					count--;
					break;
				}
				else{
					st_d++;
					count--;
				}
			}
			while (st_m==12&&count>0){
				if (st_d == 31){
					st_d = 1;
					st_m = 1;
					st_y++;
					count--;
					break;
				}
				else{
					st_d++;
					count--;
				}
			}
		}
		newString = st_y+"-"+st_m+"-"+st_d;
		return newString;
	}
	
	//public static ArrayList<TimeSlotComponent> sortTimeSlots(ArrayList<TimeSlotComponent>)

	public static ArrayList<TimeSlotComponent> createCompositeTimeSlot(ArrayList<TimeSlotComponent> array){
		//check if only one element in array
		if (array.size() == 1){
			return array;
		}
		Boolean result = false;
		ArrayList<TimeSlotComponent> fin = array;
		while (!result){
			result = true;
			for (int i=0;i<fin.size()-1;i++){
				int k=i+1;
				if (fin.get(i).getEndTime().equals(fin.get(k).getStartTime())&&fin.get(i).getName().equals(fin.get(i+1).getName())){
					result = false;
					CompositeTimeSlot cts = new CompositeTimeSlot();
					cts.add(fin.get(i));
					int h=k;
					while(cts.getEndTime().equals(fin.get(k).getStartTime())&&fin.get(i).getName().equals(fin.get(i+1).getName())){
						if (fin.get(i).getName().equals(fin.get(k).getName())){ //don't group different user slots together
							cts.add(fin.get(k));
							h++;
						}
						if (++k == fin.size()) //prevent out of bounds exception
							break;
					}
					for (int j=i+1;j<h;j++){
						fin.remove(i+1);
					}
					fin.set(i, cts);
				}
			}
		}
		
		return fin;
	}
}
