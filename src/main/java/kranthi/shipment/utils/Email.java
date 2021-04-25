package kranthi.shipment.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class Email {
	
	@Autowired
	private JavaMailSender sender;
	
	public boolean sendEmail(
			String to,
			String subject,
			String text,
			String cc
			) {
		
		boolean issent =false;
		
		// 1. create one empty message using mimemessage.
		MimeMessage message =sender.createMimeMessage();
		
		//2. fill details.
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
	    try {
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			
		}
	    catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//3. Send message using Mime message object variable.
		sender.send(message);
		return issent;
		
	}
	
	public boolean sendEmail(String to,String subject,String text)
	{
		return sendEmail(to,subject,text,null);
	}

}
