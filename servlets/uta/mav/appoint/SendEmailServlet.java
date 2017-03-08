package uta.mav.appoint;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SendEmailServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -969978370422916362L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
		String to = (String)request.getParameter("to");
		String subject = (String)request.getParameter("subject");
		String body = (String)request.getParameter("body");
		String from = "teststudent@mavs.uta.com";
		String pw = "sFre192R!";
		String host = "smtp.gmail.com";
		String port = "465";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host",host);
		properties.put("mail.smtp.user",from);
		properties.put("mail.smtp.password",pw);
		properties.put("mail.smtp.port",port);
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.socketFactory.port","465");
		properties.put("mail.smtp.socketFactory.class",
						"javax.net.ssl.SSLSocketFactory");
		
		
		
		Session session = Session.getDefaultInstance(properties,
				new javax.mail.Authenticator(){
					protected PasswordAuthentication getPasswordAuthentication(){
						return new PasswordAuthentication("maverickappointments@gmail.com","sFre192R!");
					}
		});
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
			javax.mail.Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setText(body);
			message.setSubject(subject);
			Transport.send(message);
			out.write("Message sent successfully.");
		}
		catch(Exception mex){
			mex.printStackTrace();
		}
	}	
}
