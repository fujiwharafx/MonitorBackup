
package threadtest;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    final String username = "wxdatamonitor@gmail.com";
    final String password = "wxdata101";
    
    private String emailBody;
    private Properties props;
    private Session session;
    private Address[] recipients;
    
    
    public SendEmail(String emailBody) throws AddressException{
        this.recipients = new Address[] {InternetAddress.parse("andrew.maloof@weathergroup.com")[0],
                                        InternetAddress.parse("doug.dickson@weathergroup.com")[0]};
        this.emailBody = emailBody;
        this.props = new Properties();
        this.props.put("mail.smtp.auth", "true");
        this.props.put("mail.smtp.starttls.enable", "true");
	this.props.put("mail.smtp.host", "smtp.gmail.com");
	this.props.put("mail.smtp.port", "587");
        this.session = Session.getInstance(props,
                new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(username, password);
                    }
                });
    }
    
    public void send(){
        try{
            MimeMessage message = new MimeMessage(this.session);
            message.setFrom(new InternetAddress("wxdatamonitor@gmail.com"));
            message.addRecipients(Message.RecipientType.TO,
                    this.recipients);
            message.setSubject("MONITOR: WXDATA OUTAGE");
            message.setText(this.emailBody);
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex){
            mex.printStackTrace();
        }
    }
}
