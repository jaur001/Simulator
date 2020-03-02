package view.loaders.provider.providing;

import model.provider.Product;
import model.provider.Provider;

import java.util.List;

public interface ProvidingController {
    Provider provide(List<Provider> providerList, Product product);
}
