package implementations.loaders.provider.product;

import model.provider.Product;
import utils.ProviderUtils;
import view.loaders.provider.product.ProductPriceInitializer;

public class DistributionProductPriceInitializer implements ProductPriceInitializer {
    @Override
    public double getPrice(Product product) {
        return ProviderUtils.getProductCost(product);
    }
}
