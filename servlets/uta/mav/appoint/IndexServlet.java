package uta.mav.appoint;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.visitor.GetNextAppointmentVisitor;
import uta.mav.appoint.visitor.Visitor;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	HttpSession session;
	private String header;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		if (user != null){
			try{
				header = "templates/" + user.getHeader() + ".jsp";
				String date =  new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				Visitor v = new GetNextAppointmentVisitor();
				ArrayList<Object> appArray = (ArrayList<Object>) (user.accept(v,(Object)date));
				if (appArray != null){
					Appointment app = (Appointment)appArray.get(0);
					session.setAttribute("studentapp",app);
				}
			}
			catch(Exception e){
				System.out.println("Index error : " + e);
			}
		}
		else{
			if (user == null){
				user = new LoginUser();
				session.setAttribute("user", user);
			}
			header = "templates/header.jsp";
		}
		
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/WEB-INF/jsp/views/index.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//nothing posts to index yet
		}
}

	
	