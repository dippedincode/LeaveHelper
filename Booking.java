package leave.helper.project;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

class Booking {
    private String descriptor;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(String descriptor, LocalDate startDate, LocalDate endDate) {
        this.descriptor = descriptor;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

}
