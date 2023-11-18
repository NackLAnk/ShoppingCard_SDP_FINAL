package com.shoppingcart.adapter;

public class ProductAdapter implements Product {
    private ExternalProduct externalProduct;

    public ProductAdapter(String name, double price) {
        this.externalProduct = createExternalProduct(name, price);
    }

    @Override
    public String getName() {
        return externalProduct.getTitle();
    }

    @Override
    public double getPrice() {
        return externalProduct.getCost();
    }

    private ExternalProduct createExternalProduct(String name, double price) {
        return new ExternalProduct(name, price);
    }
}