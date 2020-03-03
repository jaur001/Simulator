package threads.initializers.provider;

import implementations.loaders.provider.product.DistributionProductPriceInitializer;
import implementations.loaders.provider.product.RandomProductInitializer;
import model.provider.Provider;
import java.util.List;

public class ProductInitializerThread{


    public static void initProducts(List<Provider> providerList) {
        providerList.parallelStream().forEach(ProductInitializerThread::initProduct);
    }

    private static void initProduct(Provider provider) {
        provider.setProduct(new RandomProductInitializer().init());
        provider.setProductPrice(new DistributionProductPriceInitializer().getPrice(provider.getProduct()));
    }
}

