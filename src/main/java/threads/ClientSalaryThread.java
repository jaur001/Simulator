package threads;

import model.client.Client;
import utils.RoutineUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ClientSalaryThread extends Thread {
    private final Client client;

    protected ClientSalaryThread(Client client) {
        this.client = client;
    }


    @Override
    public void run(){
        client.setSalary(RoutineUtils.getSalarySample());
    }


    public static void executeThreads(List<Client> clientList){
        ClientSalaryThread[] clientSalaryThreadsList = getThreads(clientList);
        startThreads(clientSalaryThreadsList);
        joinThreads(clientSalaryThreadsList);
    }

    private static ClientSalaryThread[] getThreads(List<Client> clientList){
        return clientList.stream()
                .map(ClientSalaryThread::new)
                .toArray(ClientSalaryThread[]::new);
    }

    private static void startThreads(ClientSalaryThread[] threads) {
        IntStream.range(0,threads.length)
                .forEach(pos -> threads[pos].start());
    }

    private static void joinThreads(ClientSalaryThread[] threads) {
        Arrays.stream(threads).forEach(ClientSalaryThread::joinThread);
    }

    private static void joinThread(ClientSalaryThread thread){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
