package threads.bills;

import implementations.loaders.routine.DistributionAmountGenerator;
import implementations.xmlBills.CFDIBillGenerator;
import model.client.Client;
import model.restaurant.Bill;
import model.restaurant.Eating;
import model.restaurant.Restaurant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class RoutineCheckerThread extends Thread{
    private final Client client;

    private RoutineCheckerThread(Client client) {
        this.client = client;
    }


    @Override
    public void run(){
        client.getRoutineList().checkRoutines()
                .forEach(this::getBill);
    }

    private void getBill(Restaurant restaurant) {
        double amount = new DistributionAmountGenerator().generate(restaurant,client);
        client.pay(amount);
        restaurant.addSale(amount);
        new CFDIBillGenerator().generateBill(new Eating(restaurant,client,new Bill(amount),client.getCommensalNumber()));
    }

    public static void executeThreads(List<Client> clientList){
        RoutineCheckerThread[] routineCheckerThreadsList = getThreads(clientList);
        startThreads(routineCheckerThreadsList);
        joinThreads(routineCheckerThreadsList);
    }

    private static RoutineCheckerThread[] getThreads(List<Client> clientList){
        return clientList.stream()
                .map(RoutineCheckerThread::new)
                .toArray(RoutineCheckerThread[]::new);
    }

    private static void startThreads(RoutineCheckerThread[] threads) {
        IntStream.range(0,threads.length)
                .forEach(pos -> threads[pos].start());
    }
    private static void joinThreads(RoutineCheckerThread[] threads) {
        Arrays.stream(threads).forEach(RoutineCheckerThread::joinThread);
    }

    private static void joinThread(RoutineCheckerThread thread){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}