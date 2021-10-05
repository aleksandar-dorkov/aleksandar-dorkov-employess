package app.GUI;

import app.model.Couple;
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
import java.util.Comparator;

public class ApplicationFrame extends JFrame implements ActionListener {

    private final FileParserService fileParserService = new FileParserServiceImpl();
    private final SolutionLogicService solutionLogicService = new SolutionLogicServiceImpl();
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
                var employees = this.fileParserService.parseEmployees(fileChooser.getSelectedFile()
                        .getAbsolutePath());
                var solution = this.solutionLogicService.findSolution(employees);
                solution.sort((o1, o2) -> (int) (o2.getTotalDuration() - o1.getTotalDuration()));
                System.out.println(solution.get(0));
                ComponentFactory.newEmployeesGridFrame();
            }
        }
    }
}
