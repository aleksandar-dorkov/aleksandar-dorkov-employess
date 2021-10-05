package app.service;

import app.model.Couple;
import app.model.Employee;

import java.util.List;

public interface SolutionLogicService {


    /**
     * @param employees all the employees read from the file
     * @return a list of couples containing emp1 and emp 2 ids and the total time they worked tougether
     */
    List<Couple> findSolution(List<Employee> employees);
}
