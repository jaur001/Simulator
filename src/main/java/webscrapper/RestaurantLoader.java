package webscrapper;

import model.restaurant.Restaurant;

import java.io.IOException;
import java.util.List;

public interface RestaurantLoader {
    List<Restaurant> load(int j) throws IOException;
}