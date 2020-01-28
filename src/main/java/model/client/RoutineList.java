package model.client;

import model.restaurant.Restaurant;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoutineList {
    private double salary;
    private Map<Restaurant,Integer> restaurantCount = new HashMap<Restaurant, Integer>();
    private Map<Restaurant,Integer> restaurantRoutines;
    //NormalDistribution restaurantLengthDistribution;
    //NormalDistribution restaurantPriceDistribution;

    public RoutineList(double salary,Map<Restaurant,Integer> restaurantRoutines) {
        this.salary = salary;
        this.restaurantRoutines = restaurantRoutines;
        for(Restaurant i: restaurantRoutines.keySet()){
            restaurantCount.put(i,0);
        }
        setCountToZero();
    }

    public List<Restaurant> countDown(){
        increaseCount();
        List<Restaurant> restaurantList = new ArrayList<Restaurant>();
        for (Restaurant i : restaurantRoutines.keySet()){
            if(restaurantRoutines.get(i)<restaurantCount.get(i)){
                restaurantList.add(i);
                setCountToZero(i);
            }
        }
        return restaurantList;
    }

    public void setCountToZero(){
        for (Restaurant i : restaurantCount.keySet()) {
            restaurantCount.put(i,0);
        }
    }

    public void setCountToZero(Restaurant restaurant){
            restaurantCount.put(restaurant,0);
    }

    public void increaseCount(){
        for (Restaurant i : restaurantCount.keySet()) {
            restaurantCount.put(i,restaurantCount.get(i)+1);
        }
    }

    public void updateRoutine(Restaurant restaurant, int days){
        restaurantRoutines.put(restaurant,days);
    }

    public double getSalary() {
        return salary;
    }

    public Map<Restaurant, Integer> getRestaurantRoutine() {
        return restaurantRoutines;
    }

    public boolean checkRoutine(Restaurant restaurant){
        return restaurantCount.get(restaurant) == restaurantRoutines.get(restaurant);
    }

    public void printCount() {
        for(Restaurant i : restaurantRoutines.keySet()){
            int j = restaurantRoutines.get(i) - restaurantCount.get(i);
            System.out.println(i.getName() + ": " + j);
        }
    }
}
