package main;

import implementations.loaders.client.ClientLoaderCSV;
import model.client.Client;
import threads.RestaurantThread;
import view.loaders.routine.RoutinesLoader;
import implementations.loaders.routine.SimpleRoutineLoader;
import model.restaurant.Restaurant;
import time.Time;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoutineMain {
    public static void main(String[] args) {
        int count = 1;
        int rowNumber = 1;
        int restaurantRoutineLengthPerClient = 2;
        String urlClient = "./clientsFile/ExportCSV.csv";
        List<Restaurant> restaurantList = RestaurantThread.loadRestaurantsPage(count);
        List<Client> clientList = new ClientLoaderCSV().load(urlClient,rowNumber);
        Map<Integer,Integer> restaurantGroups = Utils.getRestaurantGroupsTable();
        RoutinesLoader loader = new SimpleRoutineLoader();
        clientList = loader.load(restaurantList,clientList,restaurantGroups,restaurantRoutineLengthPerClient);
        Time time = new Time(restaurantList,clientList,new ArrayList<>());
        while(true){
            time.play();
            for (Client i : clientList) {
                i.getRoutineList().printCount();
            }
        }

    }
}
