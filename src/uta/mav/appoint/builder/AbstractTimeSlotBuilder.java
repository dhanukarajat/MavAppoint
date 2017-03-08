package uta.mav.appoint.builder;

import java.util.ArrayList;

import uta.mav.appoint.TimeSlotComponent;

public interface AbstractTimeSlotBuilder {
	TimeSlotComponent createCompositeTimeSlot(ArrayList<TimeSlotComponent> ts);
}
