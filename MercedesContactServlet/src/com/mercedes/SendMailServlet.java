package com.mercedes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet(description = "Servlet used for sending mail to website owner", urlPatterns = { "/SendMailServlet" })
public class SendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SendMailServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String iterations = request.getParameter("mailCount");
		
		int N = Integer.parseInt(iterations);
		
		for(int i=0; i<N; i++) {
		
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("mailSpamer10k","altrixkk	");
			}
		});
		
		try {
			
			StringBuilder sb = new StringBuilder();
			//String author = request.getParameter("author");
			String fromEmail = request.getParameter("email");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mailSpamer10k@gmail.com"));
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(fromEmail));
			//message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("milanbojovic@gmail.com"));		
			message.setSubject("Auto Generated Message");
			message.setReplyTo(new javax.mail.Address[]	{
					new javax.mail.internet.InternetAddress(fromEmail)
			});
			
			sb.append("Mercedes Servis KM - Automatski generisana poruka\n\n");
			sb.append("Posaljilac:  NN \n\n");
			sb.append("Email: bla bla bla  \n\n");
			sb.append("Naslov: " + subject + "\n\n");
			sb.append("Poruka:\n" + content + "\n\n");
			
			sb.append("Mercedes Servis Milan\n");
			sb.append("Kolonija bb 62c Beograd-Zemun\n");
			sb.append("Web: www.kamion-servis.com\n");
			sb.append("Tel/Fax: 011/712-91-63\n");
			sb.append("Mob:     063/59-73-41\n");
			
			sb.append("Iteration: " + i + "\n");
			
			message.setText(sb.toString());
 
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		}
		response.sendRedirect("http://www.kamion-servis.com/mercedes-servis-kontakt-uspeh.html");		
	}
}