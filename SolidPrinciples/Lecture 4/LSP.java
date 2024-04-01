/*
    The Liskov Substitution Principle (LSP) is an important concept in object-oriented programming, emphasizing that 
    objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program. 
    Essentially, it means subclasses must be substitutable for their base classes. This principle ensures that a subclass 
    can stand in for its superclass without leading to unexpected behaviors. 
*/

// Example 1: Shape Area Calculation

// Problematic Code

class Rectangle {
    protected int width, height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}

class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setWidth(height);
        super.setHeight(height);
    }
}

/*
    Why it is problematic: A Square is not substitutable for a Rectangle because changing the width of a supposed 
    rectangle (when it's actually a square) changes its height, which violates the expectations for a rectangle's behavior.
*/

// Better Version

interface Shape {
    int getArea();
}

class Rectangle implements Shape {
    protected int width, height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getArea() {
        return width * height;
    }
}

class Square implements Shape {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public int getArea() {
        return side * side;
    }
}

/*
    Why it is better: By introducing a Shape interface with an getArea() method, both Rectangle and Square can be used interchangeably
    by clients expecting a Shape, without the clients needing to know the specific type of shape. This adheres to LSP because it 
    ensures that subclasses (Rectangle and Square) are directly substitutable for their base class or interface (Shape).
*/

// Example 2: Bird Flight

// Problematic Code

class Bird {
    public void fly() {}
}

class Duck extends Bird {}

class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostriches cannot fly");
    }
}

/*
    Why it is problematic: An Ostrich is a Bird, but it cannot fly, making it not substitutable for its superclass in 
    contexts where flying is expected. Using an Ostrich object where a flying Bird is expected can lead to runtime errors.
*/

// Better Version

interface Bird {}

interface FlyingBird {
    void fly();
}

class Duck implements Bird, FlyingBird {
    @Override
    public void fly() {}
}

class Ostrich implements Bird {}

/*
    Why it is better: This design separates the concept of a Bird from the ability to fly, with a specific FlyingBird interface 
    for birds that can fly. An Ostrich is no longer forced to implement a fly method that doesn't apply, and a Duck, which can 
    fly, implements the FlyingBird interface. This approach adheres to LSP by ensuring that all classes implementing a 
    given interface can be used interchangeably without causing incorrect behavior.
*/


// Example 3: User Authentication

// Problematic Code


class User {
    public boolean checkAccess() {
        // Check user access
        return true;
    }
}

class GuestUser extends User {
    @Override
    public boolean checkAccess() {
        throw new IllegalStateException("Guest users don't have access");
    }
}

/*
    Why it is problematic: The GuestUser class, by throwing an exception in checkAccess(), violates the LSP because it behaves 
    differently from what is expected of a User object, making GuestUser not substitutable for User in contexts where 
    checkAccess is called.
*/

// Better Version

interface User {
    boolean checkAccess();
}

class RegularUser implements User {
    @Override
    public boolean checkAccess() {
        // Check access for a regular user
        return true;
    }
}

class GuestUser implements User {
    @Override
    public boolean checkAccess() {
        // Guest users have different access rights
        return false;
    }
}

/*
    Why it is better: By treating User as an interface and not enforcing GuestUser to inherit behaviors it cannot fulfill 
    (like throwing an exception), we ensure that both RegularUser and GuestUser can be used interchangeably by clients expecting 
    a User. This design adheres to LSP by maintaining the substitutability of subclasses (or implementers) 
    for the parent interface.
*/

// Example 4: Payment Processing

// Problematic Code


class Payment {
    public void processPayment() {
        // process payment
    }
}

class CreditCardPayment extends Payment {}

class FreeTrialPayment extends Payment {
    @Override
    public void processPayment() {
        throw new UnsupportedOperationException("Free trials don't process payments");
    }
}

/*
    Why it is problematic: FreeTrialPayment cannot fulfill the contract of Payment.processPayment because free trials don't 
    involve an actual payment process,leading to a violation of LSP when a FreeTrialPayment is used in place of a Payment.
*/

// Better Version


interface Payment {
    void processPayment();
}

class CreditCardPayment implements Payment {
    @Override
    public void processPayment() {
        // Process credit card payment
    }
}

class FreeTrialPayment implements Payment {
    @Override
    public void processPayment() {
        // Log free trial activation without processing a payment
    }
}

/*
    Why it is better: This design ensures that FreeTrialPayment can fulfill the processPayment method in a way that's c
    onsistent with its purpose (e.g., logging trial activation), preserving the substitutability principle. By separating 
    the interfaces and possibly introducing more specialized interfaces or methods, each class can adhere to LSP without 
    misrepresenting its capabilities.
*/

// Example 5: Engine and Start Mechanism

// Problematic Code

class Engine {
    public void startEngine() {
        // Start the engine
    }
}

class ElectricEngine extends Engine {
    @Override
    public void startEngine() {
        throw new UnsupportedOperationException("Electric engines start differently");
    }
}

/*
    Why it is problematic: ElectricEngine overriding startEngine to throw an exception violates LSP because it changes 
    the expected behavior of Engine's startEngine method, making ElectricEngine not truly substitutable for Engine.
*/

// Better Version


interface Engine {
    void startEngine();
}

class CombustionEngine implements Engine {
    @Override
    public void startEngine() {
        // Start combustion engine
    }
}

class ElectricEngine implements Engine {
    @Override
    public void startEngine() {
        // Start electric engine with a different mechanism
    }
}

/*
    Why it is better: In this version, both CombustionEngine and ElectricEngine fulfill the startEngine contract 
    in ways appropriate to their types, adhering to LSP. This design allows objects of Engine type 
    (whether CombustionEngine or ElectricEngine) to be used interchangeably without altering the correctness of the program, 
    ensuring that extending functionality does not lead to violations of expected behaviors.
*/