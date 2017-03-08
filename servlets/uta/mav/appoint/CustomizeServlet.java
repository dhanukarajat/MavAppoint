package uta.mav.appoint;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.AppointmentType;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.LoginUser;

/**
 * Servlet implementation class CustomizeServlet
 */
public class CustomizeServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
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
					DatabaseManager dbm = new DatabaseManager();
					ArrayList<AppointmentType> ats = dbm.getAppointmentTypes(user.getPname());
					session.setAttribute("appointmenttypes", ats);	
			}
			catch(Exception e){
				System.out.printf(e.toString());
			}
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/WEB-INF/jsp/views/customize.jsp").forward(request,response);
	}

}
