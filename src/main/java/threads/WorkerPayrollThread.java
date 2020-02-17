package threads;

import implementations.xmlBills.CFDIPayrollGenerator;
import model.restaurant.Restaurant;
import model.restaurant.worker.Payroll;
import model.restaurant.worker.Worker;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class WorkerPayrollThread extends Thread{
    private final Restaurant restaurant;

    protected WorkerPayrollThread(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    @Override
    public void run(){
        restaurant.getWorkerList().stream()
                .forEach(this::getPayroll);
    }

    private void getPayroll(Worker worker) {
        restaurant.payWorker(worker);
        new CFDIPayrollGenerator().generatePayroll(new Payroll(worker,restaurant));
    }


    public static void executeThreads(List<Restaurant> restaurantList){
        WorkerPayrollThread[] WorkerPayrollThreadsList = getThreads(restaurantList);
        startThreads(WorkerPayrollThreadsList);
        joinThreads(WorkerPayrollThreadsList);
    }

    private static WorkerPayrollThread[] getThreads(List<Restaurant> restaurantList){
        return restaurantList.stream()
                .map(WorkerPayrollThread::new)
                .toArray(WorkerPayrollThread[]::new);
    }

    private static void startThreads(WorkerPayrollThread[] threads) {
        IntStream.range(0,threads.length)
                .forEach(pos -> threads[pos].start());
    }

    private static void joinThreads(WorkerPayrollThread[] threads) {
        Arrays.stream(threads).forEach(WorkerPayrollThread::joinThread);
    }

    private static void joinThread(WorkerPayrollThread thread){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
