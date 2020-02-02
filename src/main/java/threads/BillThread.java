package threads;

import model.client.Client;
import model.restaurant.Bill;
import model.restaurant.Eating;
import model.restaurant.Restaurant;
import org.apache.commons.math3.distribution.NormalDistribution;
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
        double mean;
        double plateNumber;
        double amount;
        int invitedPeople = 4;
        for(Client j : clientList) {
            mean = Utils.mean(new double[]{(double)restaurant.getMaxPricePlate(),(double)restaurant.getMinPricePlate()});
            plateNumber = Utils.getPlateNumberSample();
            amount = (restaurant.getMaxPricePlate()==restaurant.getMinPricePlate()? mean :
                    Utils.getPriceSample(mean,restaurant)) * plateNumber * invitedPeople;
            new CFDIBillGenerator().generateBill(new Eating(restaurant,j,new Date(),new Bill(amount),invitedPeople),url);
        }
    }

    public static void executeThreads(BillThread[] threads,List<Restaurant> restaurantList, List<Client> clientList, String url){
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new BillThread(restaurantList.get(i),clientList,url);
            threads[i].start();
        }

        for (BillThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
