package time;

import model.client.Client;
import model.provider.Provider;
import model.restaurant.Bill;
import model.restaurant.Eating;
import model.restaurant.Restaurant;
import implementations.xmlBills.CFDIBillGenerator;
import utils.Utils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Hour {
    public void passTime(List<Restaurant> restaurantList, List<Client> clientList, List<Provider> providerList){
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
