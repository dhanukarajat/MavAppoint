package uta.mav.appoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestIndex {
	IndexServlet servlet;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		servlet = new IndexServlet();
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
