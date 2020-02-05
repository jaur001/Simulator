package implementations.loaders.routine;

import model.client.routine.Counter;
import model.client.routine.Routine;
import model.restaurant.Restaurant;
import utils.Utils;
import view.loaders.routine.RoutineController;

import java.util.*;
import java.util.stream.IntStream;

public class SimpleRoutineController implements RoutineController {

    public List<Routine> addRoutines(double salary, List<Restaurant> restaurantList,
                                                Map<Integer,Integer> salaryGroups, int restaurantRoutineLengthPerClient) {

        List<Integer> auxList = new ArrayList<>(salaryGroups.keySet());
        int salaryGroup = auxList.stream()
                .filter(salaryAuxOption -> salary<=salaryAuxOption)
                .findFirst().orElse(auxList.get(auxList.size() - 1));
        return addToSalaryGroup(salary, restaurantRoutineLengthPerClient, salaryGroup, getRestaurantOptions(salaryGroups.get(salaryGroup), restaurantList));
    }

    private Restaurant[] getRestaurantOptions(int price, List<Restaurant> restaurantList){
        return restaurantList.stream()
                .filter(restaurant -> restaurant.getMaxPricePlate() <= price)
                .toArray(Restaurant[]::new);
    }

    private List<Routine> addToSalaryGroup(double salary, int restaurantRoutineLengthPerClient, Integer salaryOption, Restaurant[] restaurantOptions) {
        int days = selectDays(salary, salaryOption);
        return selectRestaurants(restaurantOptions, restaurantRoutineLengthPerClient, days);
    }


    private int selectDays(double salary, double salaryOption) {
        return Utils.Random(1,2);
    }

    private List<Routine> selectRestaurants(Restaurant[] restaurants, int restaurantRoutineLengthPerClient, int days) {
        List<Routine> restaurantRoutines = new ArrayList<>();
        IntStream.range(0,restaurantRoutineLengthPerClient)
                .forEach((i) -> restaurantRoutines.add(new Routine(getRandomRestaurant(restaurants),new Counter(days))));
        return restaurantRoutines;
    }

    private Restaurant getRandomRestaurant(Restaurant[] restaurants) {
        return restaurants[Math.abs(Utils.Random(0, restaurants.length - 1))];
    }

}
