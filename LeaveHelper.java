import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeaveHelper {

    public static void main(String[] args) {
        runLeaveHelper();
    }

    public static void runLeaveHelper() {

        System.out.println("\nWelcome to the Annual Leave Helper - 2019\n");
        System.out.println("Enter your leave entitlement for this year (no. of days):");
        Scanner scanner = new Scanner(System.in);
        String n = scanner.next();
        int num = Integer.parseInt(n);
        Allocation myAlloc = new Allocation(num);
        System.out.println("You have been granted " + myAlloc.getAllocDays() + " days of annual leave.");;

        while (true) {
            System.out.println("Choose from the following options:");
            System.out.println("1 - Make a booking");
            System.out.println("2 - View your bookings");
            System.out.println("3 - Delete a booking");
            System.out.println("4 - Edit a booking");
            System.out.println("5 - See my leave balance");

            String actionChoice = scanner.next();

            switch (actionChoice) {
                case "1":
                    System.out.println("Enter a note to describe the booking e.g. Trekking in the Dolomites :");
                    String note = scanner.next();
                    note += scanner.nextLine();

                    System.out.println("Enter the start date in the format dd-MM-2019");
                    String startDateInput = scanner.next();
                    LocalDate startDate = LocalDate.parse(startDateInput, DateTimeFormat.forPattern("dd-MM-yyyy"));
                    if ( startDate.getDayOfWeek() == DateTimeConstants.SATURDAY ||
                            startDate.getDayOfWeek() == DateTimeConstants.SUNDAY ) {
                        System.out.println("That date falls on the weekend. Please enter it again.");
                        startDateInput = scanner.next();
                        startDate = LocalDate.parse(startDateInput, DateTimeFormat.forPattern("dd-MM-yyyy"));
                    }

                    System.out.println("Enter the end date in the format dd-MM-2019");
                    String endDateInput = scanner.next();
                    LocalDate endDate = LocalDate.parse(endDateInput, DateTimeFormat.forPattern("dd-MM-yyyy"));
                    if ( endDate.getDayOfWeek() == DateTimeConstants.SATURDAY ||
                            endDate.getDayOfWeek() == DateTimeConstants.SUNDAY ) {
                        System.out.println("That date falls on the weekend. Please enter it again.");
                        endDateInput = scanner.next();
                        endDate = LocalDate.parse(endDateInput, DateTimeFormat.forPattern("dd-MM-yyyy"));
                    }
                    int newDays = Days.daysBetween(startDate, endDate).getDays() + 1;
                    List<LocalDate> datesList = Stream.iterate(startDate, date -> date.plusDays(1)).limit(newDays).collect(Collectors.toList());
//                    System.out.println("datesList is " + datesList);
                    long weekendCount = datesList.stream()
                            .filter(day -> (day.getDayOfWeek() == DateTimeConstants.SATURDAY ||
                                    day.getDayOfWeek() == DateTimeConstants.SUNDAY))
                            .count();
                    newDays = newDays - (int) weekendCount;

                    if ((startDate.isBefore(endDate) || startDate.isEqual(endDate)) && (myAlloc.getAllocDays() >= newDays)) {

                        Range<LocalDate> newRange = Range.closed(startDate, endDate);

                        // create a List of existing Ranges by mapping from bookingList, then a RangeSet
                        List<Range<LocalDate>> bookedRanges = myAlloc.getBookingList().stream()
                                .map(r -> Range.closed(r.getStartDate(), r.getEndDate()))
                                .collect(Collectors.toList());
                        RangeSet<LocalDate> bookedRangesSet = TreeRangeSet.create(bookedRanges);

                      if (!bookedRangesSet.intersects(newRange)) {
                            // if all good then create the booking and add it into bookingList
                            System.out.println("No. of days booked is: " + newDays);
                            Booking newBooking = new Booking(note, startDate, endDate);
                            myAlloc.getBookingList().add(newBooking);
                            myAlloc.reduceAlloc(newDays);
                            System.out.println("You have " + myAlloc.getAllocDays() + " days left to book this year.\n");
                        } else {
                          System.out.println("Oops, can't make that booking as it overlaps with an existing booking.\n");
                      }
                    } else {
                            System.out.println("Oops, can't make that booking you don't have enough days of leave to take.\n");
                    }
                    break;
                case "2":
                    System.out.println("Here are all your leave bookings");
                    System.out.println(myAlloc.printBookingList());
                    System.out.println();
                    break;
                case "3":
                    // run view all bookings i.e. do case 2
                    System.out.println("In due course you would see a numbered list of bookings from which you choose a number to delete.\n");
                    break;
                case "4":
                    // run view all bookings i.e. do case 2
                    System.out.println("This option will need an amount of work to make it nice.\n");
                    break;
                case "5":
                    System.out.println("You have " + myAlloc.getAllocDays() + " days remaining from your annual leave allocation.\n");
                    break;
                default:
                    System.out.println("Sorry, that's an invalid choice.\n");
                    break;
            }
        }

    }

}
