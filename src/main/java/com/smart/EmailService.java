package com.smart;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${spring.mail.password}")
    private String appPassword;

    public boolean sendEmail(String subject, String message, String to) {

    	boolean f = false;
    	
        String from = fromEmail;
        String host = "smtp.gmail.com";

        // Set properties
        Properties properties = new Properties();
        
        
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // use TLS


        // Create session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, appPassword);
            }
        });

        session.setDebug(true);

        try {
            // Create message
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject);
           // m.setText(message);
            m.setContent(message, "text/html");

            // Send mail
            Transport.send(m);
            System.out.println("Email sent successfully...");
            f=true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
    
    
}





//package com.smart;
//
//import java.net.Authenticator;
//import java.net.PasswordAuthentication;
//import java.util.Properties;
//
//import javax.mail.Message;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import org.springframework.stereotype.Service;
//
//
//
//
//
//@Service 
//public class EmailService {
//	
//	public void sendEmail(String subject, String message, String to)
//	{
//		
//		//rest of the code..
//		String from="techsoftindia2018@gmail.com";
//		
//		//variable for gmail
//		String host = "smtp.gmail.com";
//		
//		//get the system properties
//		Properties properties = new Properties();
//		System.out.println("PROPERTIES "+properties);
//		
//		//setting important information to properties object
//		
//		//host set
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.port", "465");
//		properties.put("mail.smtp.ssl.enable", "true");
//		properties.put("mail.smtp.auth", "true"); 
//		
//		//Step 1: To get the session object..
//		
//		Session session=Session.getInstance(properties, new Authenticator() {
//			
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("techsoftindia2018@gmail.com", "");
//			}
//			
//		});
//		session.setDebug(true);
//		
//		// Step 2: compose the message[text, multi media]
//		MimeMessage m = new MimeMessage(session);
//		
//		try {
//			
//			//from email
//			m.setFrom(from);
//			
//			//adding recipient to message
//			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//			
//			//adding subject to message
//			m.setSubject(subject);
//			
//			//adding text to message
//			m.setText(message);
//			
//			//send
//			
//			//Step 3: send the message using Transport class
//			Transport.send(m);
//			
//			System.out.println("Sent success.............");
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
// 		
//	}
//
//}