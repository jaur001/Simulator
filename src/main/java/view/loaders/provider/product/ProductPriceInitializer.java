package view.loaders.provider.product;

import model.provider.Product;

public interface ProductPriceInitializer {
    double getPrice(Product product);
}
