# LeaveHelper

LeaveHelper is a command line app written in Java that allows you to keep a record of your annual leave bookings. It is my first Github repo for a Java project. I produced it as part of a Java training course and it took me 1.5 days. I need to refactor and improve it but I was keen to publish and share my initial creation. I also intend to add a new feature or two.

The app is based on an annual leave allocation (# days) which applies for a calendar year only i.e. you are constrained to making leave bookings within the year. You set your number of days allocation at the beginning, including bank holidays. You can make bookings as long as you have enough days of annual leave remaining.

To run it open a termnial and type
```
javac LeaveHelper.java
```
You will then see the following:
```
Welcome to the Annual Leave Helper - 2019

Enter your leave entitlement for this year (no. of days):
```
Enter a sensible number :) . Then you will see:
```
Choose from the following options:
1 - Make a booking
2 - View your bookings
3 - Delete a booking
4 - Edit a booking
5 - See my leave balance
```
You must use the date format dd-MM-2019. It checks if the date is a Saturday or Sunday which is invalid for both start and end dates. Currently its fixed to use the year 2019 but I plan to change it to use current year by default or allow for a different year to be used if desired.
