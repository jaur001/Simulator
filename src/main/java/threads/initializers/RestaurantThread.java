package threads.initializers;

import model.restaurant.Restaurant;
import view.loaders.restaurant.RestaurantLoader;
import implementations.loaders.restaurant.tripAvisor.TripAdvisorRestaurantLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class RestaurantThread extends Thread{
    private List<Restaurant> list = new ArrayList<>();
    private int i;

    private RestaurantThread(int i) {
        this.i = i;
    }

    public List<Restaurant> getList() {
        return list;
    }

    @Override
    public void run(){
        RestaurantLoader loader = new TripAdvisorRestaurantLoader();
        try {
            list = loader.load(i);
        } catch (IOException e) {
            System.out.println("Error: Time Out exception");
        }
    }

    public static List<Restaurant> loadRestaurantsPage(int threadsCount){
        RestaurantThread[] restaurantThreads = getThreads(threadsCount);
        startThreads(restaurantThreads);
        joinThreads(restaurantThreads);
        List<Restaurant> pageList = new ArrayList<>();
        addRestaurantsToFinalList(restaurantThreads, pageList);
        return new ArrayList<>(new HashSet<>(pageList));
    }

    private static RestaurantThread[] getThreads(int threadsCount) {
        return IntStream.range(0,threadsCount).boxed()
                .map(RestaurantThread::getThread)
                .toArray(RestaurantThread[]::new);
    }

    private static RestaurantThread getThread(int pos) {
        return new RestaurantThread(pos);
    }

    private static void startThreads(RestaurantThread[] threads) {
        IntStream.range(0,threads.length)
                .forEach(pos -> threads[pos].start());
    }

    private static void addRestaurantsToFinalList(RestaurantThread[] threads, List<Restaurant> auxList) {
        IntStream.range(0,threads.length)
                .forEach(pos -> auxList.addAll(threads[pos].getList()));
    }

    private static void joinThreads(RestaurantThread[] threads) {
        Arrays.stream(threads).forEach(RestaurantThread::joinThread);
    }

    private static void joinThread(RestaurantThread thread){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
