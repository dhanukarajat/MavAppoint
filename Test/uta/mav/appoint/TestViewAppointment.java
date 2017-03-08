package uta.mav.appoint;

import static org.junit.Assert.fail;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestViewAppointment {
	private ViewAppointmentServlet servlet; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		servlet = new ViewAppointmentServlet();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() {
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {
	}
}
