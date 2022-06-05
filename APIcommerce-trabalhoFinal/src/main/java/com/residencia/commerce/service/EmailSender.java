package com.residencia.commerce.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.residencia.commerce.entity.Pedido;

@Component
public class EmailSender {

	@Autowired
	private JavaMailSender javaMailSender;

	public static int noOfQuickServiceThreads = 20;

	private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);

	public void send(Pedido pedido) throws MailException, MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

		helper.setSubject("Informacoes sobre pedido: " + pedido.getIdPedido());

		quickService.submit(new Runnable() {

			@Override
			public void run() {

				try {
					helper.setText(pedido.toString());
					helper.setTo(pedido.getCliente().getEmailCliente());

					javaMailSender.send(mimeMessage);

				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}

		});

	}
}
