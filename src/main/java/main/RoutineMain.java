package main;

import implementations.loaders.client.ClientLoaderCSV;
import model.client.Client;
import threads.RestaurantThread;
import view.loaders.routine.RoutineLoader;
import implementations.loaders.routine.DistributionRoutineLoader;
import model.restaurant.Restaurant;
import time.Time;

import java.util.ArrayList;
import java.util.List;

public class RoutineMain {
    public static void main(String[] args) {
        int count = 1;
        int rowNumber = 2;
        String urlClient = "./clientsFile/ExportCSV.csv";
        List<Restaurant> restaurantList = RestaurantThread.loadRestaurantsPage(count);
        List<Client> clientList = new ClientLoaderCSV().load(urlClient,rowNumber);
        RoutineLoader loader = new DistributionRoutineLoader();
        clientList = loader.load(restaurantList,clientList);
        Time time = new Time(restaurantList,clientList,new ArrayList<>());
        while(true){
            time.play();
            for (Client i : clientList) {
                i.printRoutines();
            }
        }

    }
}
