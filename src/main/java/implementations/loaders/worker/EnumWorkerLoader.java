package implementations.loaders.worker;

import model.restaurant.worker.Job;
import model.restaurant.worker.Worker;
import utils.RestaurantUtils;
import view.loaders.worker.WorkerLoader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EnumWorkerLoader implements WorkerLoader {


    @Override
    public List<Worker> load(int numTables) {
        return Arrays.stream(Job.values())
                .flatMap(job -> loadWorkersGroup(job,numTables).stream())
                .collect(Collectors.toList());
    }

    private List<Worker> loadWorkersGroup(Job job,int numTables) {
        int numWorkers = RestaurantUtils.getWorkerLengthGroup(job,numTables);
        return IntStream.range(0,numWorkers).boxed()
                .map(integer -> new Worker(RestaurantUtils.getWorkerSalaryGroup(job),job))
                .collect(Collectors.toList());
    }
}
