package threads;

import implementations.xmlBills.CFDIBillGenerator;
import model.client.Client;
import model.restaurant.Bill;
import model.restaurant.Eating;
import model.restaurant.Restaurant;
import utils.Utils;

import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

public class RoutineCheckerThread extends Thread{
    private final Client client;
    private static final String url = "./xmlFiles/";

    public RoutineCheckerThread(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public void run(){
        int invitedPeople = 4;
        client.getRoutineList().checkRoutines()
                .forEach(restaurantToEat -> getBill(client,restaurantToEat));
    }

    private void getBill(Client i, Restaurant j) {
        double amount = Utils.getPriceSample(j,(int)Utils.getPlateNumberSample(),4);
        i.pay(amount);
        new CFDIBillGenerator().generateBill(new Eating(j,i,new Date(),new Bill(amount),4),url);
    }

    public static void executeThreads(List<Client> clientList){
        RoutineCheckerThread[] routineCheckerThreadsList = getThreads(clientList);
        startThreads(routineCheckerThreadsList);
        joinThreads(routineCheckerThreadsList);
    }

    private static void startThreads(RoutineCheckerThread[] threads) {
        IntStream.range(0,threads.length)
                .forEach(pos -> threads[pos].start());
    }

    private static RoutineCheckerThread[] getThreads(List<Client> clientList){
        return clientList.stream()
                .map(client -> new RoutineCheckerThread(client))
                .toArray(size -> new RoutineCheckerThread[size]);
    }

    private static void joinThreads(RoutineCheckerThread[] threads) {
        for (RoutineCheckerThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}