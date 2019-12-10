package model.client;

import model.restaurant.Restaurant;

import java.util.*;

public class SimpleRoutineController implements RoutineController {
    private int maxDays = 7;
    private double maxSalary = 10000;
    private int maxPrice = 60;

    public Map<Restaurant, Integer> addRoutines(double salary, List<Restaurant> restaurantList,
                                                Map<Integer,Integer> salaryGroups, int restaurantLength) {

        Map<Restaurant,Integer> restaurantRoutines = new HashMap<Restaurant, Integer>();
        List<Integer> auxList = new ArrayList<Integer>(salaryGroups.keySet());
        for(Integer i : auxList){
            if(salary<= i){
                Restaurant[] restaurants = getRestaurantGroup(salaryGroups.get(i),restaurantList);
                int days = selectDays(salary,i);
                restaurantRoutines = selectRestaurants(restaurantRoutines,restaurants,restaurantLength, days);
                return restaurantRoutines;
            }
        }
        int lastSalary = auxList.get(auxList.size());
        Restaurant[] restaurants = getRestaurantGroup(restaurantList);
        int days = selectDays(salary,lastSalary);
        restaurantRoutines = selectRestaurants(restaurantRoutines,restaurants,restaurantLength, days);

        return restaurantRoutines;
    }

    private int selectDays(double salary, double i) {
        return (int)(Math.random() * ((7 - 1) + 1)) + 1;
    }

    private Map<Restaurant, Integer> selectRestaurants(Map<Restaurant, Integer> restaurantRoutines, Restaurant[] restaurants, int restaurantLength, int days) {
        Restaurant auxRestaurant;
        for (int i = 0; i < restaurantLength; i++) {
            auxRestaurant = restaurants[(int) (Math.random() * (restaurants.length-1))];
            restaurantRoutines.put(auxRestaurant,days);
        }

        return restaurantRoutines;
    }

    private Restaurant[] getRestaurantGroup(int price, List<Restaurant> restaurantList){
        List<Restaurant> auxList = new ArrayList<Restaurant>();
        for (Restaurant i : restaurantList) {
            if(i.getMaxPricePlate() <= price){
                auxList.add(i);
            }
        }
        Restaurant[] restaurants = new Restaurant[auxList.size()];
        return auxList.toArray(restaurants);
    }

    private Restaurant[] getRestaurantGroup(List<Restaurant> restaurantList){
        List<Restaurant> auxList = new ArrayList<Restaurant>();
        for (Restaurant i : restaurantList) {
            if(i.getMaxPricePlate() >= maxPrice){
                auxList.add(i);
            }
        }
        Restaurant[] restaurants = new Restaurant[auxList.size()];
        return auxList.toArray(restaurants);
    }
}
