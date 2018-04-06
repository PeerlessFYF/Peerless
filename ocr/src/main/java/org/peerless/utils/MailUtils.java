package org.peerless.utils;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件服务
 * 
 * @author yinfeng.fyf
 * 
 */
public class MailUtils {

	private static final String HOST = "smtp.126.com";
	private static final String PORT = "25";
	private static final String USERNAME = "jimmyrubya@126.com";
	private static final String PASSWORD = "peerless2012";

	private static Session session = null;

	static {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.host", HOST);
		props.setProperty("mail.smtp.port", PORT);
		props.setProperty("mail.smtp.socketFactory.port", PORT);
		props.put("mail.smtp.auth", "true");

		session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});
	}

	/**
	 * 发送邮件
	 * 
	 * @param address
	 *            - 收件人
	 * @param subject
	 *            - 主题
	 * @param text
	 *            - 内容
	 */
	public static void sendMessage(String address, String subject, String text) {
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(USERNAME));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address, false));
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setContent(text, "text/html;charset=UTF-8");
			Transport.send(msg);
		} catch (AddressException e) {
			logger.error(e.getMessage(), e);
		} catch (MessagingException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
