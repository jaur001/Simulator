package implementations.loaders.routine;

import model.client.routine.Counter;
import model.client.routine.Routine;
import model.restaurant.Restaurant;
import utils.Utils;
import view.loaders.routine.RoutineController;

import java.util.*;
import java.util.stream.IntStream;

public class SimpleRoutineController implements RoutineController {
    private int maxDays = 7;
    private double maxSalary = 10000;
    private int maxPrice = 60;

    public List<Routine> addRoutines(double salary, List<Restaurant> restaurantList,
                                                Map<Integer,Integer> salaryGroups, int restaurantRoutineLengthPerClient) {

        List<Integer> auxList = new ArrayList<>(salaryGroups.keySet());
        for(Integer salaryOption : auxList){
            if(salary<= salaryOption){
                return addToSalaryGroup(salary, restaurantRoutineLengthPerClient, salaryOption, getRestaurantOptions(salaryGroups.get(salaryOption), restaurantList));
            }
        }
        int lastSalaryOption = auxList.get(auxList.size() - 1);
        return addToSalaryGroup(salary, restaurantRoutineLengthPerClient, lastSalaryOption,  getRestaurantOptions(salaryGroups.get(lastSalaryOption), restaurantList));
    }

    private Restaurant[] getRestaurantOptions(int price, List<Restaurant> restaurantList){
        return restaurantList.stream()
                .filter(restaurant -> restaurant.getMaxPricePlate() <= price)
                .toArray(Restaurant[]::new);
    }

    private List<Routine> addToSalaryGroup(double salary, int restaurantLength, Integer salaryOption, Restaurant[] restaurantOptions) {
        int days = selectDays(salary, salaryOption);
        return selectRestaurants(restaurantOptions, restaurantLength, days);
    }


    private int selectDays(double salary, double salaryOption) {
        return Utils.Random(1,7);
    }

    private List<Routine> selectRestaurants(Restaurant[] restaurants, int restaurantLength, int days) {
        List<Routine> restaurantRoutines = new ArrayList<>();
        IntStream.range(0,restaurantLength)
                .forEach((i) -> restaurantRoutines.add(new Routine(getRandomRestaurant(restaurants),new Counter(days))));
        return restaurantRoutines;
    }

    private Restaurant getRandomRestaurant(Restaurant[] restaurants) {
        return restaurants[Math.abs(Utils.Random(0, restaurants.length - 1))];
    }

}
