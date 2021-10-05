package app.GUI;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Dimension;
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
}
