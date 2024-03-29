/*
    * The Single Responsibility Principle (SRP) is one of the five SOLID principles of object-oriented design 
    * and programming. SRP states that a class should have only one reason to change, meaning it should have 
    * only one job or responsibility. This principle leads to a better organization of code, making it 
    * more modular, easier to understand, maintain, and test.
*/

// <==================================================================================>
//Example 1 : User Management System

//Problematic Code

public class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void saveUser() {
        // Save the user to a database
        System.out.println("User saved to the database");
    }

    public void sendEmailVerification() {
        // Send an email verification
        System.out.println("Email verification sent");
    }
}

//Better Code

public class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getter and setter methods
}

public class UserRepository {
    public void saveUser(User user) {
        // Save the user to a database
        System.out.println("User saved to the database");
    }
}

public class EmailService {
    public void sendEmailVerification(User user) {
        // Send an email verification
        System.out.println("Email verification sent");
    }
}
// <==================================================================================>

// Example 2 : Report Generation and Printing

//Problematic Code
public class Report {
    public void generateReport() {
        // Generate report
        System.out.println("Report generated");
    }

    public void printReport() {
        // Print the report
        System.out.println("Report printed");
    }
}

//Better Code

public class Report {
    public void generateReport() {
        // Generate report
        System.out.println("Report generated");
    }
}

public class ReportPrinter {
    public void printReport(Report report) {
        // Print the report
        System.out.println("Report printed");
    }
}
// <==================================================================================>

//Example 3: Employee Management

//Problematic Code

public class Employee {
    private String name;
    private String department;

    // Constructor, getters and setters

    public void calculatePay() {
        // Calculate pay
    }

    public void saveEmployee() {
        // Save employee details to database
    }

    public void generateEmployeeReport() {
        // Generate report for the employee
    }
}


//Better Code

public class Employee {
    private String name;
    private String department;

    // Constructor, getters, and setters
}

public class Payroll {
    public void calculatePay(Employee employee) {
        // Calculate pay
    }
}

public class EmployeeRepository {
    public void saveEmployee(Employee employee) {
        // Save employee details to database
    }
}

public class ReportGenerator {
    public void generateEmployeeReport(Employee employee) {
        // Generate report for the employee
    }
}

// <==================================================================================>

//Example 4 : Order Processing

//Problematic Code

public class Order {
    public void calculateTotalSum() { /*...*/ }
    public void getItems() { /*...*/ }
    public void getItemCount() { /*...*/ }
    public void addItem(Item item) { /*...*/ }
    public void deleteItem(Item item) { /*...*/ }

    public void printOrder() { /*...*/ }
    public void showOrder() { /*...*/ }

    public void loadOrder() { /*...*/ }
    public void saveOrder() { /*...*/ }
    public void updateOrder() { /*...*/ }
    public void deleteOrder() { /*...*/ }
}

//Better Code

public class Order {
    public void calculateTotalSum() { /*...*/ }
    public void getItems() { /*...*/ }
    public void getItemCount() { /*...*/ }
    public void addItem(Item item) { /*...*/ }
    public void deleteItem(Item item) { /*...*/ }
}

public class OrderPersistence {
    public void loadOrder() { /*...*/ }
    public void saveOrder() { /*...*/ }
    public void updateOrder() { /*...*/ }
    public void deleteOrder() { /*...*/ }
}

public class OrderUI {
    public void printOrder(Order order) { /*...*/ }
    public void showOrder(Order order) { /*...*/ }
}

// <==================================================================================>

//Example 5 : User Settings Management

//Problematic Code

public class UserSettings {
    public void changeEmail(User user) { /* Change email */ }
    public void changeUsername(User user) { /* Change username */ }
    public void saveSettings(User user) { /* Save settings to a file or database */ }
    public void loadSettings(User user) { /* Load user settings */ }
}

//Better Code

public class UserSettings {
    public void changeEmail(User user) { /* Change email */ }
    public void changeUsername(User user) { /* Change username */ }
}

public class SettingsPersistence {
    public void saveSettings(User user) { /* Save settings to a file or database */ }
    public void loadSettings(User user) { /* Load user settings */ }
}
// <==================================================================================>




