package app.service;

import app.model.Employee;

import java.time.LocalDate;
import java.util.List;

public interface FileParserService {

    List<Employee> parseEmployees(String path);

    LocalDate stringToDate(String dateString);
}
