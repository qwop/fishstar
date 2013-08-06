package com.practicalspring.mail;

import java.util.Properties;


public class Main {

/*	public static void main(String[] args) {
		*//**
		 * *** CHANGE THESE FOUR VARIABLE VALUES TO REFLECT YOUR ENVIRONMENT
		 * *****
		 *//*
		String user = "qwop"; // Newly created user on JAMES
		String password = "qop"; // user password

		String fromAddress = "qwop@localhost"; // newlycreateduser@localhost
		String toAddress = "tanyuanji@qq.com";
		
		toAddress = "tanyuanji@zte.com.cn";

		// Create a mail session
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "localhost");
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.username", user);
		properties.put("mail.smtp.password", password);
		Session session = Session.getDefaultInstance(properties, null);

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromAddress));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));

			message.setSubject("Email from our JAMES Server");
			message.setText("The content is right ?");
			Transport.send(message);

			System.out.println("Email sent successfully");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}*/
}