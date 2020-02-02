package model.provider;

import model.financialData.ProviderFinancialData;

public class Provider {
    private Product product;
    private String name;
    private ProviderFinancialData data;
    private double productPrice;

    public Provider(Product product, String name, double productPrice) {
        this.product = product;
        this.name = name;
        this.productPrice = productPrice;
    }

    public Product getProduct() {
        return product;
    }

    public String getName() {
        return name;
    }

    public ProviderFinancialData getData() {
        return data;
    }

    public double getProductPrice() {
        return productPrice;
    }
}
