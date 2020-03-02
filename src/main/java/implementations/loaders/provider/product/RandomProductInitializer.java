package implementations.loaders.provider.product;

import model.provider.Product;
import utils.MathUtils;
import view.loaders.provider.product.ProductInitializer;

public class RandomProductInitializer implements ProductInitializer {
    @Override
    public Product init() {
        return Product.values()[MathUtils.random(0,Product.values().length)];
    }
}
