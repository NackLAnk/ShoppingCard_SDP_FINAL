package com.shoppingcart.Factory;

import com.shoppingcart.adapter.ProductAdapter;

public class ConcreteProductFactory implements ProductFactory {
    @Override
    public ProductAdapter createProduct(String name, double price) {
        return new ProductAdapter(name, price);
    }
}
