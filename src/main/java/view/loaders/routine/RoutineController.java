package view.loaders.routine;

import model.client.routine.Routine;
import model.restaurant.Restaurant;

import java.util.List;
import java.util.Map;

public interface RoutineController {
    List<Routine> createRoutineList(double salary, List<Restaurant> restaurantList);
    void addRoutine(double salary, List<Restaurant> restaurantList, List<Routine> restaurantRoutines);
    void deleteRoutine(Routine routine, List<Routine> restaurantRoutines);
}
