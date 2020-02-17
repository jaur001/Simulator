package utils;

import model.restaurant.worker.Job;
import org.apache.commons.math3.distribution.BetaDistribution;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class RestaurantUtils {

    private static final int MIN = 4;
    private static final int MAX = 40;
    private static final int ALPHA = 2;
    private static final int BETA = 4;
    public static final int WORKERS_MIN = 1;
    private static BetaDistribution numTablesDistribution = new BetaDistribution(ALPHA, BETA);
    private static final Map<Job, Integer> workerGroup = new HashMap<>();

    static {
        Job[] jobs = {Job.Waiter,Job.KitchenHelper,Job.Cooker,Job.Maitre,Job.Chef,Job.Receptionist};
        Integer[] lengthPerTenTables = {3,2,1,-1,-1,-1};
        IntStream.range(0,lengthPerTenTables.length).boxed()
                .forEach(i -> workerGroup.put(jobs[i],lengthPerTenTables[i]));
    }


    public static int getWorkerGroup(Job job,int numTables) {
        return (int)Math.max(Math.round(((workerGroup.get(job)/10.0))*numTables), WORKERS_MIN);
    }

    public static int getNumTablesSample(){
        return (int)Math.max(MIN,Math.round(numTablesDistribution.sample()* MAX));
    }
}
