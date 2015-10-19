/* SendEmail handles sending automated confirmation emails through a free gmail account
 * If your project is throwing errors from this class new libraries need to be addeded to your project
 * In the /lib folder in your svn repository there should be a folder called JavaMail
 * In this folder add the Javadocs and the jar file Mail.jar to your project
 * If you have any questions email me at nicholas.daigle@ndsu.edu
 */

package edu.nodak.ndsu.cs.lions.blackwood;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

/**
 * Handles the sending of automated confirmation emails.
 * @author Nick Nick Daigle
 */

public class SendEmail {
    private static final String SMTP_HOST_NAME = "smtp.gmail.com"; //host name
    private static final String SMTP_AUTH_USER = "blackwoodReg@gmail.com"; //Host Email user name
    private static final String SMTP_AUTH_PWD = "blackwoodAdmin";   //Host Email password
    private static final String SMTP_PORT = "465"; //Port used to connect to host email account

    private static String emailMsgTxt = ""; //Default message text
    private static final String emailSubjectTxt = "Blackwood Account Confirmation"; //Email subject
    private static final String emailFromAddress = "blackwoodReg@gmail.com"; //Email of the sender
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static String email = ""; //Default user email
	private static String username = "";
    private static String password = "";        //Default user password
    public String[] addresses;
   

    public SendEmail(){
    	
    }
    /*
     * Default Constructor
     *
     * @param address   The user's email address. Also to be used as their username
     * @param pswd      The user's password
     */
    public SendEmail(String email, String username, String password){
        this.email = email;
		this.username = username;
        this.password = password;
    }

    /*
     * Simply constructs a message containing the user's username and desired password.
     */
    public void buildMessage()
    {
        emailMsgTxt = "Thank you for registering in Blackwood!\n\nYour username is: " + username + "\nYour password is: "
                + password + "\n\nPlease save this email for your records.";
    }

    /*
     * This method handle the actual sending of the email.
     */
    public void sendMail() throws Exception
    {
        buildMessage();
        addresses = new String[]{email};
        postMail(addresses, emailSubjectTxt, emailMsgTxt, emailFromAddress);
    }
    public void buildMessageForNameAndPwd()
    {
        emailMsgTxt = "Your username is: " + username + "\nYour password is: "
                + password + "\n\nPlease login again!";
    }
    public void sendMailForNameAndPwd() throws Exception
    {
        buildMessageForNameAndPwd();
        postMail(addresses, emailSubjectTxt, emailMsgTxt, emailFromAddress);
    }

    public String[] getAddresses() {
		return addresses;
	}
	
	public void postMail(String recipients[], String subject, String message , String from) throws MessagingException
    {
        boolean debug = true;

        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port",SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl", "true");
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");

        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance(props, auth);

        session.setDebug(debug);

        // create a message
        Message msg = new MimeMessage(session);

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++)
        {
            addressTo[i] = new InternetAddress(recipients[i]);
            System.out.println("Email Address is :" +addressTo[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);

        msg.setRecipients(Message.RecipientType.TO, addressTo);

        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        Transport.send(msg);
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }

}
