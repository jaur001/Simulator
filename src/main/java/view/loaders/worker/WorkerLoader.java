package view.loaders.worker;

import model.restaurant.worker.Worker;

import java.util.List;

public interface WorkerLoader {
    List<Worker> load(int numTables);
}
