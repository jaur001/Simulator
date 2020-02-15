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

public class SalaryThread extends Thread{
    private final Client client;
    private static final String url = "./xmlFiles/";

    private SalaryThread(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public void run(){
        client.getRoutineList().restartBudget();
    }


    public static void executeThreads(List<Client> clientList){
        SalaryThread[] SalaryThreadsList = getThreads(clientList);
        startThreads(SalaryThreadsList);
        joinThreads(SalaryThreadsList);
    }

    private static SalaryThread[] getThreads(List<Client> clientList){
        return clientList.stream()
                .map(SalaryThread::new)
                .toArray(SalaryThread[]::new);
    }

    private static void startThreads(SalaryThread[] threads) {
        IntStream.range(0,threads.length)
                .forEach(pos -> threads[pos].start());
    }

    private static void joinThreads(SalaryThread[] threads) {
        Arrays.stream(threads).forEach(SalaryThread::joinThread);
    }

    private static void joinThread(SalaryThread thread){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
