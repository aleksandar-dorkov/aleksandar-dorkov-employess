package app.GUI;

import app.logic.FileParser;
import app.logic.Solution;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationFrame extends JFrame implements ActionListener {

    private final JButton button = ComponentFactory.newButton(this);

    public ApplicationFrame() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); //center it
        this.setPreferredSize(new Dimension(300, 170));
        this.setLayout(new FlowLayout());
        this.add(button);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            var fileChooser = ComponentFactory.newTxtJFileChooser();
            var response = fileChooser.showOpenDialog(null);//this will select file to open
            if (response == JFileChooser.APPROVE_OPTION) {
                var employees = FileParser.parseEmployees(fileChooser.getSelectedFile()
                        .getAbsolutePath());
                var solution = Solution.findSolution(employees);
                solution.forEach(System.out::println);
                System.out.println(solution);
            }
        }
    }
}
