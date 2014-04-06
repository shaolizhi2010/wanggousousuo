package com.mail;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

 
public class POP3ReceiveMailTest {
	
	public static void main(String[] args) throws Exception {
		receive();
	}
	
	/**
	 * 接收邮件
	 */
	public static void receive() throws Exception {
		// 准备连接服务器的会话信息
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "pop3");		// 协议
		props.setProperty("mail.pop3.port", "110");				// 端口
		props.setProperty("mail.pop3.host", "pop.163.com");	// pop3服务器
		
		// 创建Session实例对象
		Session session = Session.getInstance(props);
		session.setDebug(true);
		Store store = session.getStore("pop3");
		store.connect("shaolizhi2010@163.com", "cake4you");
		
		// 获得收件箱
		Folder folder = store.getFolder("INBOX");
		/* Folder.READ_ONLY：只读权限
		 * Folder.READ_WRITE：可读可写（可以修改邮件的状态）
		 */
		folder.open(Folder.READ_WRITE);	//打开收件箱
		
		// 由于POP3协议无法获知邮件的状态,所以getUnreadMessageCount得到的是收件箱的邮件总数
		System.out.println("未读邮件数: " + folder.getUnreadMessageCount());
		
		// 由于POP3协议无法获知邮件的状态,所以下面得到的结果始终都是为0
		System.out.println("删除邮件数: " + folder.getDeletedMessageCount());
		System.out.println("新邮件: " + folder.getNewMessageCount());
		
		// 获得收件箱中的邮件总数
		System.out.println("邮件总数: " + folder.getMessageCount());
		
		// 得到收件箱中的所有邮件,并解析
		Message[] messages = folder.getMessages();
		MailUtils.parseMessage(messages);
		
		//释放资源
		folder.close(true);
		store.close();
	}
	

	

	

	

}