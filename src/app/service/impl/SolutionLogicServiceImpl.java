package app.service.impl;

import app.model.Couple;
import app.model.CoupleDataGrid;
import app.model.Employee;
import app.service.SolutionLogicService;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class SolutionLogicServiceImpl implements SolutionLogicService {

    public static final List<CoupleDataGrid> COUPLES_FOR_DATA_GRID = new ArrayList<>();
    private final List<Couple> COUPLES = new ArrayList<>();

    @Override
    public List<Couple> findSolution(List<Employee> employees) {
        COUPLES_FOR_DATA_GRID.clear();
        for (int i = 0; i < employees.size() - 1; i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                var firstEmp = employees.get(i);
                var secondEmp = employees.get(j);
                //check for Overlap for same projectId
                if (this.isSameProject(firstEmp, secondEmp) && this.datesOverlap(firstEmp, secondEmp)) {
                    long overlapDays = this.calculateOverlap(firstEmp, secondEmp);

                    if (overlapDays > 0) {
                        this.updateCouples(COUPLES, firstEmp, secondEmp, overlapDays);
                        this.addToCouplesForDataGrid(firstEmp, secondEmp, overlapDays);
                    }
                }
            }
        }
        return COUPLES;
    }


    /**
     * @param firstEmp  the first Employee
     * @param secondEmp the second Employee
     * @return <b>true</b> if they or the same project id, otherwise <b>false</b>
     */
    private boolean isSameProject(Employee firstEmp, Employee secondEmp) {
        return firstEmp.getProjectId()
                .equals(secondEmp.getProjectId());
    }

    /**
     * @param firstEmp  the first Employee
     * @param secondEmp the second Employee
     * @return
     * <b>true</b> if firstEmp.dateFrom is before or equal to secondEmp.dateTo
     * or
     * if firstEmp.dateTo is after or equal to secondEmp.dateTo
     * <br/>
     * otherwise returns <b>false</b>
     */
    private boolean datesOverlap(Employee firstEmp, Employee secondEmp) {
        return (firstEmp.getDateFrom()
                .isBefore(secondEmp.getDateTo())
                || firstEmp.getDateFrom()
                .isEqual(secondEmp.getDateTo()))
                && (firstEmp.getDateTo()
                .isAfter(secondEmp.getDateFrom())
                || firstEmp.getDateTo()
                .isEqual(secondEmp.getDateFrom()));
    }


    /**
     * @param firstEmp  the first Employee
     * @param secondEmp the second Employee
     * @return the time the 2 employees worked together in days
     */
    private long calculateOverlap(Employee firstEmp, Employee secondEmp) {
        var periodStartDate = firstEmp.getDateFrom()
                .isBefore(secondEmp.getDateFrom()) ? secondEmp.getDateFrom() : firstEmp.getDateFrom();
        var periodEndDate = firstEmp.getDateTo()
                .isBefore(secondEmp.getDateTo()) ? firstEmp.getDateTo() : secondEmp.getDateTo();
        return Math.abs(ChronoUnit.DAYS.between(periodStartDate, periodEndDate));
    }

    /**
     * @param couples the List that the caller method is supposed to return
     * @param firstEmp the first Employee
     * @param secondEmp the second Employee
     * @param overlapDays the time the 2 employees worked together in days
     * <br/>
     * add a new Couple to the collection that the caller method is supposed to return.
     */
    private void updateCouples(List<Couple> couples, Employee firstEmp, Employee secondEmp, long overlapDays) {
        var isPresent = false;

        //If the team is present updated the time
        for (Couple couple : couples) {
            if (this.isTeamPresent(couple, firstEmp.getEmployeeId(), secondEmp.getEmployeeId())) {
                couple.setTotalDuration(couple.getTotalDuration() + overlapDays);
                isPresent = true;
            }
        }
        //If the team isn't present just add it
        if (!isPresent) {
            var team = new Couple(firstEmp.getEmployeeId(), secondEmp.getEmployeeId(), overlapDays);
            couples.add(team);
        }
    }

    private boolean isTeamPresent(Couple couple, long firstEmpId, long secondEmpId) {
        return (couple.getFirstEmployeeId() == firstEmpId
                && couple.getSecondEmployeeId() == secondEmpId)
                || (couple.getFirstEmployeeId() == secondEmpId
                && couple.getSecondEmployeeId() == firstEmpId);
    }

    /**
     * @param firstEmp the first Employee
     * @param secondEmp the second Employee
     * @param overlapDays the time the 2 employees worked together in days
     * populates the array needed for the data-grid
     */
    private void addToCouplesForDataGrid(Employee firstEmp, Employee secondEmp, long overlapDays) {
        var dataGridCouple = new CoupleDataGrid();
        dataGridCouple.setFirstEmployeeId(firstEmp.getEmployeeId());
        dataGridCouple.setSecondEmployeeId(secondEmp.getEmployeeId());
        dataGridCouple.setProjectId(firstEmp.getProjectId());
        dataGridCouple.setDaysWorked(overlapDays);
        COUPLES_FOR_DATA_GRID.add(dataGridCouple);

    }
}
