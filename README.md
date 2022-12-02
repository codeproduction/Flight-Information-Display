# Flight Information Display (FIDS) Project

## About the Application (Task 2)

**What will the application do?**  

- It will display Flights and information related to their departure and arrival timings.
- It can be edited by the airport staff in order to update status of Flights and also to add or remove Flights
- It will display the current time, and possibly time in the GMT 0 time zone
- It will be useful for hotels with large amount of guests staying for short period of time
- It might be able to display simple statistics regarding the performance of the flights

**Who will use it?**

- Airport staffing and management companies and authorities will use it
- Hotels close to or in airports could also use it for facilitation of guests.
- It is ultimately designed to benefit air passengers at airports
- It will simultaneously benefit airport operations authorities too by increasing passenger-handling efficiency

**Why is this project of interest to you?**

- It particularly interests me as I have a long-held interest in planes and passion for aviation

## User Stories

- As a user, I shall be able to add an ArrivingFlight to the FlightDisplay
- As a user, I shall be able to add a DepartingFlight to the FlightDisplay
- As a user, I shall be able to update the arrival and departure status and estimated arrival and departure times 
  of individual flights
- As a user, I shall be able to remove a Flight (Departing&Arriving) from the list of flights displayed on the
 FlightDisplay
- As a user, I want to be able to add and remove emergency passenger alerts in a separate section on the FlightDisplay
  if needed
- As a user, I want to be able to save the entire state of the flight information display to file
- As a user, I want to be able to load a saved state of the flight information display from the last time it was run 
  from file and resume exactly from where left off.

  ## Phase 4 : Task 2

Below is a representative sample of the EventLog when the Flight Information Display is closed:
```java
Thu Dec 01 13:34:52 PST 2022 Arriving Flight added.  

Thu Dec 01 13:34:52 PST 2022 Arriving Flight added.  

Thu Dec 01 13:34:52 PST 2022 Arriving Flight added.  

Thu Dec 01 13:34:52 PST 2022 Departing Flight added.  

Thu Dec 01 13:34:52 PST 2022 Departing Flight added.

Thu Dec 01 13:34:52 PST 2022 Departing Flight added.

Thu Dec 01 13:34:52 PST 2022 Emergency Alert added.

Thu Dec 01 13:34:52 PST 2022 Emergency Alert added.

Thu Dec 01 13:34:54 PST 2022 Arriving Flight removed.

Thu Dec 01 13:34:55 PST 2022 Arriving Flight removed.

Thu Dec 01 13:34:57 PST 2022 Departing Flight removed

Thu Dec 01 13:34:58 PST 2022 Departing Flight removed

Thu Dec 01 13:35:02 PST 2022 Emergency Alert removed.
```

