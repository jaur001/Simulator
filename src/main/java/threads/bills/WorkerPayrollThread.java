package threads.bills;

import implementations.xmlBills.CFDIPayrollGenerator;
import model.restaurant.Restaurant;
import model.restaurant.worker.Payroll;
import model.restaurant.worker.Worker;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class WorkerPayrollThread extends Thread{

    public static void payWorkers(List<Restaurant> restaurantList){
        restaurantList.parallelStream().forEach(WorkerPayrollThread::payWorkers);
    }

    private static void payWorkers(Restaurant restaurant){
        restaurant.getWorkerList()
                .forEach(worker -> getPayroll(worker,restaurant));
    }

    private static void getPayroll(Worker worker, Restaurant restaurant) {
        restaurant.payWorker(worker);
        new CFDIPayrollGenerator().generatePayroll(new Payroll(worker,restaurant));
    }
}
