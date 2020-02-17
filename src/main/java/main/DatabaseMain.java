package main;

import implementations.loaders.restaurant.SQLite.SQLRestaurantWriter;
import implementations.loaders.restaurant.SQLite.SQLiteRestaurantLoader;
import model.restaurant.Restaurant;
import threads.initializers.RestaurantThread;

import java.util.ArrayList;
import java.util.List;

public class DatabaseMain {

    public static void main(String[] args) {
        int count = 1;
        String url = "jdbc:sqlite:Simulator.db";
        SQLRestaurantWriter.setSQLiteUrl(url);
        SQLiteRestaurantLoader.setSQLiteUrl(url);
        List<Restaurant> restaurantList = new SQLiteRestaurantLoader().load(300);
    }
}
