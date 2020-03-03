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

    public static void checkRoutines(List<Client> clientList){
        clientList.parallelStream().forEach(RoutineCheckerThread::checkRoutines);
    }

    private static void checkRoutines(Client client){
        client.getRoutineList().checkRoutines()
                .forEach(restaurant -> getBill(restaurant,client));
    }

    private static void getBill(Restaurant restaurant, Client client) {
        double amount = new DistributionAmountGenerator().generate(restaurant,client);
        client.pay(amount);
        restaurant.addSale(amount);
        new CFDIBillGenerator().generateBill(new Eating(restaurant,client,new Bill(amount),client.getCommensalNumber()));
    }
}