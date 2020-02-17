package view.loaders.restaurant;

import model.restaurant.Restaurant;

import java.util.List;

public interface DatabaseRestaurantWriter {
    void write(List<Restaurant> restaurantList);
}
