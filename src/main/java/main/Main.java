package main;

import implementations.loaders.CSV.ClientLoaderCSV;
import implementations.loaders.CSV.ProviderLoaderCSV;
import implementations.loaders.restaurant.SQLite.SQLRestaurantWriter;
import implementations.loaders.restaurant.SQLite.SQLiteRestaurantReader;
import implementations.xmlBills.CFDIBillGenerator;
import implementations.xmlBills.CFDIPayrollGenerator;
import model.client.Client;
import model.provider.Provider;
import threads.initializers.RestaurantThread;
import threads.initializers.RoutineThread;
import threads.initializers.WorkerThread;
import model.restaurant.Restaurant;
import threads.initializers.provider.ProductInitializerThread;
import threads.initializers.provider.ProvidingThread;
import time.Time;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int count = 5;
        int clientRowNumber = 1;
        int providerRowNumber = 100;
        int restaurantRowNumber = 30;
        String urlClient = "./CSVFiles/Clients.csv";
        String urlProvider = "./CSVFiles/Providers.csv";

        CFDIBillGenerator.setUrl("./xmlFiles/EatingBills/");
        CFDIPayrollGenerator.setUrl("./xmlFiles/Payrolls/");

        String url = "jdbc:sqlite:Simulator.db";
        SQLRestaurantWriter.setSQLiteUrl(url);
        SQLiteRestaurantReader.setSQLiteUrl(url);

        List<Provider> providerList = new ProviderLoaderCSV().load(urlProvider,providerRowNumber);
        ProductInitializerThread.initProducts(providerList);

        List<Restaurant> restaurantList = RestaurantThread.loadRestaurantsPage(count);
        System.out.println(restaurantList.size());
        //List<Restaurant> restaurantList = new SQLiteRestaurantReader().read(restaurantRowNumber);
        WorkerThread.addWorkers(restaurantList);
        ProvidingThread.initRestaurantProviders(providerList,restaurantList);

        List<Client> clientList = new ClientLoaderCSV().load(urlClient,clientRowNumber);
        RoutineThread.setClientRoutines(clientList,restaurantList);

        Time time = new Time(restaurantList,clientList,providerList);
        while(true){
            time.play();
            for (Client i : clientList) {
                i.printRoutines();
            }
        }

    }
}
