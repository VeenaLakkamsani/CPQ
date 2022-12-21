package ricoh.core.email;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.search.FlagTerm;

import org.jsoup.Jsoup;

public class Email {
	public Properties properties = null;
	public Session userSession = null;
	public Store store = null;
	public Folder inbox = null;
	public String userName = "cigniti30031984@gmail.com";
	public String password = "user@cigniti";

	public Session authenticate(final String strUserName, final String strPassword, String strHostName, String strPort, String strTransportProtocol){
		
		try {
			this.userName = strUserName;
			this.password = strPassword;
			
			properties = new Properties();
			//properties.setProperty("mail.host", "imap.gmail.com");
			properties.put("mail.smtp.host", strHostName);
			//Gmail: properties.setProperty("mail.port", "995");
			properties.put("mail.smtp.socketFactory.port", strPort);
			properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.port", strPort);
			properties.setProperty("mail.transport.protocol", "imaps");

			//Create a new session
			userSession = Session.getInstance(properties,

			new javax.mail.Authenticator() {

				protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication(userName, password);

				}

			});	
			Thread.sleep(8000L);
			
		} catch ( InterruptedException ie){
			ie.printStackTrace();
		}		
		return userSession;
	}
	
	public String readEmail(String strfromFolder, String strSubject){ //readQuikFlixResetLink() {

		try {
			
			if (userSession != null && properties != null) {
				
				//store = userSession.getStore("imaps");
				store = userSession.getStore(properties.getProperty("mail.transport.protocol"));
				store.connect();

				//inbox = store.getFolder("INBOX");
				inbox = store.getFolder(strfromFolder.trim());

				inbox.open(Folder.READ_WRITE);

				Message messages[] = inbox.search(new FlagTerm(
						new Flags(Flag.SEEN), false));

				System.out.println("Number of mails = " + messages.length);

				//Iterate through messges 
				for (int i = 0; i < messages.length; i++) {
					
					Message message = messages[i];
					
					//find the first message with expected subject
					if (message.getSubject().equalsIgnoreCase(strSubject)) {
						Object content;
						try {
							content = message.getContent();
							
							if (content instanceof String) {
//								message.setFlag(Flags.Flag.DELETED, true);
								String strContent = (String) content;
								return strContent;
//								resetURL = Jsoup.parse((String) content)
//										.select("a").first().text();
//								return resetURL;
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				inbox.close(true);
				store.close();				
			}
		} catch (NoSuchProviderException e) {

			e.printStackTrace();

		} catch (MessagingException e) {

			e.printStackTrace();

		} 
		return "No Unread Mails from QuickFlix";
	}

	public boolean sendEmail(String[] to, String[] cc, String subject, String Mailbody, String attachmentpath){
		boolean blnRes = false;
		
		if (userSession != null && properties != null){
			
			MimeMessage message = new MimeMessage(userSession);

			try {
				message.setFrom(new InternetAddress(this.userName));
				InternetAddress[] toAddress = new InternetAddress[to.length];

				// To get the array of addresses
				for( int i = 0; i < to.length; i++ ) {
					toAddress[i] = new InternetAddress(to[i]);
					message.addRecipient(Message.RecipientType.TO, toAddress[i]);
				}

				InternetAddress[] addressCC = new InternetAddress[cc.length];
				for (int i = 0; i < cc.length; i++)
				{
					addressCC[i] = new InternetAddress(cc[i]);
					message.setRecipients(Message.RecipientType.CC, addressCC);					
				}

				message.setSubject(subject);	
				message.setSentDate(new Date());

				// Create the message part 
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setText(Mailbody + "\n"+ "\n" + "This is an auto-generated email.");
				MimeBodyPart attachmentPart = new MimeBodyPart();
				DataSource source = new FileDataSource(attachmentpath);
				attachmentPart.setDataHandler(new DataHandler(source));
				attachmentPart.setFileName("AutomationResults.html");
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);
				multipart.addBodyPart(attachmentPart);
				message.setContent(multipart);

				//Send an Email
				Transport.send(message, message.getAllRecipients());

				System.out.println("Email successfully sent to "+ Arrays.toString(to));
				return true;
			}
			catch (AddressException ae) {
				ae.printStackTrace();
				System.out.println(ae.getMessage());
				return false;
			}
			catch (MessagingException me) {
				me.printStackTrace();
				System.out.println(me.getMessage());
				return false;
			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e.getMessage());
				return false;
			}			
		}
		return blnRes;
	}
}
