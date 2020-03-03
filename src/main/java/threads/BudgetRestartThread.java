package threads;

import model.client.Client;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BudgetRestartThread extends Thread{

    public static void restartBudgets(List<Client> clientList){
        clientList.parallelStream()
                .forEach(BudgetRestartThread::restartBudget);
    }

    private static void restartBudget(Client client){
        client.getRoutineList().restartBudget();
    }



}
