package view.loaders.restaurant;

import model.restaurant.Restaurant;
import org.jsoup.nodes.Document;


public interface RestaurantDataReader {
    Restaurant readData(Object document);
}
