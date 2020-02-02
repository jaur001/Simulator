package time;

import model.client.Client;
import model.provider.Provider;
import model.restaurant.Bill;
import model.restaurant.Eating;
import model.restaurant.Restaurant;
import implementations.xmlBills.CFDIBillGenerator;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Hour {
    public void passTime(List<Restaurant> restaurantList, List<Client> clientList, List<Provider> providerList){
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Client i : clientList) {
            for(Restaurant j: i.getRestaurantRoutines().keySet()){
                if(i.getRoutineList().checkRoutine(j)){
                    new CFDIBillGenerator().generateBill(new Eating(j,i,new Date(),new Bill(10),4),"./xmlFiles/");
                    i.getRoutineList().setCountToZero(j);
                }
            }
        }
    }

    public void initialize() {

    }
}
