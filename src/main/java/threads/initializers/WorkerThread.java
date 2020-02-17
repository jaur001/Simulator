package threads.initializers;

import implementations.loaders.worker.EnumWorkerLoader;
import model.restaurant.Restaurant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class WorkerThread extends Thread{
    private final Restaurant restaurant;

    protected WorkerThread(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    @Override
    public void run(){
        new EnumWorkerLoader().load(restaurant.getNumberTables()).stream()
        .forEach(restaurant::addWorker);
    }


    public static void executeThreads(List<Restaurant> restaurantList){
        WorkerThread[] WorkerThreadsList = getThreads(restaurantList);
        startThreads(WorkerThreadsList);
        joinThreads(WorkerThreadsList);
    }

    private static WorkerThread[] getThreads(List<Restaurant> restaurantList){
        return restaurantList.stream()
                .map(WorkerThread::new)
                .toArray(WorkerThread[]::new);
    }

    private static void startThreads(WorkerThread[] threads) {
        IntStream.range(0,threads.length)
                .forEach(pos -> threads[pos].start());
    }

    private static void joinThreads(WorkerThread[] threads) {
        Arrays.stream(threads).forEach(WorkerThread::joinThread);
    }

    private static void joinThread(WorkerThread thread){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
