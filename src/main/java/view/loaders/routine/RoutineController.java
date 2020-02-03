package view.loaders.routine;

import model.client.routine.Routine;
import model.restaurant.Restaurant;

import java.util.List;
import java.util.Map;

public interface RoutineController {
    List<Routine> addRoutines(double salary, List<Restaurant> restaurantList, Map<Integer, Integer> salaryGroups, int restaurantLength);
}
