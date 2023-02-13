package com.reccos.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String user_mail;

	public String sendEmail(String recipient, String title, String message) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			
			simpleMailMessage.setFrom(user_mail);
			simpleMailMessage.setTo(recipient);
			simpleMailMessage.setSubject(title);
			simpleMailMessage.setText(message);
			javaMailSender.send(simpleMailMessage);
			
			return "Enviado enviado com sucesso!";
		} catch (Exception ex) {
			return "Erro ao enviar o e-mail";
		}
	}
}
