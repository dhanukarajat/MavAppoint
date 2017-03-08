package uta.mav.appoint;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.visitor.AppointmentVisitor;
import uta.mav.appoint.visitor.Visitor;

/**
 * Servlet implementation class AdvisingServlet
 */
public class ManageAppointmentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	HttpSession session;
	String header;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		if (user != null){
			try{
					header = "templates/" + user.getHeader() + ".jsp";
					//must be logged in to see advisor schedules - safety concern
					DatabaseManager dbm = new DatabaseManager();
					ArrayList<String> array =  dbm.getAdvisors();
					if (array.size() != 0){
						session.setAttribute("advisors", array);
					}
					else{
						//no advisors for department?
					}
					ArrayList<TimeSlotComponent> schedules = dbm.getAdvisorSchedule("all");
					if (schedules.size() != 0){
						session.setAttribute("schedules", schedules);
					}
					Visitor v = new AppointmentVisitor();
					ArrayList<Object> appointments = user.accept(v,null);
					if (appointments.size() != 0){
						session.setAttribute("appointments", appointments);
					}
			}
			catch(Exception e){
				
			}
		}
		else{
			header = "templates/header.jsp";
		}
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/WEB-INF/jsp/views/advising.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		if (user != null){
			try{
					header = "templates/" + user.getHeader() + ".jsp";
					Appointment a = new Appointment();
					a.setDescription(request.getParameter("description"));
					a.setStudentid(request.getParameter("studentid"));
					a.setAppointmentId(Integer.parseInt(request.getParameter("id2")));
					DatabaseManager dbm = new DatabaseManager();
					Boolean result = dbm.updateAppointment(a);
					if (result == true){
						response.setHeader("Refresh","2; URL=appointments");
						request.getRequestDispatcher("/WEB-INF/jsp/views/success.jsp").forward(request,response);
					}
					else{
						response.setHeader("Refresh","2; URL=appointments");
						request.getRequestDispatcher("/WEB-INF/jsp/views/failure.jsp").forward(request,response);
					}
				}
			catch(Exception e){
				System.out.printf("Error in ManageAppointment Post : " + e.toString());
			}
		}
		else{
			header = "templates/header.jsp";
		}
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/WEB-INF/jsp/views/advising.jsp").forward(request, response);
	}
}
