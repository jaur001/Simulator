package main;

import View.MainFrame;
import model.client.Client;
import model.restaurant.Plate;
import model.restaurant.Restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args){
        int count = 2;
        List<Restaurant> auxList = new ArrayList<Restaurant>();
        RestaurantThread[] threads = new RestaurantThread[count];

        for (int i = 0; i < count; i++) {
            threads[i] = new RestaurantThread(i);
            threads[i].start();
        }

        for (int i = 0; i < count; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < count; i++) {
            auxList.addAll(threads[i].getList());
        }
        List<Restaurant> finalList = new ArrayList<Restaurant>(new HashSet<Restaurant>(auxList));
        MainFrame frame = new MainFrame("Administration",finalList);
        List<Client> clientList = new ArrayList<Client>();
        Client client = new Client(8,"Coleman");
        Restaurant choosen = finalList.get(0);
        System.out.println(choosen);
        client.chooseRestaurantToEat(finalList.get(0));
        System.out.println(choosen.getBusyTables());
        Plate plate = new Plate("Potaje",10);
        client.order(plate);
        choosen.getMenu().getPlateList().add(plate);

        //Order
        client.order(plate);
        //Prepare
        choosen.completeOrders();
        //Pay
        System.out.println(choosen.getData().getSales());
        System.out.println(choosen.getData().getTreasury());
        client.pay();
        System.out.println(choosen.getBusyTables());
        System.out.println(choosen.getData().getSales());
        System.out.println(choosen.getData().getTreasury());


    }
}
