package time;

import model.client.Client;
import model.provider.Provider;
import model.restaurant.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Time {
    private Year firsYear = new Year();
    List<Restaurant> restaurantList;
    List<Client> clientList;
    List<Provider> providerList;
    private int actualYear = 2020;

    public Time(List<Restaurant> restaurantList, List<Client> clientList, List<Provider> providerList) {
        this.restaurantList = restaurantList;
        this.clientList = clientList;
        this.providerList = providerList;
        firsYear.initialize();
    }

    public void play(){
        if(firsYear.passTime(restaurantList,clientList,providerList)){
            actualYear++;
            System.out.println("New Year: " + actualYear);
        }
    }
}
