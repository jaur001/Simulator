package time;

import model.client.Client;
import model.provider.Provider;
import model.restaurant.Restaurant;
import threads.SalaryThread;
import java.util.List;

public class Year {
    private Month[] yearMonths = new Month[12];
    private int actualMonth = 1;
    private int lastMonth = 12;



    public boolean passTime(List<Restaurant> restaurantList, List<Client> clientList, List<Provider> providerList) {
        if(yearMonths[actualMonth-1].passTime(restaurantList,clientList,providerList)){
            actualMonth = ++actualMonth>lastMonth?1 : actualMonth;
            System.out.println("New Month: " + actualMonth);
            enterSalaryToClients(clientList);
            return actualMonth==1;
        }
        return false;
    }

    private void enterSalaryToClients(List<Client> clientList) {
        SalaryThread.executeThreads(clientList);
    }

    public void initialize() {
        for (int i = 0; i < yearMonths.length; i++) {
            yearMonths[i] = new Month();
            yearMonths[i].initialize();
        }
    }
}
