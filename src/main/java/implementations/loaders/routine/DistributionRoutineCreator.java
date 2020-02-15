package implementations.loaders.routine;

import model.client.routine.Counter;
import model.client.routine.Routine;
import model.restaurant.Restaurant;
import utils.MathUtils;
import utils.RoutineUtils;
import view.loaders.routine.RoutineCreator;

import java.util.List;

public class DistributionRoutineCreator implements RoutineCreator {
    @Override
    public Routine create(double salary, List<Restaurant> restaurantList) {
        Integer salaryOption = RoutineUtils.getSalaryGroup(salary);
        Restaurant[] restaurants = RoutineUtils.getRestaurantOptions(salary,restaurantList);
         return new Routine(getRandomRestaurant(restaurants), createCounter(salary,salaryOption));
    }

    public Counter createCounter(double salary, double salaryOption){
        return new Counter(selectDays(salary,salaryOption));
    }

    public void restartRoutine(Routine routine,double salary){
        routine.setCounter(new Counter(selectDays(salary,RoutineUtils.getSalaryGroup(salary))));
    }

    private int selectDays(double salary, double salaryOption) {
        return MathUtils.random(2,3);
    }

    private Restaurant getRandomRestaurant(Restaurant[] restaurants) {
        return restaurants[Math.abs(MathUtils.random(0, restaurants.length - 1))];
    }
}
