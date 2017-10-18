package service;

import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Session Bean implementation class SendmailEJB
 */
@Stateful
@LocalBean
public class SendmailEJB implements SendmailEJBRemote {

    /**
     * Default constructor. 
     */
    public SendmailEJB() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public void send(String too,String subject,String txt){
  	
    	final String username = "N.O.societyEsprit@gmail.com";
		final String password = "nosociety";

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

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("N.O.societyEsprit@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(too));
			message.setSubject(subject);
			message.setText(txt);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    	
    	
    	
    }

}
