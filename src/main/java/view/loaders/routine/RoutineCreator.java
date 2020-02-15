package view.loaders.routine;

import model.client.routine.Routine;
import model.restaurant.Restaurant;

import java.util.List;

public interface RoutineCreator {

    Routine create(double salary, List<Restaurant> restaurantList);
}
