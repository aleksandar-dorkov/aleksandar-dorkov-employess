package app.service;

import app.model.Couple;
import app.model.Employee;

import java.util.List;

public interface SolutionLogicService {

    List<Couple> findSolution(List<Employee> allRecords);
}
