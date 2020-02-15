package implementations.loaders.routine;

import model.client.Client;
import model.client.routine.Routine;
import model.client.routine.RoutineList;
import model.restaurant.Restaurant;
import utils.RoutineUtils;
import view.loaders.routine.RoutineLoader;

import java.util.ArrayList;
import java.util.List;

public class DistributionRoutineLoader implements RoutineLoader {
    public List<Client> load(List<Restaurant> restaurantList, List<Client> clientList) {
        List<Client> auxList = new ArrayList<>();
        for(Client i : clientList){
            double salary = RoutineUtils.getSalarySample();
            setRoutineToClient(restaurantList, auxList, i, salary);
        }
        return auxList;
    }

    private void setRoutineToClient(List<Restaurant> restaurantList, List<Client> auxList, Client client, double salary) {
        List<Routine> restaurantRoutines = new DistributionRoutineController().createRoutineList(salary,restaurantList);
        client.setRoutineList(new RoutineList(salary, restaurantRoutines));
        auxList.add(client);
    }
}
