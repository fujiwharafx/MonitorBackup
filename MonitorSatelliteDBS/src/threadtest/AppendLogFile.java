
package threadtest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AppendLogFile {

    String logText;
    File logFile;

    public AppendLogFile(File logFile, String logText) {
        this.logText = logText;
        this.logFile = logFile;
    }

    public void appendToLogFile() {

        BufferedWriter bw = null;

        try {

            bw = new BufferedWriter(new FileWriter(this.logFile, true));
            bw.write(this.logText);
            bw.newLine();
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
