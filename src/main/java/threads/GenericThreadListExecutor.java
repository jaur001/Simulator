package threads;

import model.client.Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenericThreadListExecutor<Field> extends Thread{

    protected Field field;

    public GenericThreadListExecutor(Field field) {
        this.field = field;
    }

    public static void executeThreads(List<GenericThreadListExecutor> list){
        GenericThreadListExecutor[] SalaryThreadsList = getThreads(list);
        startThreads(SalaryThreadsList);
        joinThreads(SalaryThreadsList);
    }

    private static GenericThreadListExecutor[] getThreads(List<GenericThreadListExecutor> list){
        return list.stream()
                .map(GenericThreadListExecutor::new)
                .toArray(GenericThreadListExecutor[]::new);
    }

    private static void startThreads(GenericThreadListExecutor[] threads) {
        IntStream.range(0,threads.length)
                .forEach(pos -> threads[pos].start());
    }

    private static void joinThreads(GenericThreadListExecutor[] threads) {
        Arrays.stream(threads).forEach(GenericThreadListExecutor::joinThread);
    }

    private static void joinThread(GenericThreadListExecutor thread){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<GenericThreadListExecutor> getList(List<Field> list){
        return list.stream()
                .map(GenericThreadListExecutor::new)
                .collect(Collectors.toList());
    }
}
