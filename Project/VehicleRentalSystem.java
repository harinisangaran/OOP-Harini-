import java.text.*;
import java.util.*;

// Superclass: shows INHERITANCE
abstract class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public abstract void displayRole(); // POLYMORPHISM
}

// Customer class
class Customers extends Person {
    private String icNumber;
    private String mobileNumber;

    public Customers(String name, String icNumber, String mobileNumber) {
        super(name);
        this.icNumber = icNumber;
        this.mobileNumber = mobileNumber;
    }

    public void displayRole() {
        System.out.println("Role: Customer");
    }

    public String getIcNumber() {
        return icNumber;
    }

    public String getDetails() {
        return "Name: " + name + ", IC: " + icNumber + ", Mobile: " + mobileNumber;
    }
}

// Vehicle class
class RentalVehicle {
    private String id;
    private String model;
    private boolean available;

    public RentalVehicle(String id, String model) {
        this.id = id;
        this.model = model;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean status) {
        available = status;
    }

    public String toString() {
        return id + " - " + model + " (" + (available ? "Available" : "Booked") + ")";
    }
}

// Booking class
class Booking {
    private Customers customer;
    private RentalVehicle vehicle;
    private String dateTime;
    private boolean approved;

    public Booking(Customers customer, RentalVehicle vehicle, String dateTime) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.dateTime = dateTime;
        this.approved = false;
    }

    public Customers getCustomer() {
        return customer;
    }

    public RentalVehicle getVehicle() {
        return vehicle;
    }

    public String getDateTime() {
        return dateTime;
    }

    public boolean isApproved() {
        return approved;
    }

    public void approve() {
        approved = true;
    }

    public void disapprove() {
        approved = false;
    }

    public String toString() {
        return "Booking: " + vehicle.getModel() + " for " + customer.getDetails() + " on " + dateTime + " [" +
                (approved ? "Approved" : "Pending") + "]";
    }
}

// Admin class
class Admin extends Person {
    public Admin(String name) {
        super(name);
    }

    public void displayRole() {
        System.out.println("Role: Admin");
    }

    public void approveBooking(Booking booking) {
        booking.approve();
        booking.getVehicle().setAvailable(false);
        System.out.println("Booking approved.");
    }

    public void disapproveBooking(Booking booking) {
        booking.disapprove();
        System.out.println("Booking disapproved.");
    }
}

// Main system
public class VehicleRentalSystem {
    static ArrayList<RentalVehicle> vehicles = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        vehicles.add(new RentalVehicle("V001", "Toyota Vios"));
        vehicles.add(new RentalVehicle("V002", "Honda Civic"));
        vehicles.add(new RentalVehicle("V003", "Perodua Myvi"));

        while (true) {
            System.out.println("\nWelcome to the Vehicle Rental System!");
            System.out.println("Are you a Customer or Admin?");
            System.out.println("Type 'C' for Customer, 'A' for Admin, or '-1' to Exit.");
            System.out.print("Your choice: ");
            String role = scanner.nextLine().trim();

            if (role.equals("-1")) {
                System.out.println("Goodbye!");
                break;
            }

            if (role.equalsIgnoreCase("C")) {
                customerMenu();
            } else if (role.equalsIgnoreCase("A")) {
                adminFlow();
            } else {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    // New menu for customer actions
    private static void customerMenu() {
        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Make a booking");
            System.out.println("2. View my booking status");
            System.out.println("3. Back to main menu");
            System.out.print("Select option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    makeBooking();
                    break;
                case "2":
                    viewBookingsByIC();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void makeBooking() {
        try {
            System.out.print("Enter rental date and time (dd/MM/yyyy HH:mm): ");
            String dateTime = scanner.nextLine();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            df.setLenient(false);
            df.parse(dateTime);

            ArrayList<RentalVehicle> availableVehicles = new ArrayList<>();
            for (RentalVehicle v : vehicles) {
                if (v.isAvailable()) {
                    availableVehicles.add(v);
                }
            }

            if (availableVehicles.isEmpty()) {
                System.out.println("No vehicles available.");
                return;
            }

            System.out.println("Available Vehicles:");
            for (int i = 0; i < availableVehicles.size(); i++) {
                System.out.println((i + 1) + ". " + availableVehicles.get(i));
            }

            System.out.print("Select vehicle number to book: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice < 1 || choice > availableVehicles.size()) {
                System.out.println("Invalid selection.");
                return;
            }

            RentalVehicle selectedVehicle = availableVehicles.get(choice - 1);

            System.out.print("Your name: ");
            String name = scanner.nextLine();
            System.out.print("IC number: ");
            String ic = scanner.nextLine();
            System.out.print("Mobile number: ");
            String mobile = scanner.nextLine();

            Customers customer = new Customers(name, ic, mobile);
            Booking booking = new Booking(customer, selectedVehicle, dateTime);

            bookings.add(booking);

            System.out.println("Booking submitted. Status: Pending approval.");

        } catch (ParseException e) {
            System.out.println("Invalid date format. Please follow dd/MM/yyyy HH:mm.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number entered.");
        }
    }

    private static void viewBookingsByIC() {
        System.out.print("Enter your IC number to view bookings: ");
        String ic = scanner.nextLine();
        boolean found = false;

        for (Booking b : bookings) {
            if (b.getCustomer().getIcNumber().equals(ic)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No bookings found for this IC number.");
        }
    }

    private static void adminFlow() {
        Admin admin = new Admin("System Admin");

        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. List Vehicles");
            System.out.println("4. View Bookings");
            System.out.println("5. Approve/Disapprove Booking");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose option: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.print("Vehicle ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Model: ");
                    String model = scanner.nextLine();
                    vehicles.add(new RentalVehicle(id, model));
                    System.out.println("Vehicle added.");
                    break;
                case "2":
                    System.out.print("Enter Vehicle ID to remove: ");
                    String removeId = scanner.nextLine();
                    boolean removed = vehicles.removeIf(v -> v.getId().equals(removeId));
                    System.out.println(removed ? "Vehicle removed." : "Vehicle not found.");
                    break;
                case "3":
                    System.out.println("Vehicles:");
                    for (RentalVehicle v : vehicles) {
                        System.out.println(v);
                    }
                    break;
                case "4":
                    if (bookings.isEmpty()) {
                        System.out.println("No bookings.");
                    } else {
                        for (int i = 0; i < bookings.size(); i++) {
                            System.out.println((i + 1) + ". " + bookings.get(i));
                        }
                    }
                    break;
                case "5":
                    if (bookings.isEmpty()) {
                        System.out.println("No bookings to process.");
                        break;
                    }
                    System.out.print("Enter booking number: ");
                    int num = Integer.parseInt(scanner.nextLine());
                    if (num < 1 || num > bookings.size()) {
                        System.out.println("Invalid booking number.");
                        break;
                    }
                    Booking b = bookings.get(num - 1);
                    System.out.print("Approve (A) or Disapprove (D): ");
                    String decision = scanner.nextLine().toUpperCase();
                    if (decision.equals("A")) {
                        admin.approveBooking(b);
                    } else if (decision.equals("D")) {
                        admin.disapproveBooking(b);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
