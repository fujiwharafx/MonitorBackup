package threadtest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadCreator implements Runnable {
    File event;
    File log;
    Path file;
    Date date;
    Long time;
    WatcherContent watcher;
    Thread myThread;
    int i;

    public ThreadCreator() throws IOException {
        myThread = new Thread(this);
        int i = 0;
        watcher = new WatcherContent();
        this.event = new File("C:\\Users\\amaloof\\Desktop\\SatelliteDBS\\Event.txt");
        this.log = new File("C:\\Users\\amaloof\\Desktop\\SatelliteDBS\\Log.txt");
        this.date = new Date();
        this.time = date.getTime();
        
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (WatchEvent<?> event : watcher.getWatchKey().pollEvents()) {
                DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
                this.file = watcher.getDirectory().resolve((Path) event.context());
                this.date = new Date(file.toFile().lastModified());
                this.time = date.getTime();
                WriteEventFile eventFile = new WriteEventFile(this.event, this.time.toString());
                eventFile.writeToEventFile();
                AppendLogFile appendLogFile = new AppendLogFile(this.log, (df.format(this.date))+ "-Update");
                appendLogFile.appendToLogFile();
            }

        }
    }

    public Date getDate() {
        return this.date;
    }

    public Path getFile() {
        return this.file;
    }

    public Long getTime() {
        return this.time;
    }

}
