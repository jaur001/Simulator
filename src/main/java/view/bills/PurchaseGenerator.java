package view.bills;

import model.provider.Provider;
import model.restaurant.Restaurant;

public interface PurchaseGenerator {

    void generatePurchase(Restaurant restaurant, Provider provider);
}
