package app.model;

public class CoupleDataGrid {

    private Long firstEmployeeId;
    private Long secondEmployeeId;
    private Long projectId;
    private Long daysWorked;

    public CoupleDataGrid() {
    }

    public CoupleDataGrid(Long firstEmployeeId, Long secondEmployeeId, Long projectId, Long daysWorked) {
        this.firstEmployeeId = firstEmployeeId;
        this.secondEmployeeId = secondEmployeeId;
        this.projectId = projectId;
        this.daysWorked = daysWorked;
    }

    public Long getFirstEmployeeId() {
        return firstEmployeeId;
    }

    public void setFirstEmployeeId(Long firstEmployeeId) {
        this.firstEmployeeId = firstEmployeeId;
    }

    public Long getSecondEmployeeId() {
        return secondEmployeeId;
    }

    public void setSecondEmployeeId(Long secondEmployeeId) {
        this.secondEmployeeId = secondEmployeeId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(Long daysWorked) {
        this.daysWorked = daysWorked;
    }

    @Override
    public String toString() {
        return "CoupleDataGrid[" +
                "firstEmployeeId=" + firstEmployeeId +
                ", secondEmployeeId=" + secondEmployeeId +
                ", projectId=" + projectId +
                ", daysWorked=" + daysWorked +
                ']';
    }
}
