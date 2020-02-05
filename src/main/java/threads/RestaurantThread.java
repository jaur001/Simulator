package threads;

import model.restaurant.Restaurant;
import view.loaders.restaurant.RestaurantLoader;
import implementations.loaders.restaurant.TripAdvisorRestaurantLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class RestaurantThread extends Thread{
    private List<Restaurant> list = new ArrayList<Restaurant>();
    private int i;

    public RestaurantThread(int i) {
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
        List<Restaurant> auxList = new ArrayList<>();
        RestaurantThread[] restaurantThreads = executeThreads(threadsCount);
        joinThreads(restaurantThreads);
        addRestaurantsToFinalList(restaurantThreads, auxList);
        List<Restaurant> finalList = new ArrayList<Restaurant>(new HashSet<>(auxList));
        return finalList;
    }

    private static RestaurantThread[] executeThreads(int threadsCount) {
        return IntStream.range(0,threadsCount).boxed()
                .map(RestaurantThread::executeThread)
                .toArray(size -> new RestaurantThread[size]);
    }

    private static RestaurantThread executeThread(int pos) {
        RestaurantThread thread = new RestaurantThread(pos);
        thread.start();
        return thread;
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
