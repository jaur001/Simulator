package view.loaders.restaurant;

import model.restaurant.Restaurant;

import java.io.IOException;
import java.util.List;

public interface RestaurantLoader {
    List<Restaurant> load(int pages) throws IOException;
}
