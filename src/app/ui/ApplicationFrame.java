package app.ui;

import app.service.FileParserService;
import app.service.SolutionLogicService;
import app.service.impl.FileParserServiceImpl;
import app.service.impl.SolutionLogicServiceImpl;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationFrame extends JFrame implements ActionListener {

    private final FileParserService fileParserService = new FileParserServiceImpl();
    private final SolutionLogicService solutionLogicService = new SolutionLogicServiceImpl();
    private final JButton button = UIComponentFactory.newButton(this);

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
            var fileChooser = UIComponentFactory.newTxtJFileChooser();
            var response = fileChooser.showOpenDialog(null);//this will select file to open
            if (response == JFileChooser.APPROVE_OPTION) {
                var employees = this.fileParserService.parseEmployees(fileChooser.getSelectedFile()
                        .getAbsolutePath());
                this.solutionLogicService.findSolution(employees);
                UIComponentFactory.newEmployeesGridFrame(SolutionLogicServiceImpl.COUPLES_FOR_DATA_GRID);
            }
        }
    }
}
