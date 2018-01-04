package threadtest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

    private File file;
    private Scanner scanner;

    public FileReader() {
        this.file = new File("");
    }

    public String read(File file) throws FileNotFoundException {
        String line = "";
        try {
            this.scanner = new Scanner(file);
            line = this.scanner.nextLine();

        } catch (FileNotFoundException e) {
            System.out.println("File is blank!");
        }
        return line;
    }
}
