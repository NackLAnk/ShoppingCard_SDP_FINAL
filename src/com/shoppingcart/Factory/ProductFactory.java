package com.shoppingcart.Factory;

import com.shoppingcart.adapter.ProductAdapter;

public interface ProductFactory {
    ProductAdapter createProduct(String name, double price);
}
