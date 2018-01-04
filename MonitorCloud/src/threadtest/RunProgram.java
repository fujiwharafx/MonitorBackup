
package threadtest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.internet.AddressException;

public class RunProgram {

    public static void main(String[] args) throws InterruptedException, IOException, AddressException {
        File event = new File("C:\\Users\\amaloof\\Desktop\\CloudFODHourlyTemp\\Event.txt");
        File log = new File("C:\\Users\\amaloof\\Desktop\\CloudFODHourlyTemp\\Log.txt");

        new Alert("\\\\pttvfs1\\WxData_Proxy\\Image\\FOD Hourly CONUS 4km\\Temperature", event, log).runAlert();

    }
}
