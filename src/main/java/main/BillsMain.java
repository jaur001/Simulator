package main;

import clientLoader.ClientLoader;
import clientLoader.ClientLoaderCSV;
import model.client.Client;
import model.restaurant.Eating;
import model.restaurant.Restaurant;
import org.apache.commons.math3.distribution.NormalDistribution;
import xmlBills.CFDIBillGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class BillsMain {
    public static void main(String[] args) {
        int count = 1;

        RestaurantThread[] threads = new RestaurantThread[count];
        List <Restaurant> restaurantList = RestaurantThread.mergeLists(threads);

        ClientLoader loader = new ClientLoaderCSV();
        List<Client> clientList = loader.load("./src/main/java/clientLoader/MOCK_DATA.csv",10);

        BillThread[] billThreads = new BillThread[restaurantList.size()];
        for (Restaurant i : restaurantList){
            BillThread.executeThreads(billThreads,i,clientList);
        }
    }
}
