package main;

import model.restaurant.Restaurant;
import webscrapper.TripAdvisorRestaurantLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantThread extends Thread{
    private List<Restaurant> list = new ArrayList<Restaurant>();
    private int i;

    public RestaurantThread(int i) {
        this.i = i;
    }

    public List<Restaurant> getList() {
        return list;
    }

    @Override
    public void run(){
        TripAdvisorRestaurantLoader loader = new TripAdvisorRestaurantLoader();
        try {
            list = loader.load(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
