package main;

import threads.RestaurantThread;
import implementations.swing.MainFrame;
import model.restaurant.Restaurant;

import java.util.List;

public class Main {
    public static void main(String[] args){
        int count = 3;
        List <Restaurant> restaurantList = RestaurantThread.mergeLists(new RestaurantThread[count]);
    }
}
