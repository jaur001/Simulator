package main;

import clientLoader.ClientLoaderCSV;
import model.client.Client;
import model.client.RoutinesLoader;
import model.client.SimpleRoutineLoader;
import model.provider.Provider;
import model.restaurant.Restaurant;
import org.apache.commons.math3.distribution.NormalDistribution;
import time.Time;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoutineMain {
    public static void main(String[] args) {
        int count = 1;
        int rowNumber = 1;
        int restaurantLength = 1;
        String urlClient = "./clientsFile/ExportCSV.csv";
        List<Restaurant> restaurantList = RestaurantThread.mergeLists(new RestaurantThread[count]);
        List<Client> clientList = new ClientLoaderCSV().load(urlClient,rowNumber);
        Map<Integer,Integer> restaurantGroups = Utils.getRestaurantGroupsTable();
        RoutinesLoader loader = new SimpleRoutineLoader();
        clientList = loader.load(new NormalDistribution(),restaurantList,clientList,restaurantGroups,restaurantLength);
        Time time = new Time(restaurantList,clientList,new ArrayList<Provider>());
        while(true){
            time.play();
            for (Client i : clientList) {
                i.getRoutineList().printCount();
            }
        }

    }
}
