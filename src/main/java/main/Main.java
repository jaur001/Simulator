package main;

import implementations.loaders.client.ClientLoaderCSV;
import implementations.loaders.restaurant.SQLite.SQLRestaurantWriter;
import implementations.loaders.restaurant.SQLite.SQLiteRestaurantLoader;
import implementations.xmlBills.CFDIBillGenerator;
import implementations.xmlBills.CFDIPayrollGenerator;
import model.client.Client;
import threads.initializers.RestaurantThread;
import threads.initializers.RoutineThread;
import threads.initializers.WorkerThread;
import model.restaurant.Restaurant;
import time.Time;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int count = 1;
        int rowNumber = 2;
        String urlClient = "./clientsFile/ExportCSV.csv";
        CFDIBillGenerator.setUrl("./xmlFiles/EatingBills/");
        CFDIPayrollGenerator.setUrl("./xmlFiles/Payrolls/");

        String url = "jdbc:sqlite:Simulator.db";
        SQLRestaurantWriter.setSQLiteUrl(url);
        SQLiteRestaurantLoader.setSQLiteUrl(url);

        //List<Restaurant> restaurantList = RestaurantThread.loadRestaurantsPage(count);
        List<Restaurant> restaurantList = new SQLiteRestaurantLoader().load(30);
        System.out.println(restaurantList.size());
        WorkerThread.executeThreads(restaurantList);

        List<Client> clientList = new ClientLoaderCSV().load(urlClient,rowNumber);
        RoutineThread.executeThreads(clientList,restaurantList);

        Time time = new Time(restaurantList,clientList,new ArrayList<>());
        while(true){
            time.play();
            for (Client i : clientList) {
                i.printRoutines();
            }
        }
    }
}
