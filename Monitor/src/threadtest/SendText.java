
package threadtest;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendText {
    final String username = "encrypt";
    final String password = "encrypt";
    
    private String emailBody;
    private Properties props;
    private Session session;
    
    
    public SendText(String emailBody){
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
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("encrypt@vtext.com"));
            message.setSubject("MONITOR: WXDATA OUTAGE");
            message.setText(this.emailBody);
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex){
            mex.printStackTrace();
        }
    }
}
