package app.service;

import app.model.Employee;

import java.time.LocalDate;
import java.util.List;

public interface FileParserService {

    /**
     * @param path the absolute path to the file
     * @return a list of the parsed employees from the file
     */
    List<Employee> parseEmployees(String path);
}
