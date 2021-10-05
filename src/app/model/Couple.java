package app.model;

public class Couple {

    private Long firstEmployeeId;
    private Long secondEmployeeId;
    private Long totalDuration;

    public Couple() {
    }

    public Couple(Long firstEmployeeId, Long secondEmployeeId, Long totalDuration) {
        this.firstEmployeeId = firstEmployeeId;
        this.secondEmployeeId = secondEmployeeId;
        this.totalDuration = totalDuration;
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

    public Long getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Long totalDuration) {
        this.totalDuration = totalDuration;
    }

    public void addOverlapDuration(long overlap) {
        this.totalDuration += overlap;
    }

    @Override
    public String toString() {
        return "Team[" +
                "firstEmployeeId=" + firstEmployeeId +
                ", secondEmployeeId=" + secondEmployeeId +
                ", totalDuration=" + totalDuration +
                ']';
    }
}
