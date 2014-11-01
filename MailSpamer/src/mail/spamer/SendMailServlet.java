package mail.spamer;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Servlet used for sending mail to website owner", urlPatterns = { "/SendMailServlet" })
public class SendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SendMailServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String iterations = request.getParameter("mailCount");
		
		int N = Integer.parseInt(iterations);
		
		if(N > 100) {N = 100;}
		if(N < 1)	{N = 1;}
		
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
			//String fromEmail = request.getParameter("email");
			String fromEmail = "nekad.nikolovski@db.com";
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mailSpamer10k@gmail.com"));
					
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(fromEmail));
			
			
			
			message.setSubject(subject);
			message.setReplyTo(new javax.mail.Address[]	{
					new javax.mail.internet.InternetAddress("mailSpamer10k@gmail.com")
			});
						
			sb.append("Auto generated message\n\n");
			sb.append(content + "\n\n");
			sb.append("Iteration: " + i + "\n");
			
			message.setText(sb.toString());
 
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		}
		response.sendRedirect("success.html");		
	}
}