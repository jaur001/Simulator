package utils;

public class MathUtils {

    public static int random(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    public static double mean(double[] data){
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

    public static double twoNumberMean(double min, double max){
        return mean(new double[]{min,max});
    }
}
