package time;

import model.client.Client;
import model.provider.Provider;
import model.restaurant.Restaurant;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Hour {
    public void passTime(List<Restaurant> restaurantList, List<Client> clientList, List<Provider> providerList){
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
