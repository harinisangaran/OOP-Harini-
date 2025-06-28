Project Title : Vehicle Rental System
Group Members : Anis Saffiya (A23CS)
                Harini A/P Sangaran (A23CS0081)
Section : 03

Course Code and Name : SECJ2154 Object Oriented Programming
Lecturer's Name : Dr Muhammad Khatibsyarbini

----------------------------------------------------------------------------------------------

Brief Project Description : This project is all about creating a system that manages vehicle rental. There are 2 users point of view, which are customer and admin. Customers will be able to make a booking and view their booking status via this system. As for the admin, they can add or remove vehicle, view list of vehicles and bookings, and finally, approve or disapprove the booking. 

Implementation of Concepts in the System : 1. Inheritance
                                              - Customers and Admin classes are extended from the class User which shows the inheritance behaviour.
                                           2. Polymorphism
                                              - The displayRole() function is abstract in User but is implemented differently in each subclass. For example :
                                              - In Customers class :
                                                  public void displayRole() { System.out.println("Role: Customer"); }
                                              - In Admin class :
                                                  public void displayRole() { System.out.println("Role: Admin"); }
                                           3. ArrayList
                                              - These codes shows the implementation of ArrayList : 
                                                   static ArrayList<Vehicle> vehicles = new ArrayList<>();
                                                   static ArrayList<Booking> bookings = new ArrayList<>();
                                           4. Exception Handling 
                                                try {
                                                    df.parse(dateTime);
                                                } catch (ParseException e) {
                                                    System.out.println("Invalid date format.");
                                                }
                                              - The code catch(ParseException e) {} will run if there's any other format than the required format input is given. For example, the date format for the system is DD/MM/YYYY HH:MM. If the user enters YYYY/DD/MM HH:MM format, the system will run the exception handling code.
                                           5. Aggregation
                                              - Booking class HAS-A Customer and HAS-A Vehicle.
                                              - Booking can only exist if there's a customer and vehicle but customer and vehicle can exist without a booking. Thus, it proves that Booking class has aggregation relationship with both Customers class and RentalVehicle class
                                           6. Association
                                              - Admin uses Booking objects in its methods.
                                              - The approveBooking() and disapproveBooking() methods operate on Booking, representing an association
