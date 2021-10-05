package app.logic;

import app.model.Employee;
import app.model.Couple;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Solution {

    private static final List<Couple> TEAMS = new ArrayList<>();

    public static List<Couple> findSolution(List<Employee> allRecords) {
        System.out.println(allRecords);
        for (int i = 0; i < allRecords.size() - 1; i++) {
            for (int j = i + 1; j < allRecords.size(); j++) {
                Employee firstEmp = allRecords.get(i);
                Employee secondEmp = allRecords.get(j);

                if (firstEmp.getProjectId()
                        .equals(secondEmp.getProjectId())
                        && hasOverlap(firstEmp, secondEmp)) {
                    long overlapDays = calculateOverlap(firstEmp, secondEmp);

                    if (overlapDays > 0) {
                        updateTeamCollection(TEAMS, firstEmp, secondEmp, overlapDays);
                    }
                }
            }
        }
        return TEAMS;
    }

    /**
     * hasOverlap method returning if two employees have overlap
     */
    private static boolean hasOverlap(Employee firstEmp, Employee secondEmp) {
        //have overlap if (startA <= endB) and (endA >= startB)
        return (firstEmp.getDateFrom()
                .isBefore(secondEmp.getDateTo())
                || firstEmp.getDateFrom()
                .isEqual(secondEmp.getDateTo()))
                && (firstEmp.getDateTo()
                .isAfter(secondEmp.getDateFrom())
                || firstEmp.getDateTo()
                .isEqual(secondEmp.getDateFrom()));
    }

    private static long calculateOverlap(Employee firstEmp, Employee secondEmp) {
        LocalDate periodStartDate =
                firstEmp.getDateFrom()
                        .isBefore(secondEmp.getDateFrom()) ?
                        secondEmp.getDateFrom() : firstEmp.getDateFrom();

        LocalDate periodEndDate =
                firstEmp.getDateTo()
                        .isBefore(secondEmp.getDateTo()) ?
                        firstEmp.getDateTo() : secondEmp.getDateTo();

        //This method work good and when we have involved leap years too
        //from 2019-01-01 to 2019-01-15 will return 14days in result not 15, which will accept for correct
        return Math.abs(ChronoUnit.DAYS.between(periodStartDate, periodEndDate));
    }

    private static void updateTeamCollection(List<Couple> couples, Employee firstEmp, Employee secondEmp, long overlapDays) {
        AtomicBoolean isPresent = new AtomicBoolean(false);
        //If the team is present -> update team's total overlap
        couples.forEach(couple -> {
            if (isTeamPresent(couple, firstEmp.getEmployeeId(), secondEmp.getEmployeeId())) {
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

    private static boolean isTeamPresent(Couple team, long firstEmpId, long secondEmpId) {
        return (team.getFirstEmployeeId() == firstEmpId
                && team.getSecondEmployeeId() == secondEmpId)
                || (team.getFirstEmployeeId() == secondEmpId
                && team.getSecondEmployeeId() == firstEmpId);
    }
}
