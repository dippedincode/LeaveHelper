import java.util.ArrayList;
import java.util.List;

public class Allocation {

    private int allocDays;
    private List<Booking> bookingList = new ArrayList<>();
    private int remainDays;

    public Allocation(int numDays) {
        this.allocDays = numDays;
    }

    public Allocation() {
        this.allocDays = 25;
    }

    public int getAllocDays() {
        return allocDays;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public String printBookingList() {
        String bookingInfo = "";
        for (Booking eachBooking : bookingList) {
            bookingInfo = bookingInfo + eachBooking.getStartDate() + " to " + eachBooking.getEndDate() + " : " +
                    eachBooking.getDescriptor() + "\n";
        }
        return bookingInfo + "-----------------------------------------------------------------";
    }

    public int reduceAlloc(int days) {
        allocDays =  allocDays - days;
        return allocDays;
    }

}
