package uta.mav.appoint;

import java.io.IOException;
import java.rmi.server.UID;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SendMeetingServlet extends HttpServlet{
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -5280170396166375849L;


	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 
	 * 
	 * Uses form post parameters:
	 * student_email - must be valid email address
	 * advisor_email - must be valid email address
	 * starttime - format is yyyy-MM-ddTHH:mm:ss
	 * end - same as above
	 * 
	 * description and summary can also be used for more details in the meeting
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {  
			String from = "maverickappointments@gmail.com";
			String pw = "gue#212!ns";
			String host = "smtp.gmail.com";
			String port = "465";
			String to = request.getParameter("student_email");
			String advisor_email = request.getParameter("advisor_email");
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");

			//convert start and end times to correct format
			String[] parts = starttime.split("T");
			String[] date = parts[0].split("-");
			String[] start = parts[1].split(":");
			starttime = date[0]+date[1]+date[2]+"T"+start[0]+start[1];
			parts = endtime.split("T");
			String[] end = parts[1].split(":");
			endtime = date[0]+date[1]+date[2]+"T"+end[0]+end[1];

			
			String description = "This is a meeting request from Maverick Appointments. Please do not reply to this email.";
			String summary = "Maverick Appointments Advising Meeting Request";
			UID uid = new UID();
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
							return new PasswordAuthentication("maverickappointments@gmail.com","gue#212!ns");
						}
			});
		
			// Define message   
			MimeMessage message = new MimeMessage(session);  
			message.addHeaderLine("method=REQUEST");  
			message.addHeaderLine("charset=UTF-8");   
			message.addHeaderLine("component=VEVENT");   
			message.setFrom(new InternetAddress(from));   
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(advisor_email));
			message.setSubject("Meeting Request from Maverick Appointments");  
			StringBuffer sb = new StringBuffer();    
			StringBuffer buffer = sb.append(
					"BEGIN:VCALENDAR\r\n"+
							"PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\r\n"+  
							"VERSION:2.0\r\n" +  
							"METHOD:REQUEST\r\n" +
							"BEGIN:VTIMEZONE\r\n"+
								"TZID:Central Standard Time\r\n"+
								"BEGIN:STANDARD\r\n"+
									"DTSTART:16011104T020000\r\n"+
									"RRULE:FREQ=YEARLY;BYDAY=1SU;BYMONTH=11\r\n"+
									"TZOFFSETFROM:-0500\r\n"+
									"TZOFFSETTO:-0600\r\n"+
								"END:STANDARD\r\n"+
								"BEGIN:DAYLIGHT\r\n"+
									"DTSTART:16010311T020000\r\n"+
									"RRULE:FREQ=YEARLY;BYDAY=2SU;BYMONTH=3\r\n"+
									"TZOFFSETFROM:-0600\r\n"+
									"TZOFFSETTO:-0500\r\n"+
								"END:DAYLIGHT\r\n"+
							"END:VTIMEZONE\r\n"+
							"BEGIN:VEVENT\r\n" +  
								"ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:" + to + "\r\n" +   
								"ORGANIZER:MAILTO:" + advisor_email + "\r\n" +   
								"DTSTART;TZID=Central Standard Time:" + starttime + "00\r\n" +   
								"DTEND;TZID=Central Standard Time:" + endtime + "00\r\n" +   
								"LOCATION:Advisor Office\r\n" +  
								"TRANSP:OPAQUE\r\n" +  
								"SEQUENCE:0\r\n" +  
								"UID:"+uid+"\r\n"+ 
								"DTSTAMP:20141118T120102\r\n" +   
								"CATEGORIES:Meeting\r\n" +  
								"DESCRIPTION: " + description + "\r\n" +  
								"SUMMARY: " + summary + "\r\n" +   
								"PRIORITY:1\r\n" +  
								"CLASS:PUBLIC\r\n" +   
								"BEGIN:VALARM\r\n" +   
									"TRIGGER:PT1440M\r\n" +  
									"ACTION:DISPLAY\r\n" +   
									"DESCRIPTION:Reminder\r\n" +  
								"END:VALARM\r\n" +  
							"END:VEVENT\r\n" +
					"END:VCALENDAR");  // Create the message part  
	   
				BodyPart messageBodyPart = new MimeBodyPart();   
	   // Fill the message    
	   messageBodyPart.setHeader("Content-Class", "urn:content-classes:calendarmessage");  
	   messageBodyPart.setHeader("Content-ID","calendar_message");   
	   messageBodyPart.setDataHandler(new DataHandler( new ByteArrayDataSource(buffer.toString(), "text/calendar")));//very important  
	   // Create a Multipart    
	   Multipart multipart = new MimeMultipart("alternative");    
	   // Add part one   
	   multipart.addBodyPart(messageBodyPart);      
	   // Put parts in message  
	   message.setContent(multipart);    
	   // send message   
	   Transport.send(message);  
	  } 
		catch (MessagingException me) {  
	   me.printStackTrace();   
	  } 
		catch (Exception ex) {  
	   ex.printStackTrace();   
	  }   
	}   
	    

}
