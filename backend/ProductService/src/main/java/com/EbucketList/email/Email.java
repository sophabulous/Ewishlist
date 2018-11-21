package com.EbucketList.email;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Email {

	private static final String FROM = "spacewhales302@gmail.com";
	private static final String PASSWORD = "proj_SpaceWhales";

	public static void sendEmail(String to, String subject, String msg){    

		//Get properties object    
		Properties props = new Properties();    
		props.put("mail.smtp.host", "smtp.gmail.com");   
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");    
		props.put("mail.smtp.port", "587"); 

		//get Session   
		Session session = Session.getInstance(props,    
				new javax.mail.Authenticator() {    
			protected PasswordAuthentication getPasswordAuthentication() {    
				return new PasswordAuthentication(FROM, PASSWORD);  
			}    
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(msg);

			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public static void main(String[] args){
		String to = "spacewhales302@gmail.com";
		String subject = "Testing JavaMail";
		String msg = "Email msg";
		sendEmail(to, subject, msg);
	}

}
