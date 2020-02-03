package time;

import model.client.Client;
import model.provider.Provider;
import model.restaurant.Restaurant;

import java.util.List;

public class Month {
    private Day[] monthDays = new Day[30];
    private int actualDay = 1;
    private int lastDay = 30;


    public boolean passTime(List<Restaurant> restaurantList, List<Client> clientList, List<Provider> providerList) {
        if(monthDays[actualDay-1].passTime(restaurantList,clientList,providerList)){
            actualDay = ++actualDay>lastDay?1 : actualDay;
            System.out.println("New Day: " + actualDay);
            return actualDay==1;
        }
        return false;
    }


    public void initialize() {
        for (int i = 0; i < monthDays.length; i++) {
            monthDays[i] = new Day();
            monthDays[i].initialize();
        }
    }
}
