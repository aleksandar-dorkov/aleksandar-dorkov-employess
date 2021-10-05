package app.service;

import app.model.Couple;
import app.model.Employee;

import java.util.List;

public interface SolutionLogicService {


    /**
     * @param employees all the employees read from the file
     */
    void findSolution(List<Employee> employees);
}
