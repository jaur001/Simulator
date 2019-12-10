package main;

import model.restaurant.Restaurant;
import restaurantLoader.RestaurantLoader;
import restaurantLoader.TripAdvisorRestaurantLoader;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashSet;
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
        RestaurantLoader loader = new TripAdvisorRestaurantLoader();
        try {
            list = loader.load(i);
        } catch (IOException e) {
            System.out.println("Error: Time Out exception");
        }
    }

    public static List<Restaurant> mergeLists(RestaurantThread[] threads){
        int count = threads.length;
        List<Restaurant> auxList = new ArrayList<Restaurant>();
        for (int i = 0; i < count; i++) {
            threads[i] = new RestaurantThread(i);
            threads[i].start();
        }

        for (int i = 0; i < count; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < count; i++) {
            auxList.addAll(threads[i].getList());
        }
        List<Restaurant> finalList = new ArrayList<Restaurant>(new HashSet<Restaurant>(auxList));
        return finalList;
    }
}
