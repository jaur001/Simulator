package model.client;

import model.restaurant.Restaurant;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleRoutineLoader implements RoutinesLoader {
    public List<Client> load(NormalDistribution salaryDistribution, List<Restaurant> restaurantList, List<Client> clientList, Map<Integer,Integer> salaryGroups, int restaurantLength) {
        List<Client> auxList = new ArrayList<Client>();
        for(Client i : clientList){

            //double salary = salaryDistribution.sample();
            double salary = (Math.random() * ((7000 - 1000) + 1)) + 1000;
            Map<Restaurant,Integer> restaurantRoutines = new SimpleRoutineController().addRoutines(salary,restaurantList,salaryGroups,restaurantLength);
            RoutineList routines = new RoutineList(salary, restaurantRoutines);
            Map<Restaurant,Integer> map = routines.getRestaurantRoutine();
            for (Restaurant j : map.keySet()) {
                System.out.println(j.getName() + ": " + map.get(j));
            }
            i.setRoutineList(new RoutineList(salary, restaurantRoutines));
            auxList.add(i);
        }

        return auxList;
    }
}
