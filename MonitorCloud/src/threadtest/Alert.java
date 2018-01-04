
package threadtest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
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
    private File drive;
    
    public Alert(String dataType, File event, File log){
        this.dataType = dataType;
        this.event = event;
        this.log = log;
        this.reader = new FileReader();
        this.fileName = "";
        this.drive = new File("\\\\pttvfs1\\WxData_Proxy\\Image\\FOD Hourly CONUS 4km\\Temperature");
        
        
        
    }
    
    
    public void runAlert() throws IOException, InterruptedException, AddressException{
        this.threadCreator = new ThreadCreator();
        threadCreator.myThread.start();
        
        new UserInterface("Cloud Hourly Temp", ("monitoring...   " + dataType)).run();
        
        
        
        while(threadCreator.myThread.isAlive()){
            this.fileName = reader.read(this.event);
            Thread.sleep(1500000);
            if(this.fileName.matches(reader.read(event)) && this.drive.exists()){
                long millis = System.currentTimeMillis() % 1000;
                this.dfA = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
                this.dfB = new SimpleDateFormat("HH:mm:ss");
                this.date = new Date();
                
                //SEND EMAIL
                //this.email = new SendEmail("REPORTED-----" + date + "\nDATA THAT IS MISSING-----" + dataType + "\nDATA LAST UPDATED AT----- " + dfB.format(threadCreator.getDate()) + "\n\n\n" + millis);
                //this.email.send();
                
                //SEND TEXT
                //this.textSMS = new SendText("DBS RADAR");
                //this.textSMS.send();
                
                new UserInterfaceHide("OUTAGE", dfA.format(date)).run();
                this.logFile = new AppendLogFile(log, (dfA.format(date)) + "---OUTAGE");
                this.logFile.appendToLogFile();
            }if(!this.drive.exists()){
                Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                new UserInterfaceHide("DISCONNECTED DRIVE!", df.format(date)).run();
                this.logFile = new AppendLogFile(log, df.format(date) + " Drive is not connected!");
                this.logFile.appendToLogFile();
                System.exit(0);
            }
        }
    }
    
    
}
