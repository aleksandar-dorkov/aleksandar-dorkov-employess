package app.logic;

import app.model.Employee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParser {

    private final static List<String> POSSIBLE_FORMATS = Arrays.asList("yyyy-MM-dd", "dd/MM/yyyy", "dd-MM-yyyy", "M/y", "M/d/y", "M-d-y");

    public static List<Employee> parseEmployees(String path) {
        var employees = new ArrayList<Employee>();
        var file = new File(path);
        try {
            var br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                var lineData = line.split(",");
                var employee = new Employee();
                employee.setEmployeeId(Long.parseLong(lineData[0].trim()));
                employee.setProjectId(Long.parseLong(lineData[1].trim()));
                employee.setDateFrom(stringToDate(lineData[2].trim()));
                employee.setDateTo(stringToDate(lineData[3].trim()));
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static LocalDate stringToDate(String dateString) {
        if (dateString.equalsIgnoreCase("null")) {
            return LocalDate.now();
        }
        for (String formatString : POSSIBLE_FORMATS) {
            try {
                var formatter = DateTimeFormatter.ofPattern(formatString);
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                //catch parse exception and move to the next pattern
            }
        }
        return LocalDate.now();
    }

}
