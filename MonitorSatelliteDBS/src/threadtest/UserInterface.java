
package threadtest;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class UserInterface implements Runnable {

    private JFrame frame;
    private JLabel textField;
    private String text;
    private String label;

    public UserInterface() {
    }

    public UserInterface(String text, String label) {
        this.text = text;
        this.label = label;
    }

    public void setTextLabel(String text, String label) {
        this.text = text;
        this.label = label;
    }

    @Override
    public void run() {
        frame = new JFrame(this.text);
        frame.setPreferredSize(new Dimension(230, 100));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        JLabel textField = new JLabel(this.label);
        container.add(textField);
        
    }

    public JFrame getFrame() {
        return frame;
    }

}
