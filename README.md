# Project Name: Shopping Cart

# Group: SE-2215

# Team members
* Tungyshbai Alisher
* Zhumagali Dastan
* Nurzhanuly Yernur

## Project Overview
Welcome to the README file for the Shopping Cart project by our dedicated team. Below, we provide an overview of the project, detailing its idea, purpose, and objectives.

### Idea of the Project
Our project, the "Online Shopping Cart," is designed to facilitate a seamless online shopping experience. Users can add products to their cart, view the cart, and proceed to checkout using various payment methods. The system also includes employee-specific functionalities for product management.

### Purpose of the Work
The purpose of our work is to create a robust and user-friendly online shopping platform. Our objectives include implementing core features like product addition, cart management, and checkout, as well as incorporating design patterns and strategies for extensibility and maintainability.

## Main Body

### Features
1. Product Management (Employee Only):
   - Employees can add or remove products from the inventory.
   - Ensures secure access using an employee ID.

2. Shopping Cart Operations:
   - Users can add products to their cart.
   - View and manage the cart with options to remove products.
   - Real-time notifications for order updates.

3. Checkout with Payment Strategies:
   - Supports different payment methods: Credit Card, PayPal, and Cash on Delivery.
   - Decorates products with extended warranty options during checkout.

### Design Patterns
## 1. Singleton Pattern
The Singleton pattern ensures that a class has only one instance and provides a global point to this instance.

Example:

```java
public class ShoppingCart {
    private static final ShoppingCart instance = new ShoppingCart();

    private ShoppingCart() {
        items = new ArrayList<>();
        observers = new ArrayList<>();
    }

   public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    // Other methods and functionalities...
}
```
In the example, the ShoppingCart class is a Singleton, allowing only one instance to be created and providing a global point of access.

## 2. Strategy Pattern
The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable.

Example:

```java
public interface PaymentStrategy {
    void pay(double amount);
}
```

```java
public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        // Implement payment with credit card logic.
        System.out.println("Paid $" + amount + " with Credit Card.");
    }
}

// Other payment strategy implementations: PayPalPayment, CashOnDeliveryPayment
```
In the example, the PaymentStrategy interface defines a common method, and concrete classes (e.g., CreditCardPayment) implement specific payment strategies.

## 3. Adapter Pattern
The Adapter pattern allows the interface of an existing class to be used as another interface.

Example:

```java
public interface Product {
    String getName();
    double getPrice();
}
```
```java
public class ProductAdapter implements Product {
    private ExternalProduct externalProduct;

    public ProductAdapter(ExternalProduct externalProduct) {
        this.externalProduct = externalProduct;
    }

    @Override
    public String getName() {
        return externalProduct.getTitle();
    }

    @Override
    public double getPrice() {
        return externalProduct.getCost();
    }
}
```
In the example, ProductAdapter adapts ExternalProduct to the Product interface.

## 4. Decorator Pattern
The Decorator pattern attaches additional responsibilities to an object dynamically.

Example:
```java
public abstract class ProductDecorator implements Product {
    private Product decoratedProduct;

    public ProductDecorator( Product decoratedProduct) {
        this.decoratedProduct = decoratedProduct;
    }

    @Override
    public String getName() {
        return decoratedProduct.getName();
    }

    @Override
    public double getPrice() {
        return decoratedProduct.getPrice();
    }
}

```
```java
public class ExtendedWarrantyDecorator extends ProductDecorator {
    public ExtendedWarrantyDecorator(Product decoratedProduct) {
        super(decoratedProduct);
    }

    @Override
    public String getName() {
        return super.getName() + " with Extended Warranty";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 9.99;
    }
}
```
In the example, ExtendedWarrantyDecorator enhances a product's name and price with an extended warranty.

## 5. Observer Pattern
The Observer pattern defines a one-to-many dependency between objects, where one object changes state, and its dependents are notified and updated automatically.

Example:
```java
public interface CartObserver {
    void update();
}
```
```java
public class NotificationService implements CartObserver {
    private List<String> notifications;

    public void addNotification(String notification) {
        notifications.add(notification);
    }

    @Override
    public void update() {
        // Implementation to send notifications to users
    }
}
```
In the example, NotificationService acts as an observer, receiving updates from the shopping cart.

## 6. Factory Pattern
The Factory pattern defines an interface for creating an object but allows subclasses to alter the type of objects that will be created.

Example:

```java
public interface ProductFactory {
    Product createProduct(String name, double price);
}
```
```java
public class ConcreteProductFactory implements ProductFactory {
    @Override
    public Product createProduct(String name, double price) {
        return new ConcreteProduct(name, price);
    }
}
```
In the example, ConcreteProductFactory implements the ProductFactory interface to create concrete products.

### UML Diagram
![UML Diagram](path/to/your/uml/diagram.png)
*Add a brief description of the UML diagram and how it represents the structure of the design patterns used in your project.*

## Conclusion
In conclusion, our project successfully provides a comprehensive online shopping experience. Key points include the implementation of the observer, decorator, and factory patterns to enhance extensibility and maintainability.

### Project Outcomes
Our project has resulted in a functional online shopping cart system. We faced challenges in [mention challenges], but collaborative efforts led to successful solutions.

### Future Improvements
Future improvements may include:
- Enhanced user interface and experience.
- Integration with external APIs for more product options.
- Further optimization of code and performance.
