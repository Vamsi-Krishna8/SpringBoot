/*
 * The Open-Closed Principle (OCP) is one of the five SOLID principles of object-oriented design, 
 * which states that software entities (classes, modules, functions, etc.) should be open for extension but 
 * closed for modification. This means you should be able to add new functionality to an entity without 
 * changing its existing code. Achieving this often involves using interfaces or abstract classes, 
 * allowing behavior to be extended by adding new classes that implement those interfaces or inherit 
 * from those classes.
 */

// <==================================================================================>
// Example 1: Shape Area Calculator

// Problematic Code


public class AreaCalculator {
    public double calculateRectangleArea(Rectangle r) {
        return r.length * r.width;
    }

    public double calculateCircleArea(Circle c) {
        return Math.PI * c.radius * c.radius;
    }
}
/*
    Why it is problematic: The AreaCalculator class must be modified every time you need to calculate the 
    area of a new shape, violating OCP.
*/

// Better Version

public interface Shape {
    double calculateArea();
}

public class Rectangle implements Shape {
    private double length;
    private double width;

    // Constructor, getters, and setters

    @Override
    public double calculateArea() {
        return length * width;
    }
}

public class Circle implements Shape {
    private double radius;

    // Constructor, getters, and setters

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

public class AreaCalculator {
    public double calculateShapeArea(Shape shape) {
        return shape.calculateArea();
    }
}
/*
    Why it is better: By defining a Shape interface with a calculateArea method and implementing this 
    interface in various shape classes, AreaCalculator now works with any shape that implements Shape, 
    adhering to OCP. This design allows new shapes to be added without modifying existing calculator logic.
*/

// <==================================================================================>

//Example 2 Discount Calculator

// Problematic Code


public class DiscountCalculator {
    public double calculateDiscount(String type, double amount) {
        if (type.equals("FIXED")) {
            return amount - 50;
        } else if (type.equals("PERCENTAGE")) {
            return amount - amount * 0.1;
        }
        return amount;
    }
}

/*
    Why it is problematic: Adding a new discount type requires modifying the DiscountCalculator class, 
    violating OCP.
*/

// Better version


public interface DiscountStrategy {
    double applyDiscount(double amount);
}

public class FixedDiscountStrategy implements DiscountStrategy {
    public double applyDiscount(double amount) {
        return amount - 50;
    }
}

public class PercentageDiscountStrategy implements DiscountStrategy {
    public double applyDiscount(double amount) {
        return amount - amount * 0.1;
    }
}

public class DiscountCalculator {
    public double calculateDiscount(DiscountStrategy discountStrategy, double amount) {
        return discountStrategy.applyDiscount(amount);
    }
}

/*
    Why it is better: The DiscountCalculator now relies on a DiscountStrategy interface, allowing new 
    discount strategies to be added without changing the calculator's code. This adheres to OCP, as the system
    is open for extension but closed for modification.
*/

// <==================================================================================>

//Example 3 :Logging Mechanism 

// Problematic Code


public class Logger {
    public void log(String message, String logType) {
        if (logType.equals("console")) {
            System.out.println(message);
        } else if (logType.equals("file")) {
            // Code to log to a file
        }
        // More conditions for other log types
    }
}

/*
 * Why it is problematic: Adding a new logging destination requires modifying the Logger class, violating OCP.
 */

// <==================================================================================>

// Better Version

public interface LogStrategy {
    void log(String message);
}

public class ConsoleLogStrategy implements LogStrategy {
    public void log(String message) {
        System.out.println(message);
    }
}

public class FileLogStrategy implements LogStrategy {
    public void log(String message) {
        // Code to log to a file
    }
}

public class Logger {
    private LogStrategy logStrategy;

    public Logger(LogStrategy logStrategy) {
        this.logStrategy = logStrategy;
    }

    public void log(String message) {
        logStrategy.log(message);
    }
}

// <==================================================================================>

// Example 4: Payment Processing System

// Problematic Code


public class PaymentProcessor {
    public void processPayment(String paymentType, double amount) {
        if (paymentType.equals("credit")) {
            // Process credit payment
        } else if (paymentType.equals("paypal")) {
            // Process PayPal payment
        }
        // Additional conditions for other payment types
    }
}

/*
    Why it is problematic: Adding a new payment method requires changes to the PaymentProcessor class, 
    violating OCP.
*/

//Better version

public interface PaymentMethod {
    void processPayment(double amount);
}

public class CreditPaymentMethod implements PaymentMethod {
    public void processPayment(double amount) {
        // Process credit payment
    }
}

public class PaypalPaymentMethod implements PaymentMethod {
    public void processPayment(double amount) {
        // Process PayPal payment
    }
}

public class PaymentProcessor {
    public void processPayment(PaymentMethod paymentMethod, double amount) {
        paymentMethod.processPayment(amount);
    }
}

/*
    Why it is better: The PaymentProcessor now operates on a PaymentMethod interface, allowing new payment 
    methods to be added as new implementations. This approach adheres to OCP, facilitating easy extensions 
    without modifying existing code.
*/
// <==================================================================================>

// Example 5: User Notification System

// Problematic Code

public class NotificationService {
    public void sendNotification(String userType, String message) {
        if (userType.equals("email")) {
            // Send email notification
        } else if (userType.equals("sms")) {
            // Send SMS notification
        }
        // More conditions for other notification types
    }
}

/*
    Why it is problematic: Introducing a new notification type means altering the NotificationService, 
    violating OCP.
*/

// Better Version


public interface NotificationChannel {
    void sendNotification(String message);
}

public class EmailNotification implements NotificationChannel {
    public void sendNotification(String message) {
        // Send email notification
    }
}

public class SmsNotification implements NotificationChannel {
    public void sendNotification(String message) {
        // Send SMS notification
    }
}

public class NotificationService {
    public void sendNotification(NotificationChannel channel, String message) {
        channel.sendNotification(message);
    }
}

/*
    Why it is better: NotificationService now depends on a NotificationChannel interface, allowing the 
    notification mechanism to be extended by implementing new channels. This design adheres to OCP, 
    making the system more flexible and easier to extend without modifying existing code.
*/

// <==================================================================================>