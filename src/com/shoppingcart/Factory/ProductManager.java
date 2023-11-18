package com.shoppingcart.Factory;

import java.util.ArrayList;
import java.util.List;
import com.shoppingcart.adapter.Product;
import com.shoppingcart.adapter.ConcreteProduct;
public class ProductManager {
    private List<Product> products;

    public ProductManager() {
        this.products = new ArrayList<>();
        initProducts();
    }

    private void initProducts() {
        products.addAll(List.of(
                new ConcreteProduct("Apples", 2.99),
                new ConcreteProduct("Milk", 1.99),
                new ConcreteProduct("Bread", 3.49),
                new ConcreteProduct("Eggs", 2.49),
                new ConcreteProduct("Bananas", 1.79),
                new ConcreteProduct("Cheese", 4.99),
                new ConcreteProduct("Tomatoes", 2.29),
                new ConcreteProduct("Chicken", 6.99),
                new ConcreteProduct("Potatoes", 1.99),
                new ConcreteProduct("Onions", 1.49)
        ));
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
    }

    public void removeProduct(String productName) {
        products.removeIf(product -> product.getName().equalsIgnoreCase(productName));
    }
}

