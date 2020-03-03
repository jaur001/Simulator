package threads.initializers;

import implementations.loaders.worker.EnumWorkerLoader;
import model.restaurant.Restaurant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class WorkerThread extends Thread{

    public static void addWorkers(List<Restaurant> restaurantList){
        restaurantList.parallelStream().forEach(WorkerThread::addWorker);
    }

    private static void addWorker(Restaurant restaurant){
        new EnumWorkerLoader().load(restaurant.getNumberTables())
                .forEach(restaurant::addWorker);
    }

}
