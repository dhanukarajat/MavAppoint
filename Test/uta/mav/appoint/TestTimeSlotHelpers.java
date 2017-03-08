package uta.mav.appoint;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uta.mav.appoint.helpers.TimeSlotHelpers;

public class TestTimeSlotHelpers {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCount() {
		String testStartTime = "9:00";
		String testEndTime = "9:15";
		int result = TimeSlotHelpers.count(testStartTime, testEndTime);
		assertEquals(result,3);
		testEndTime = "10:15";
		result = TimeSlotHelpers.count(testStartTime, testEndTime);
		assertEquals(result,15);
		testEndTime = "11:15";
		result =TimeSlotHelpers.count(testStartTime, testEndTime);
		assertEquals(result,27);
	}

	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateCompositeTimeSlot() {
		ArrayList<TimeSlotComponent> array = new ArrayList<TimeSlotComponent>();
		PrimitiveTimeSlot ts = new PrimitiveTimeSlot();
		PrimitiveTimeSlot ts2 = new PrimitiveTimeSlot();
		PrimitiveTimeSlot ts3 = new PrimitiveTimeSlot();
		PrimitiveTimeSlot ts4 = new PrimitiveTimeSlot();
		PrimitiveTimeSlot ts5 = new PrimitiveTimeSlot();
		
		ts.setName("test");
		ts.setStartTime("9:00");
		ts.setEndTime("9:05");
		ts.setUniqueId(1);
		ts.setDate("2014-10-10");
		ts2.setName("test");
		ts2.setStartTime("9:05");
		ts2.setEndTime("9:10");
		ts2.setUniqueId(2);
		ts2.setDate("2014-10-10");
		ts3.setName("test2");
		ts3.setStartTime("9:10");
		ts3.setEndTime("9:15");
		ts3.setUniqueId(3);
		ts3.setDate("2014-10-10");
		ts4.setName("test");
		ts4.setStartTime("10:00");
		ts4.setEndTime("10:05");
		ts4.setUniqueId(4);
		ts4.setDate("2014-10-10");
		ts5.setName("test");
		ts5.setStartTime("10:05");
		ts5.setEndTime("10:10");
		ts5.setUniqueId(5);
		ts5.setDate("2014-10-10");
		
		array.add(ts);
		array.add(ts2);
		array.add(ts3);
		array.add(ts4);
		array.add(ts5);
		
		ArrayList<TimeSlotComponent> array2 = TimeSlotHelpers.createCompositeTimeSlot(array);
		assertEquals(array2.get(0).getStartTime(),"9:00");
		assertEquals(array2.get(0).getEndTime(),"9:10");
		assertEquals(array2.get(1).getStartTime(),"9:10");
		assertEquals(array2.get(1).getEndTime(),"9:15");
		assertEquals(array2.get(2).getStartTime(),"10:00");
		assertEquals(array2.get(2).getEndTime(),"10:10");
		
		
	}

}
