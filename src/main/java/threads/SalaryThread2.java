package threads;

import model.client.Client;

public class SalaryThread2 extends GenericThreadListExecutor<Client> {
    public SalaryThread2(Client client) {
        super(client);
    }

    public void run(){
        field.getRoutineList().restartBudget();
    }
}
