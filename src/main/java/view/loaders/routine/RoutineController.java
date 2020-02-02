package view.loaders.routine;

import model.restaurant.Restaurant;

import java.util.List;
import java.util.Map;

public interface RoutineController {
    Map<Restaurant,Integer> addRoutines(double salary, List<Restaurant> restaurantList,Map<Integer, Integer> salaryGroups, int restaurantLength);
}
