package threads;

import implementations.loaders.routine.DistributionAmountGenerator;
import implementations.xmlBills.CFDIBillGenerator;
import model.client.Client;
import model.restaurant.Bill;
import model.restaurant.Eating;
import model.restaurant.Restaurant;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

public class RoutineCheckerThread extends Thread{
    private final Client client;
    private static final String url = "./xmlFiles/";

    private RoutineCheckerThread(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public void run(){
        client.getRoutineList().checkRoutines()
                .forEach(restaurantToEat -> getBill(client,restaurantToEat));
    }

    private void getBill(Client i, Restaurant j) {
        double amount = new DistributionAmountGenerator().generate(j,i);
        i.pay(amount);
        new CFDIBillGenerator().generateBill(new Eating(j,i,new Date(),new Bill(amount),client.getCommensalNumber()),url);
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