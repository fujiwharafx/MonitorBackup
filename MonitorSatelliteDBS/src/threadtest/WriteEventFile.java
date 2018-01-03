
package threadtest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteEventFile {
    String logText;
    File logFile;

    public WriteEventFile(File logFile, String logText) {
        this.logText = logText;
        this.logFile = logFile;
    }

    public void writeToEventFile() {

        BufferedWriter bw = null;

        try {

            bw = new BufferedWriter(new FileWriter(this.logFile, false));
            bw.write(this.logText);
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ioe2) {

                }
            }
        }

    }

}

