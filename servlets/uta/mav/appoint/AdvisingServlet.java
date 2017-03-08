package uta.mav.appoint;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.LoginUser;

/**
 * Servlet implementation class AdvisingServlet
 */
@WebServlet("/AdvisingServlet")
public class AdvisingServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	HttpSession session;
	String header;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		if (user == null){
			user = new LoginUser();
			session.setAttribute("user", user);
		}
		try{
				header = "templates/" + user.getHeader() + ".jsp";
				//must be logged in to see advisor schedules - safety concern
				DatabaseManager dbm = new DatabaseManager();
				ArrayList<String> array =  dbm.getAdvisors();
				if (array.size() != 0){
					session.setAttribute("advisors", array);
				}
				ArrayList<TimeSlotComponent> schedules = dbm.getAdvisorSchedule("all");
				if (schedules.size() != 0){
					session.setAttribute("schedules", schedules);
				}
				ArrayList<Object> appointments = dbm.getAppointments(user);
				if (appointments.size() != 0){
					session.setAttribute("appointments", appointments);
				}
		}
		catch(Exception e){
			
		}
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/WEB-INF/jsp/views/advising.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		if (user == null){
			user = new LoginUser();
		}
		try{
					header = "templates/" + user.getHeader() + ".jsp";
					DatabaseManager dbm = new DatabaseManager();
					ArrayList<String> array =  dbm.getAdvisors();
					if (array.size() != 0){
						session.setAttribute("advisors", array);
					}					
					//get advisor schedules
					String advisor = (String)request.getParameter("advisor_button");
					ArrayList<TimeSlotComponent> schedule;
					if (advisor != null){
						schedule = dbm.getAdvisorSchedule(advisor);
					}
					else{
						schedule = dbm.getAdvisorSchedule("all");
					}
					if (schedule.size() != 0){
						session.setAttribute("schedules", schedule);
					}
		}
		catch(Exception e){
			System.out.printf(e.toString());
		}
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/WEB-INF/jsp/views/advising.jsp").forward(request, response);
	}
}
