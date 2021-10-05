package app.ui;

import app.model.CoupleDataGrid;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Factory that generates all the needed components GUI componeents for the project
 */
public class UIComponentFactory {

    private final static Dimension BUTTON_DIMENSION = new Dimension(130, 100);
    private final static JButton EMPLOYEES_GIRD_COL_1 = new JButton("Employee ID #1");
    private final static JButton EMPLOYEES_GIRD_COL_2 = new JButton("Employee ID #2");
    private final static JButton EMPLOYEES_GIRD_COL_3 = new JButton("Project ID");
    private final static JButton EMPLOYEES_GIRD_COL_4 = new JButton("Days worked");

    /**
     * @param actionListener the JFrame that this button should be attached to
     * @return JButton
     */
    public static JButton newButton(ActionListener actionListener) {
        var button = new JButton("Select File");
        button.addActionListener(actionListener);
        button.setPreferredSize(BUTTON_DIMENSION);
        return button;
    }

    /**
     * @return a component that will choose a .txt file for us
     */
    public static JFileChooser newTxtJFileChooser() {
        var fileChooser = new JFileChooser();
        var txt = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(txt);
        return fileChooser;
    }

    /**
     * @param list the list that you want to be displayed on the grid
     */
    public static void newEmployeesGridFrame(List<CoupleDataGrid> list) {
        var gridLayout = new GridLayout(list.size() + 1, 4, 5, 10);
        var jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null); //center it
        jFrame.setPreferredSize(new Dimension(1200, 1000));
        jFrame.setLayout(gridLayout);
        jFrame.pack();
        jFrame.add(EMPLOYEES_GIRD_COL_1);
        jFrame.add(EMPLOYEES_GIRD_COL_2);
        jFrame.add(EMPLOYEES_GIRD_COL_3);
        jFrame.add(EMPLOYEES_GIRD_COL_4);
        for (CoupleDataGrid coupleDataGrid : list) {
            jFrame.add(new JButton(coupleDataGrid.getFirstEmployeeId().toString()));
            jFrame.add(new JButton(coupleDataGrid.getSecondEmployeeId().toString()));
            jFrame.add(new JButton(coupleDataGrid.getProjectId().toString()));
            jFrame.add(new JButton(coupleDataGrid.getDaysWorked().toString()));
        }
        jFrame.setVisible(true);
    }
}
