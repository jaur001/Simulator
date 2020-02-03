package utils;

import model.restaurant.Restaurant;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    private static final NormalDistribution salaryDistribution = new NormalDistribution(1717,979.28);
    private static final NormalDistribution plateNumberDistribution = new NormalDistribution(2,0.7);

    public static double mean (double[] data){
        double sum = 0.0;
        int length = data.length;
        for(double num : data) {
            sum += num;
        }
        return sum/length;
    }

    public static double standardDeviation(double[] data){
        double standardDeviation = 0.0;
        double mean = mean(data);
        for(double num: data) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation/data.length);
    }


    public static Map<Integer,Integer> getRestaurantGroupsTable(){
        Map<Integer,Integer> restaurantGroup = new HashMap<>();
        Integer[] salaries = {1500,3000,5000,7000};
        Integer[] prices = {15,30,50,70};
        for (int i = 0; i < salaries.length; i++) {
            restaurantGroup.put(salaries[i],prices[i]);
        }

        return restaurantGroup;
    }

    public static int Random(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }


    public static double getSalarySample() {
        double sample = salaryDistribution.sample();
        return Math.max(sample,500);
    }

    public static double getPlateNumberSample() {
        double sample = Math.round(Math.abs(plateNumberDistribution.sample()));
        return sample<1? 1: sample;
    }

    public static double platePriceMean(double min, double max){
        return mean(new double[]{min,max});
    }

    public static double getPriceSample(Restaurant restaurant,int plateNumber, int invitedPeople){
        double mean = platePriceMean(restaurant.getMinPricePlate(),restaurant.getMaxPricePlate());
        return (restaurant.getMaxPricePlate()==restaurant.getMinPricePlate()? mean :
                Utils.getPlatePriceSample(restaurant)) * plateNumber * invitedPeople;

    }

    public static double getPlatePriceSample(Restaurant restaurant){
        double mean = platePriceMean(restaurant.getMinPricePlate(),restaurant.getMaxPricePlate());
        return Math.abs(new NormalDistribution(mean,restaurant.getMaxPricePlate()-mean).sample());
    }



    public static void main(String[] args) {
        int count = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        double sample = 0;
        NormalDistribution normalDistribution = new NormalDistribution(1717,979.28);
        for (int i = 0; i < 100; i++) {
            sample = Math.max(normalDistribution.sample(),sample);
        }
        System.out.println(sample);
    }
}
