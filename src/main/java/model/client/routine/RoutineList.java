package model.client.routine;

import model.restaurant.Restaurant;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoutineList {
    public static final double PERCENTAGE_FOR_RESTAURANT = 0.148;
    private double salary;
    private double budgetForRestaurant;
    private double salarySpent;
    private List<Routine> restaurantRoutines;
    private double provisionalBudgetSpent;

    public RoutineList(double salary,List<Routine> restaurantRoutines) {
        restartBudget();
        restartProvisionalBudget();
        this.salary = salary;
        this.budgetForRestaurant = calculateBudgetForRestaurant(salary);
        this.restaurantRoutines = restaurantRoutines;
    }

    public List<Restaurant> checkRoutines(){
        List<Restaurant> finalList = clientHasBudget(getRestaurantOptions());
        restartProvisionalBudget();
        return finalList;
    }

    private List<Restaurant> getRestaurantOptions() {
        return restaurantRoutines.stream()
                .filter(Routine::check)
                .map(Routine::getRestaurant)
                .collect(Collectors.toList());
    }

    private List<Restaurant> clientHasBudget(List<Restaurant> restaurantOptions) {
        return restaurantOptions.stream()
                .filter(restaurant -> thereIsEnoughBudget(restaurant))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    private boolean thereIsEnoughBudget(Restaurant restaurant) {
        return addToProvisionalBudget(getBudgetApproximation(restaurant));
    }

    private double getBudgetApproximation(Restaurant restaurant) {
        return restaurant.getPricePlateMean()*Utils.getPlateNumberMean()*Utils.getNumberPeopleMean();
    }

    private boolean addToProvisionalBudget(double money) {
        provisionalBudgetSpent += getBudgetAvailable()>provisionalBudgetSpent? money:0;
        return provisionalBudgetSpent <= getBudgetAvailable();
    }

    private double getBudgetAvailable() {
        return budgetForRestaurant - salarySpent;
    }

    public double getSalary() {
        return salary;
    }

    public double getBudgetForRestaurant() {
        return budgetForRestaurant;
    }

    public List<Routine> getClientRoutines() {
        return restaurantRoutines;
    }

    private double calculateBudgetForRestaurant(double salary) {
        return salary* PERCENTAGE_FOR_RESTAURANT;
    }

    private void restartProvisionalBudget() {
        provisionalBudgetSpent = 0;
    }

    public void restartBudget(){
        salarySpent = 0;
    }

    public void decreaseBudget(double amount) {
        salarySpent += amount;
    }

    public void printCount() {
        restaurantRoutines.forEach((routine -> System.out.println(routine.getRestaurant().getName() + ": " + routine.getCount())));
    }
}
