package threads;

import model.client.Client;
import model.restaurant.Bill;
import model.restaurant.Eating;
import model.restaurant.Restaurant;
import implementations.xmlBills.CFDIBillGenerator;
import utils.Utils;

import java.util.Date;
import java.util.List;

public class BillThread extends Thread{
    private Restaurant restaurant;
    private List<Client> clientList;
    private final String url;

    public BillThread(Restaurant restaurant, List<Client> clientList, String url) {
        this.restaurant = restaurant;
        this.clientList = clientList;
        this.url = url;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    @Override
    public void run(){
        double plateNumber;
        double amount;
        int invitedPeople = 4;
        for(Client j : clientList) {
            getBill(invitedPeople, j);
        }
    }

    private void getBill(int invitedPeople, Client j) {
        double plateNumber;
        double amount;
        plateNumber = Utils.getPlateNumberSample();
        amount = Utils.getPriceSample(restaurant,(int)plateNumber,invitedPeople);
        new CFDIBillGenerator().generateBill(new Eating(restaurant,j,new Date(),new Bill(amount),invitedPeople),url);
    }

    public static void executeThreads(BillThread[] threads,List<Restaurant> restaurantList, List<Client> clientList, String url){
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new BillThread(restaurantList.get(i),clientList,url);
            threads[i].start();
        }
        joinThreads(threads);
    }

    private static void joinThreads(BillThread[] threads) {
        for (BillThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
