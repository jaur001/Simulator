package main;

import model.client.Client;
import model.restaurant.Eating;
import model.restaurant.Restaurant;
import org.apache.commons.math3.distribution.NormalDistribution;
import xmlBills.CFDIBillGenerator;

import java.util.Date;
import java.util.List;

public class BillThread extends Thread{
    private Restaurant restaurant;
    private List<Client> clientList;

    public BillThread(Restaurant restaurant, List<Client> clientList) {
        this.restaurant = restaurant;
        this.clientList = clientList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    @Override
    public void run(){
        double mean = 0;
        double plateNumber = 0;
        double amount = 0;
        int invitedPeople = 4;
        for(Client j : clientList) {
            mean = (restaurant.getMaxPricePlate()+restaurant.getMinPricePlate())/2;
            plateNumber = Math.round(Math.abs(new NormalDistribution(2,0.7).sample()));
            plateNumber = plateNumber<1? 1: plateNumber;
            amount = restaurant.getMaxPricePlate()==restaurant.getMinPricePlate()? mean * plateNumber * invitedPeople :
                    Math.abs(new NormalDistribution(mean,restaurant.getMaxPricePlate()-mean).sample()) * plateNumber * invitedPeople;
            CFDIBillGenerator.generateBill(new Eating(restaurant,j,new Date(),amount,invitedPeople));
        }
    }

    public static void executeThreads(BillThread[] threads,Restaurant restaurant, List<Client> clientList){
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new BillThread(restaurant,clientList);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
