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
/*
    Why it is problematic: The User class violates SRP by handling both user properties management and 
    user-related operations like saving to a database and sending email verifications. Changes in the database
    schema or the email sending process would require modifications to the User class, making it 
    less cohesive and more prone to errors.
 */

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

/*
    Why it is better: The responsibilities are now divided among three classes: User handles user properties,
    UserRepository deals with database operations, and EmailService takes care of sending emails. 
    This separation makes the system more modular, easier to maintain, and adheres to SRP.
 */

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

/*
   Why it is problematic: The Report class is responsible for both generating and printing reports. 
   If the method of report generation or the printing mechanism changes, the Report class must be modified, 
   violating SRP.
*/

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

/*
   Why it is better: This version separates the responsibilities into two classes: Report for report 
   generation and ReportPrinter for printing. This makes each class adhere to a single responsibility, 
   simplifying maintenance and scalability.
*/
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

/*
   Why it is problematic: The Employee class is handling multiple responsibilities: managing employee 
   properties, calculating pay, persisting employee details, and generating reports. This violates SRP 
   and makes the class complex and harder to maintain.
*/


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

/*
   Why it is better: This approach distributes the responsibilities across four classes, each handling 
   a specific aspect of employee management. It adheres to SRP, making the system more modular, 
   easier to understand, and maintain.
*/

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

/*
   Why it is problematic: The Order class is overloaded with responsibilities, from managing order items 
   and calculating totals to persisting orders and presenting them. This makes the class complex and 
   challenging to maintain.
*/

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

/*
   Why it is better: This design separates the concerns into distinct classes: Order for managing order data, 
   OrderPersistence for database operations, and OrderUI for user interface operations. Each class now 
   adheres to SRP, simplifying modifications and testing.
*/

// <==================================================================================>

//Example 5 : User Settings Management

//Problematic Code

public class UserSettings {
    public void changeEmail(User user) { /* Change email */ }
    public void changeUsername(User user) { /* Change username */ }
    public void saveSettings(User user) { /* Save settings to a file or database */ }
    public void loadSettings(User user) { /* Load user settings */ }
}

/*
   Why it is problematic: UserSettings manages both the modification of user settings and the persistence 
   of these settings. Changes in how settings are changed or how they're saved/loaded would affect this class,
   violating SRP.
*/

//Better Code

public class UserSettings {
    public void changeEmail(User user) { /* Change email */ }
    public void changeUsername(User user) { /* Change username */ }
}

public class SettingsPersistence {
    public void saveSettings(User user) { /* Save settings to a file or database */ }
    public void loadSettings(User user) { /* Load user settings */ }
}

/*
   plitting the responsibilities into UserSettings for managing the settings and SettingsPersistence for 
   handling the saving/loading of settings adheres to SRP. It simplifies each class, making them easier 
   to maintain and extend.
*/
// <==================================================================================>




