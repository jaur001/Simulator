package implementations.loaders.routine;

import model.client.Client;
import model.client.routine.Routine;
import model.client.routine.RoutineList;
import model.restaurant.Restaurant;
import utils.Utils;
import view.loaders.routine.RoutinesLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleRoutineLoader implements RoutinesLoader {
    public List<Client> load(List<Restaurant> restaurantList, List<Client> clientList, Map<Integer,Integer> salaryGroups, int restaurantRoutineLengthPerClient) {
        List<Client> auxList = new ArrayList<>();
        for(Client i : clientList){
            double salary = Utils.getSalarySample();
            setRoutineToClient(restaurantList, salaryGroups, restaurantRoutineLengthPerClient, auxList, i, salary);
        }
        return auxList;
    }

    private void setRoutineToClient(List<Restaurant> restaurantList, Map<Integer, Integer> salaryGroups, int restaurantRoutineLengthPerClient, List<Client> auxList, Client client, double salary) {
        List<Routine> restaurantRoutines = new SimpleRoutineController().addRoutines(salary,restaurantList,salaryGroups,restaurantRoutineLengthPerClient);
        client.setRoutineList(new RoutineList(salary, restaurantRoutines));
        auxList.add(client);
    }
}
