package model.client;

import model.restaurant.Plate;
import model.restaurant.Restaurant;

public class Client {
    private static int count = 0;
    private int NIF = ++count;
    private int invitedPeople;
    private String name;
    private Restaurant restaurant = null;

    public Client(int invitedPeople, String name) {
        this.invitedPeople = invitedPeople;
        this.name = name;
    }

    public int getNIF() {
        return NIF;
    }

    public String getClientName() {
        return name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public int getInvitedPeople() {
        return invitedPeople;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isClient(Restaurant restaurant){
        return this.restaurant.getName().equals(restaurant.getName());
    }

    public boolean chooseRestaurantToEat(Restaurant restaurant){
        return restaurant.newClient(this);
    }

    public void order(Plate plate){
        restaurant.addOrder(plate,this);
    }

    public void pay(){
        restaurant.payBill(this);
    }
}
