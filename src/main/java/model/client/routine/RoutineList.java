package model.client.routine;

import model.restaurant.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

public class RoutineList {
    private double salary;
    private List<Routine> restaurantRoutines;
    //NormalDistribution restaurantLengthDistribution;
    //NormalDistribution restaurantPriceDistribution;

    public RoutineList(double salary,List<Routine> restaurantRoutines) {
        this.salary = salary;
        this.restaurantRoutines = restaurantRoutines;
    }

    public List<Restaurant> checkRoutines(){
        return restaurantRoutines.stream()
                .filter(Routine::check)
                .map(Routine::getRestaurant)
                .collect(Collectors.toList());
    }

    public double getSalary() {
        return salary;
    }

    public List<Routine> getClientRoutines() {
        return restaurantRoutines;
    }

    public void printCount() {
        restaurantRoutines.forEach((routine -> System.out.println(routine.getRestaurant().getName() + ": " + routine.getCount())));
    }
}
