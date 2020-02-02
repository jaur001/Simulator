package implementations.loaders.routine;

import model.restaurant.Restaurant;
import utils.Utils;
import view.loaders.routine.RoutineController;

import java.util.*;
import java.util.stream.IntStream;

public class SimpleRoutineController implements RoutineController {
    private int maxDays = 7;
    private double maxSalary = 10000;
    private int maxPrice = 60;

    public Map<Restaurant, Integer> addRoutines(double salary, List<Restaurant> restaurantList,
                                                Map<Integer,Integer> salaryGroups, int restaurantRoutineLengthPerClient) {

        List<Integer> auxList = new ArrayList<Integer>(salaryGroups.keySet());
        for(Integer salaryOption : auxList){
            if(salary<= salaryOption){
                return addToSalaryGroup(salary, restaurantRoutineLengthPerClient, salaryOption, getRestaurantOptions(salaryGroups.get(salaryOption), restaurantList));
            }
        }
        int lastSalaryOption = auxList.get(auxList.size() - 1);
        return addToSalaryGroup(salary, restaurantRoutineLengthPerClient, lastSalaryOption,  getRestaurantOptions(salaryGroups.get(lastSalaryOption), restaurantList));
    }

    private Map<Restaurant, Integer> addToSalaryGroup(double salary, int restaurantLength, Integer salaryOption, Restaurant[] restaurantOptions) {
        int days = selectDays(salary, salaryOption);
        return selectRestaurants(restaurantOptions, restaurantLength, days);
    }


    private int selectDays(double salary, double salaryOption) {
        return Utils.Random(1,7);
    }

    private Map<Restaurant, Integer> selectRestaurants(Restaurant[] restaurants, int restaurantLength, int days) {
        Map<Restaurant,Integer> restaurantRoutines = new HashMap<>();
        IntStream.range(0,restaurantLength)
                .map( (p) -> restaurantRoutines.put(getRandomRestaurant(restaurants),days));
        return restaurantRoutines;
    }

    private Restaurant getRandomRestaurant(Restaurant[] restaurants) {
        return restaurants[Math.abs(Utils.Random(0, restaurants.length - 1))];
    }

    private Restaurant[] getRestaurantOptions(int price, List<Restaurant> restaurantList){
        return restaurantList.stream()
                .filter(restaurant -> restaurant.getMaxPricePlate() <= price)
                .toArray(Restaurant[]::new);
    }
}
