package uta.mav.appoint;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.AppointmentType;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.visitor.AddAppointmentTypeVisitor;
import uta.mav.appoint.visitor.Visitor;

public class AddAppointmentServlet extends HttpServlet {
		private static final long serialVersionUID = 2L;
		HttpSession session;
		String header;
		
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			session = request.getSession();
			LoginUser user = (LoginUser)session.getAttribute("user");
			if (user == null){
				user = new LoginUser();
				session.setAttribute("user", user);
				response.sendRedirect("/WEB-INF/jsp/views/login.jsp");
			}
				try{
						header = "templates/" + user.getHeader() + ".jsp";
						AppointmentType at = new AppointmentType();
						at.setType(request.getParameter("apptypes"));
						at.setDuration(Integer.parseInt(request.getParameter("minutes")));
						Visitor v = new AddAppointmentTypeVisitor();
						user.accept(v,(Object)at);
						String msg = user.getMsg();
						response.setContentType("text/plain");
						response.setHeader("Cache-Control", "no-cache");
						response.setHeader("Pragma", "no-cache");
						response.setCharacterEncoding("UTF-8");
						PrintWriter out = response.getWriter();
						out.write(msg);
						out.flush();
						out.close();
				}
				catch(Exception e){
					System.out.printf(e.toString());
				}
			request.setAttribute("includeHeader", header);
			request.getRequestDispatcher("/WEB-INF/jsp/views/customize.jsp").forward(request,response);
		}

}
