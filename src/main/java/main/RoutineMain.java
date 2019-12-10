package main;

import clientLoader.ClientLoaderCSV;
import model.client.Client;
import model.client.RoutinesLoader;
import model.client.SimpleRoutineLoader;
import model.restaurant.Restaurant;
import org.apache.commons.math3.distribution.NormalDistribution;
import utils.Utils;

import java.util.List;
import java.util.Map;

public class RoutineMain {
    public static void main(String[] args) {
        int count = 1;
        int rowNumber = 30;
        int restaurantLength = 3;
        String urlClient = "./clientsFile/ExportCSV.csv";
        List<Restaurant> restaurantList = RestaurantThread.mergeLists(new RestaurantThread[count]);
        List<Client> clientList = new ClientLoaderCSV().load(urlClient,rowNumber);
        Map<Integer,Integer> restaurantGroups = Utils.getRestaurantGroupsTable();
        RoutinesLoader loader = new SimpleRoutineLoader();
        clientList = loader.load(new NormalDistribution(),restaurantList,clientList,restaurantGroups,restaurantLength);
        for (Client i : clientList) {
            for (Restaurant j : i.getRoutineList().getRestaurantRoutine().keySet()) {
                System.out.println(j.getName() + ": " + i.getRoutineList().getRestaurantRoutine().get(j));
            }
        }
        
    }
}
