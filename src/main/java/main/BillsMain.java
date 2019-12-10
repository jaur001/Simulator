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
        int rowNumber = 30;
        String urlClient = "./clientsFile/ExportCSV.csv";
        String urlBill = "./xmlFiles/";
        List <Restaurant> restaurantList = RestaurantThread.mergeLists(new RestaurantThread[count]);
        List<Client> clientList = new ClientLoaderCSV().load(urlClient,rowNumber);
        BillThread.executeThreads(new BillThread[restaurantList.size()],restaurantList,clientList,urlBill);
    }
}
