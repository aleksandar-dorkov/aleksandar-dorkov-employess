package app.service.impl;

import app.model.Couple;
import app.model.Employee;
import app.service.SolutionLogicService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SolutionLogicServiceImpl implements SolutionLogicService {

    private final List<Couple> COUPLES = new ArrayList<>();

    @Override
    public List<Couple> findSolution(List<Employee> employees) {
        for (int i = 0; i < employees.size() - 1; i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                Employee firstEmp = employees.get(i);
                Employee secondEmp = employees.get(j);
                //check for Overlap for same projectId
                if (firstEmp.getProjectId().equals(secondEmp.getProjectId())
                        && this.hasOverlap(firstEmp, secondEmp)) {
                    long overlapDays = this.calculateOverlap(firstEmp, secondEmp);

                    if (overlapDays > 0) {
                        this.updateCouples(COUPLES, firstEmp, secondEmp, overlapDays);
                    }
                }
            }
        }
        return COUPLES;
    }

    // if dateFrom of firstEmp is before or equal dateTo of secondEmp
    // and dateTo of firstEmp is after or equal dateFrom secondEmp
    private boolean hasOverlap(Employee firstEmp, Employee secondEmp) {
        return (firstEmp.getDateFrom()
                .isBefore(secondEmp.getDateTo())
                || firstEmp.getDateFrom()
                .isEqual(secondEmp.getDateTo()))
                && (firstEmp.getDateTo()
                .isAfter(secondEmp.getDateFrom())
                || firstEmp.getDateTo()
                .isEqual(secondEmp.getDateFrom()));
    }

    //returns to overlap in days
    private long calculateOverlap(Employee firstEmp, Employee secondEmp) {
        LocalDate periodStartDate = firstEmp.getDateFrom()
                .isBefore(secondEmp.getDateFrom()) ? secondEmp.getDateFrom() : firstEmp.getDateFrom();
        LocalDate periodEndDate = firstEmp.getDateTo()
                .isBefore(secondEmp.getDateTo()) ? firstEmp.getDateTo() : secondEmp.getDateTo();
        return Math.abs(ChronoUnit.DAYS.between(periodStartDate, periodEndDate));
    }

    private void updateCouples(List<Couple> couples, Employee firstEmp, Employee secondEmp, long overlapDays) {
        AtomicBoolean isPresent = new AtomicBoolean(false);
        //If the team is present -> update team's total overlap
        couples.forEach(couple -> {
            if (this.isTeamPresent(couple, firstEmp.getEmployeeId(), secondEmp.getEmployeeId())) {
                couple.addOverlapDuration(overlapDays);
                isPresent.set(true);
            }
        });
        //If the team isn't present -> create and add new team with the current data
        if (!isPresent.get()) {
            var team = new Couple(firstEmp.getEmployeeId(), secondEmp.getEmployeeId(), overlapDays);
            couples.add(team);
        }
    }

    private boolean isTeamPresent(Couple team, long firstEmpId, long secondEmpId) {
        return (team.getFirstEmployeeId() == firstEmpId
                && team.getSecondEmployeeId() == secondEmpId)
                || (team.getFirstEmployeeId() == secondEmpId
                && team.getSecondEmployeeId() == firstEmpId);
    }
}
