package view.loaders.routine;

import model.client.Client;
import model.restaurant.Restaurant;

public interface AmountGenerator {
    double generate(Restaurant restaurant, Client client);
}
