
package threadtest;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.internet.AddressException;


public class Alert {
    private DateFormat dfA;
    private DateFormat dfB;
    private Date date;
    private SendEmail email;
    private SendText textSMS;
    private AppendLogFile logFile;
    private FileReader reader;
    private String dataType;
    private String fileName;
    private File event;
    private File log;
    private ThreadCreator threadCreator;
    
    public Alert(String dataType, File event, File log){
        this.dataType = dataType;
        this.event = event;
        this.log = log;
        this.reader = new FileReader();
        this.fileName = "";
        
        
    }
    
    
    public void runAlert() throws IOException, InterruptedException, AddressException{
        this.threadCreator = new ThreadCreator();
        threadCreator.myThread.start();
        
        new UserInterface("CloudMonitor", ("monitoring...   " + dataType)).run();
        
        while(threadCreator.myThread.isAlive()){
            this.fileName = reader.read(this.event);
            Thread.sleep(5000);
            if(this.fileName.matches(reader.read(event))){
                long millis = System.currentTimeMillis() % 1000;
                this.dfA = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
                this.dfB = new SimpleDateFormat("HH:mm:ss");
                this.date = new Date();
                
                //SEND EMAIL
                this.email = new SendEmail("STARTED-----" + date + "\nDATA THAT IS MISSING-----" + dataType + "\nDATA LAST UPDATED AT----- " + dfB.format(threadCreator.getDate()) + "\n\n\n" + millis);
                this.email.send();
                
                //SEND TEXT
                this.textSMS = new SendText("DATA OUTAGE");
                this.textSMS.send();
                
                new UserInterfaceHide("OUTAGE", dfA.format(date)).run();
                this.logFile = new AppendLogFile(log, (dfA.format(date)) + "---OUTAGE");
                this.logFile.appendToLogFile();
            }
        }
    }
    
    
}
