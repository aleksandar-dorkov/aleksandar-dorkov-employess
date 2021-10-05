package app.GUI;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class ComponentFactory {

    private final static Dimension BUTTON_DIMENSION = new Dimension(130, 100);

    public static JButton newButton(ActionListener actionListener) {
        var button = new JButton("Select File");
        button.addActionListener(actionListener);
        button.setPreferredSize(BUTTON_DIMENSION);
        return button;
    }

    public static JFileChooser newTxtJFileChooser() {
        var fileChooser = new JFileChooser();
        var txt = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(txt);
        return fileChooser;
    }

    public static void newEmployeesGridFrame() {
        var gridLayout = new GridLayout(14, 4, 5, 10);
        var jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null); //center it
        jFrame.setPreferredSize(new Dimension(1200, 1000));
        jFrame.setLayout(gridLayout);
        jFrame.pack();
        for (int i = 0; i < 56; i++) {
            jFrame.add(new JButton("Random text placeholder"));
        }
        jFrame.setVisible(true);
    }
}
