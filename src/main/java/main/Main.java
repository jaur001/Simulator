package main;

import View.MainFrame;
import model.client.Client;
import model.restaurant.Plate;
import model.restaurant.Restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args){
        int count = 50;
        List <Restaurant> restaurantList = RestaurantThread.mergeLists(new RestaurantThread[count]);
        MainFrame frame = new MainFrame("Administration",restaurantList);
    }
}
