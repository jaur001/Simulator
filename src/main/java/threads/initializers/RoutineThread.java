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

public class RoutineThread {

    public static void setClientRoutines(List<Client> clients, List<Restaurant> restaurants) {
        clients.parallelStream().forEach(client -> setClientRoutine(client, restaurants));
    }

    private static void setClientRoutine(Client client, List<Restaurant> restaurants) {
        double salary = RoutineUtils.getSalarySample();
        List<Routine> restaurantRoutines = new DistributionRoutineController().createRoutineList(salary,restaurants);
        client.setRoutineList(new RoutineList(salary, restaurantRoutines));
    }

}
