package implementations.loaders.routine;

import model.client.Client;
import model.client.RoutineList;
import model.restaurant.Restaurant;
import org.apache.commons.math3.distribution.NormalDistribution;
import view.loaders.routine.RoutinesLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleRoutineLoader implements RoutinesLoader {
    public List<Client> load(NormalDistribution salaryDistribution, List<Restaurant> restaurantList, List<Client> clientList, Map<Integer,Integer> salaryGroups, int restaurantRoutineLengthPerClient) {
        List<Client> auxList = new ArrayList<Client>();
        for(Client i : clientList){
            //double salary = salaryDistribution.sample();
            double salary = (Math.random() * ((7000 - 1000) + 1)) + 1000;
            setRoutineToClient(restaurantList, salaryGroups, restaurantRoutineLengthPerClient, auxList, i, salary);
        }
        return auxList;
    }

    private void setRoutineToClient(List<Restaurant> restaurantList, Map<Integer, Integer> salaryGroups, int restaurantRoutineLengthPerClient, List<Client> auxList, Client i, double salary) {
        Map<Restaurant,Integer> restaurantRoutines = new SimpleRoutineController().addRoutines(salary,restaurantList,salaryGroups,restaurantRoutineLengthPerClient);
        i.setRoutineList(new RoutineList(salary, restaurantRoutines));
        auxList.add(i);
    }
}
