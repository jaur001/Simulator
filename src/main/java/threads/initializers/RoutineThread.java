package threads.initializers;

import implementations.loaders.routine.DistributionRoutineController;
import implementations.loaders.worker.EnumWorkerLoader;
import model.client.Client;
import model.client.routine.Routine;
import model.client.routine.RoutineList;
import model.restaurant.Restaurant;
import utils.RoutineUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class RoutineThread extends Thread{
    private final Client client;
    private final List<Restaurant> restaurantList;

    protected RoutineThread(Client client,List<Restaurant> restaurantList) {
        this.client = client;
        this.restaurantList = restaurantList;
    }


    @Override
    public void run(){
        double salary = RoutineUtils.getSalarySample();
        List<Routine> restaurantRoutines = new DistributionRoutineController().createRoutineList(salary,restaurantList);
        client.setRoutineList(new RoutineList(salary, restaurantRoutines));
    }


    public static void executeThreads(List<Client> clientList, List<Restaurant> restaurantList){
        RoutineThread[] RoutineThreadsList = getThreads(clientList,restaurantList);
        startThreads(RoutineThreadsList);
        joinThreads(RoutineThreadsList);
    }

    private static RoutineThread[] getThreads(List<Client> clientList,List<Restaurant> restaurantList){
        return clientList.stream()
                .map(client -> new RoutineThread(client,restaurantList))
                .toArray(RoutineThread[]::new);
    }

    private static void startThreads(RoutineThread[] threads) {
        IntStream.range(0,threads.length)
                .forEach(pos -> threads[pos].start());
    }

    private static void joinThreads(RoutineThread[] threads) {
        Arrays.stream(threads).forEach(RoutineThread::joinThread);
    }

    private static void joinThread(RoutineThread thread){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
