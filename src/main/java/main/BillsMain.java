package main;

import implementations.loaders.client.ClientLoaderCSV;
import model.client.Client;
import model.restaurant.Restaurant;
import threads.BillThread;
import threads.RestaurantThread;

import java.util.List;

public class BillsMain {
    public static void main(String[] args) {
        int count = 1;
        int rowNumber = 30;
        String urlClient = "./clientsFile/ExportCSV.csv";
        String urlBill = "./xmlFiles/";
        List <Restaurant> restaurantList = RestaurantThread.loadRestaurantsPage(count);
        List<Client> clientList = new ClientLoaderCSV().load(urlClient,rowNumber);
        BillThread.executeThreads(new BillThread[restaurantList.size()],restaurantList,clientList,urlBill);
    }
}
