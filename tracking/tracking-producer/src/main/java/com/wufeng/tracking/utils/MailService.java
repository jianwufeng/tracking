package com.wufeng.tracking.utils;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
* <p>Title: SendMailService.java</p>
* <p>Description: </p>
* @author yurui.zeng
* @date 2014年3月21日下午2:37:02
* @version 1.0
*/
public class MailService
{
	private MailInfo mailInfor = null;
	private static ExecutorService threadpool = null;
	
	public static ExecutorService getInstance()
	{
		if (null == threadpool)
		{
			synchronized (MailService.class)
			{
				try
				{
					if (null == threadpool)
					{
						threadpool = Executors.newFixedThreadPool(10);
					}
				} catch (Exception e)
				{
					threadpool = null;
					
				}
			}
		}
		return threadpool;
	}
	
	
	public MailService(String mailList, String subject, String body)
	{
		mailInfor = new MailInfo();
		mailInfor.setHost("smtp.meitunmama.com");
		mailInfor.setSendEmail("bigdata-monitor");
		mailInfor.setSendPassword("meitunmama123"); 
		mailInfor.setSendName("monitor_bigdata@meitunmama.com"); 
		mailInfor.setMail_to(mailList);
		mailInfor.setMail_subject(subject);
		mailInfor.setMail_body(body);
	}

	public static void submit(final String mailList, final String subject, final String body) throws Exception
	{
		
		Runnable runjob = new Runnable() {
			public void run()
			{
				try
				{
					new MailService(mailList, subject, body).send();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		};

		MailService.getInstance().submit(runjob);
		
	}
	private void send() throws Exception
	{

		Properties props = new Properties(); 

		props.put("mail.smtp.host", mailInfor.getHost()); 
		props.put("mail.smtp.auth", "true"); 

		Session session = Session.getDefaultInstance(props); 
		session.setDebug(false); 

		MimeMessage message = new MimeMessage(session); 
		try {
			message.setFrom(new InternetAddress("monitor_bigdata@meitunmama.com"));
			InternetAddress[] iaToList = new InternetAddress().parse(mailInfor.getMail_to()); 
			message.setRecipients(Message.RecipientType.TO, iaToList);
			message.setSubject(mailInfor.getMail_subject());
			message.setContent(mailInfor.getMail_body(), "text/html;charset = gbk");
			message.saveChanges(); 
			Transport transport = session.getTransport("smtp"); 
			transport.connect(mailInfor.getHost(), mailInfor.getSendName(), mailInfor.getSendPassword()); 
			transport.sendMessage(message, message.getAllRecipients()); 
			transport.close(); 
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} 
	}


	public static void main(String args[])
	{
		String toList = "bigdata@meitunmama.com";
		String subject = "java mail test!";
		String body = "Hello world!!!";
		try {
			MailService.submit("bigdata@meitunmama.com","ubt-common",body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("done");

	}
}
