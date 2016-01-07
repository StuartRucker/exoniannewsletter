import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

	public static void sendEmail(ArrayList<String> emails) {

		final String username = "stuart.a.rucker@gmail.com";
		final String password = "babyballs";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("stuart.a.rucker@gmail.com"));
			
//			message.setRecipients(Message.RecipientType.TO,
//					InternetAddress.parse("stuart.a.rucker@gmail.com"));
			
			for(String emailAddress: emails){
				try{
					message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse(emailAddress));
				}catch(Exception E){}
			}
			
			
			message.setSubject("Exonian Newsletter");
			try {
				message.setText(JSON.getNews(), "utf-8", "html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}