package model.restaurant;

import model.client.Client;

import java.util.Date;

public class Eating {
    private Restaurant restaurant;
    private Client client;
    private Date date;
    private double amount;
    private int invitedPeople;

    public Eating(Restaurant restaurant, Client client, Date date, double amount, int invitedPeople) {
        this.restaurant = restaurant;
        this.client = client;
        this.date = date;
        this.amount = amount;
        this.invitedPeople = invitedPeople;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Client getClient() {
        return client;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public int getInvitedPeople() {
        return invitedPeople;
    }
}
