package implementations.loaders.provider;

import model.provider.Product;
import model.provider.Provider;
import utils.MathUtils;
import view.loaders.provider.providing.ProvidingController;

import java.util.List;
import java.util.stream.Collectors;

public class RandomProvidingController implements ProvidingController {
    @Override
    public Provider provide(List<Provider> providerList, Product product) {
        List<Provider> providersWithTheProduct = providerList.stream()
                .filter(provider -> provider.getProduct().name().equals(product.name()))
                .collect(Collectors.toList());
        return providersWithTheProduct.get(MathUtils.random(0,providersWithTheProduct.size()));
    }
}
