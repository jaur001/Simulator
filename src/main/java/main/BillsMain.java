package main;

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
        List<Restaurant> auxList = new ArrayList<Restaurant>();
        RestaurantThread[] threads = new RestaurantThread[count];

        for (int i = 0; i < count; i++) {
            threads[i] = new RestaurantThread(i);
            threads[i].start();
        }

        for (int i = 0; i < count; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < count; i++) {
            auxList.addAll(threads[i].getList());
        }
        List<Restaurant> finalList = new ArrayList<Restaurant>(new HashSet<Restaurant>(auxList));
        Client client = new Client(8,"Coleman");
        int invitedPeople = 4;
        for (Restaurant i : finalList){
            double mean = (i.getMaxPricePlate()+i.getMinPricePlate())/2;
            double plateNumber = Math.abs(new NormalDistribution(1,1).sample());
            double amount = Math.abs(new NormalDistribution(mean,i.getMaxPricePlate()-mean).sample()) * plateNumber * invitedPeople;
            CFDIBillGenerator.generateBill(new Eating(i,client,new Date(),amount,invitedPeople));
        }
    }
}
