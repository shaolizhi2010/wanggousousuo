package com.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import com.digger.advertisement.AdvertisementDigger;
import com.entity.AdvertisementEntity;
import com.sun.mail.imap.IMAPMessage;
import com.utils.L;
import com.utils.U;

/**
  * 收取邮件，并提取打折促销信息
 */
public class AdvertisementMailReceiver {

	/**
	 * 收取邮件，并提取打折促销信息
	 * 
	 * @throws Exception
	 */
	public static void receive(List<AdvertisementEntity> list) {

		// 准备连接服务器的会话信息
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imap");
		props.setProperty("mail.imap.host", "imap.163.com");
		props.setProperty("mail.imap.port", "143");

		// 创建Session实例对象
		Session session = Session.getInstance(props);

		Store store = null;
		Folder folder = null;
		Message[] messages = null;

		// 连接邮件服务器
		try {
			// 创建IMAP协议的Store对象
			store = session.getStore("imap");
			store.connect("shaolizhi2010@163.com", "cake4you");
			// 获得收件箱
			folder = store.getFolder("INBOX");
			// 以读写模式打开收件箱
			folder.open(Folder.READ_WRITE);

			// 获得收件箱的邮件列表
			messages = folder.getMessages();

			// 解析邮件
			for (Message message : messages) {
				IMAPMessage msg = (IMAPMessage) message;
				if(msg.isSet(Flag.SEEN)){
					continue;//已读 说明已处理过，不在处理 避免重复
				}
				// String subject = MimeUtility.decodeText(msg.getSubject());

				StringBuffer content = new StringBuffer();
				MailUtils.getMailTextContent(msg, content);

				// FileUtil.saveFile(content.toString(),
				// "d:/mmm/"+msg.getSubject());
				// //System.out.println(msg.get);
				// //System.out.println(content);
				// MailUtils.saveAttachment(msg, "d:/mmm/");

				// 提取广告信息
				
				//TODO
				//AdvertisementDigger.dig(content.toString(), list);
				// //System.out.println("list size : "+list.size());
				// U.printList(list);

				// MailUtils.parseMessage(msg); // 解析邮件
				// 第二个参数如果设置为true，则将修改反馈给服务器。false则不反馈给服务器
				msg.setFlag(Flag.SEEN, true); // 设置已读标志
				
				if (System.currentTimeMillis()
						- msg.getReceivedDate().getTime() > 7 * 24 * 3600 * 1000) {// 大于7天
					msg.setFlag(Flag.DELETED, true); // 删
				}
			}

			// 关闭资源
			folder.close(true);
			store.close();
		} catch (MessagingException e) {
			L.exception("IMAPReceiveMail", "读取邮件出错 " + e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {
		List<AdvertisementEntity> list = new ArrayList<AdvertisementEntity>();
		receive(list);
		U.printList(list);
	}
}