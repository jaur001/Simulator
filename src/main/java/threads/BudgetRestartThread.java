package threads;

import model.client.Client;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BudgetRestartThread extends Thread{
    private final Client client;

    protected BudgetRestartThread(Client client) {
        this.client = client;
    }


    @Override
    public void run(){
        client.getRoutineList().restartBudget();
    }


    public static void executeThreads(List<Client> clientList){
        BudgetRestartThread[] budgetRestartThreadsList = getThreads(clientList);
        startThreads(budgetRestartThreadsList);
        joinThreads(budgetRestartThreadsList);
    }

    private static BudgetRestartThread[] getThreads(List<Client> clientList){
        return clientList.stream()
                .map(BudgetRestartThread::new)
                .toArray(BudgetRestartThread[]::new);
    }

    private static void startThreads(BudgetRestartThread[] threads) {
        IntStream.range(0,threads.length)
                .forEach(pos -> threads[pos].start());
    }

    private static void joinThreads(BudgetRestartThread[] threads) {
        Arrays.stream(threads).forEach(BudgetRestartThread::joinThread);
    }

    private static void joinThread(BudgetRestartThread thread){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
