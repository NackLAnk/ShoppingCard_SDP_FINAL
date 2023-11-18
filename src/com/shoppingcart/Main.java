package com.shoppingcart;

import com.shoppingcart.adapter.Product;
import com.shoppingcart.strategy.CashOnDeliveryPayment;
import com.shoppingcart.strategy.CreditCardPayment;
import com.shoppingcart.strategy.PayPalPayment;
import com.shoppingcart.strategy.PaymentStrategy;
import com.shoppingcart.observer.NotificationService;
import com.shoppingcart.Factory.ConcreteProductFactory;
import com.shoppingcart.Factory.ProductManager;
import com.shoppingcart.decorator.ExtendedWarrantyDecorator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = ShoppingCart.getInstance();
        NotificationService notificationService = new NotificationService();
        ConcreteProductFactory cpf = new ConcreteProductFactory();
        ProductManager productManager = new ProductManager();

        System.out.println("Welcome to the Online Shopping Cart!");

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("0. Add a new product or delete a product (Employee Only!)");
            System.out.println("1. Add a product to the cart");
            System.out.println("2. View cart");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    System.out.print("Enter your employee ID:");
                    int employeeId = scanner.nextInt();
                    if (employeeId == 12345321) {
                        System.out.println("");
                        System.out.println("Welcome!");

                        System.out.println("0. <-- Return to main menu");
                        System.out.println("1. Add a product");
                        System.out.println("2. Remove a product");
                        System.out.print("Choose an action: ");
                        int actionChoice = scanner.nextInt();

                        switch (actionChoice) {
                            case 0: return;
                            case 1:
                                scanner.nextLine();
                                System.out.print("Please enter a name for the product: ");
                                String name = scanner.nextLine();
                                Double price = null;
                                while (price == null) {
                                    System.out.print("Please enter a price for the product: ");
                                    try {
                                        price = Double.parseDouble(scanner.nextLine());
                                        if (price <= 0) {
                                            System.out.println("Invalid input. Please enter a valid positive price.");
                                            price = null;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input. Please enter a valid numeric price.");
                                    }
                                }

                                Product product = cpf.createProduct(name, price);
                                productManager.addProduct(product);
                                break;

                            case 2:
                                System.out.print("Enter the name of the product to remove: ");
                                String productName = scanner.next();
                                productManager.removeProduct(productName);
                                System.out.println(productName + " removed from the products.");
                                break;

                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid employee ID. Permission denied.");
                    }
                    break;

                case 1:
                    addProductToCart(scanner, cart, productManager.getProducts());
                    break;
                case 2:
                    viewCart(scanner, cart, productManager.getProducts());
                    break;
                case 3:
                    checkout(cart, notificationService);
                    break;
                case 4:
                    System.out.println("Thank you for shopping with us!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        }
    }

    private static void addProductToCart(Scanner scanner, ShoppingCart cart, List<Product> products) {
        System.out.println("Available products:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " - $" + product.getPrice());
        }

        System.out.println("0. <-- Return to main menu");
        System.out.print("Enter the number of the product you want to add to the cart: ");
        int productNumber = scanner.nextInt();

        if (productNumber == 0) {
            return;
        }

        if (productNumber < 1 || productNumber > products.size()) {
            System.out.println("Invalid product number. Please try again.");
            return;
        }

        Product selectedProduct = products.get(productNumber - 1);
        cart.addProduct(selectedProduct);
        System.out.println(selectedProduct.getName() + " added to the cart.");
    }

    private static void viewCart(Scanner scanner, ShoppingCart cart, List<Product> products) {
        System.out.println("Your Shopping Cart:");
        System.out.println("Total: $" + cart.getTotal());
        List<Product> cartProducts = cart.getProducts();
        if (cartProducts.isEmpty()) {
            System.out.println("Your cart is empty.");
            System.out.println("Press Enter to return to the main menu.");
            scanner.nextLine();
            scanner.nextLine();
            return;
        } else {
            System.out.println("Products:");
            for (int i = 0; i < cartProducts.size(); i++) {
                Product product = cartProducts.get(i);
                System.out.println((i + 1) + ". " + product.getName() + " - $" + product.getPrice());
            }
            System.out.println((cartProducts.size() + 1) + ". Remove a product from the cart");
            System.out.println((cartProducts.size() + 2) + ". Exit cart");
        }

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > cartProducts.size() + 2) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }

        if (choice == cartProducts.size() + 1) {
            removeProductFromCart(scanner, cart, cartProducts);
        } else if (choice == cartProducts.size() + 2) {
            System.out.println("Exiting cart.");
        } else {
            Product selectedProduct = cartProducts.get(choice - 1);
            System.out.println(selectedProduct.getName() + " - $" + selectedProduct.getPrice() + " removed from the cart.");
            cart.removeProduct(selectedProduct);
        }
    }



    private static void removeProductFromCart(Scanner scanner, ShoppingCart cart, List<Product> products) {
        System.out.print("Enter the number of the product you want to remove from the cart: ");
        int productNumber = scanner.nextInt();

        if (productNumber < 1 || productNumber > products.size()) {
            System.out.println("Invalid product number. Please try again.");
            return;
        }

        Product selectedProduct = products.get(productNumber - 1);
        cart.removeProduct(selectedProduct);
        System.out.println(selectedProduct.getName() + " - $" + selectedProduct.getPrice() + " removed from the cart.");
    }

    private static void checkout(ShoppingCart cart, NotificationService notificationService) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Add products before checking out.");
            return;
        }
        System.out.println("0. <-- Return to main menu");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("3. Cash on Delivery");
        System.out.print("Select a payment method: ");
        Scanner scanner = new Scanner(System.in);
        int paymentChoice = scanner.nextInt();

        PaymentStrategy paymentStrategy;

        switch (paymentChoice) {
            case 0: return;
            case 1:
                paymentStrategy = new CreditCardPayment();
                break;
            case 2:
                paymentStrategy = new PayPalPayment();
                break;
            case 3:
                paymentStrategy = new CashOnDeliveryPayment();
                break;
            default:
                System.out.println("Invalid payment method. Please try again.");
                return;
        }

        paymentStrategy.pay(cart.getTotal());
        System.out.println("Payment successful. Thank you for your purchase!");
        System.out.println(" ");
        System.out.println("<-- Attention -->");
        List<Product> decoratedProducts = new ArrayList<>();
        for (Product product : cart.getProducts()) {
            Product productWithWarranty = new ExtendedWarrantyDecorator(product);
            decoratedProducts.add(productWithWarranty);
        }

        for (Product product : decoratedProducts) {
            System.out.println(" ");
            System.out.println("Product: " + product.getName());
            System.out.println("Refund Price: $" + product.getPrice() + ", Due to technical issues on our end.");
        }
        System.out.println(" ");
        notificationService.addNotification("Your order has been placed.");
        notificationService.addNotification("Payment received.");
        notificationService.addNotification("Shipped: ETA 1-2 days.");

        notificationService.update();
        System.exit(0);
    }
}