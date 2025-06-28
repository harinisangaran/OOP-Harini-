# Vehicle Rental System

## Project Title
Vehicle Rental System

## Group Members
- Anis Saffiya (A23CS)
- Harini A/P Sangaran (A23CS0081)

## Section
03

## Course Code and Name
SECJ2154 Object Oriented Programming

## Lecturer's Name
Dr. Muhammad Khatibsyarbini

---

## Brief Project Description
This project is all about creating a system that manages vehicle rental.  
There are two user points of view: **Customer** and **Admin**.

**Customers** can:
- Make a booking
- View their booking status via this system

**Admins** can:
- Add or remove vehicles
- View the list of vehicles and bookings
- Approve or disapprove bookings

---

## Implementation of Concepts in the System

1. **Inheritance**
   - Customers and Admin classes are extended from the class `User`, which shows inheritance behavior.

2. **Polymorphism**
   - The `displayRole()` function is abstract in `User` but is implemented differently in each subclass:
     ```java
     public void displayRole() { System.out.println("Role: Customer"); }
     public void displayRole() { System.out.println("Role: Admin"); }
     ```

3. **ArrayList**
   - Example usage:
     ```java
     static ArrayList<Vehicle> vehicles = new ArrayList<>();
     static ArrayList<Booking> bookings = new ArrayList<>();
     ```

4. **Exception Handling**
   - Example:
     ```java
     try {
         df.parse(dateTime);
     } catch (ParseException e) {
         System.out.println("Invalid date format.");
     }
     ```
   - If the date format is invalid (e.g., YYYY/DD/MM instead of DD/MM/YYYY), the system handles the error.

5. **Aggregation**
   - The `Booking` class HAS-A `Customer` and HAS-A `Vehicle`.
   - Bookings require a customer and vehicle, but customers and vehicles can exist without a booking.

6. **Association**
   - `Admin` uses `Booking` objects in its methods.
   - The `approveBooking()` and `disapproveBooking()` methods operate on `Booking`, representing an association.
  
---

The output of the system is in the *Output* folder.
