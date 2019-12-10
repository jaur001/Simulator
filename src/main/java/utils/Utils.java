package utils;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static double calculateStandardDeviation(double[] data){
        double sum = 0.0, standardDeviation = 0.0;
        int length = data.length;
        for(double num : data) {
            sum += num;
        }
        double mean = sum/length;
        for(double num: data) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation/length);
    }

    public static double calculateMean(double[] data){
        double sum = 0.0;
        int length = data.length;
        for(double num : data) {
            sum += num;
        }
        return sum/length;
    }

    public static Map<Integer,Integer> getRestaurantGroupsTable(){
        Map<Integer,Integer> restaurantGroup = new HashMap<Integer, Integer>();
        Integer salaries[] = {1500,3000,5000,7000};
        Integer prices[] = {15,30,50,70};
        for (int i = 0; i < salaries.length; i++) {
            restaurantGroup.put(salaries[i],prices[i]);
        }

        return restaurantGroup;
    }
}
