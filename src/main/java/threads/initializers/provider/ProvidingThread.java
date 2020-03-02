package threads.initializers.provider;

import implementations.loaders.provider.RandomProvidingController;
import model.provider.Product;
import model.provider.Provider;
import model.restaurant.Restaurant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ProvidingThread extends Thread{
    private final List<Provider> providerList;
    private final Restaurant restaurant;

    private ProvidingThread(Restaurant restaurant, List<Provider> providerList) {
        this.providerList = providerList;
        this.restaurant = restaurant;
    }


    @Override
    public void run(){
        IntStream.range(0, Product.values().length).boxed()
                .map(integer -> new RandomProvidingController().provide(providerList,Product.values()[integer]))
                .forEach(restaurant::addProvider);
    }


    public static void executeThreads(List<Provider> providerList, List<Restaurant> restaurantList){
        ProvidingThread[] providingThreadsList = getThreads(providerList,restaurantList);
        startThreads(providingThreadsList);
        joinThreads(providingThreadsList);
    }

    private static ProvidingThread[] getThreads(List<Provider> providerList, List<Restaurant> restaurantList){
        return restaurantList.stream()
                .map(restaurant -> new ProvidingThread(restaurant,providerList))
                .toArray(ProvidingThread[]::new);
    }

    private static void startThreads(ProvidingThread[] threads) {
        IntStream.range(0,threads.length)
                .forEach(pos -> threads[pos].start());
    }

    private static void joinThreads(ProvidingThread[] threads) {
        Arrays.stream(threads).forEach(ProvidingThread::joinThread);
    }

    private static void joinThread(ProvidingThread thread){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
