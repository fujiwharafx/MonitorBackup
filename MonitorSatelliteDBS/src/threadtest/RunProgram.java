
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
        File event = new File("C:\\Users\\amaloof\\Desktop\\SatelliteDBS\\Event.txt");
        File log = new File("C:\\Users\\amaloof\\Desktop\\SatelliteDBS\\Log.txt");

        new Alert("K:\\VizData\\DBS_Images\\prism_sat_IR", event, log).runAlert();

    }
}
