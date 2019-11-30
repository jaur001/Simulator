package model.client;

import model.restaurant.Plate;
import model.restaurant.Restaurant;

import java.util.Date;

public class Client {
    private int NIF;
    private String name;
    private String surname;
    private String email;
    private String genre;
    private Date birthdate;

    private int invitedPeople;
    private Restaurant restaurant = null;

    public Client(int invitedPeople, String name) {
        this.invitedPeople = invitedPeople;
        this.name = name;
    }

    public Client(int NIF, String name, String surname, String email, String genre, String birthdate) {
        this.NIF = NIF;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.genre = genre;
        this.birthdate = new Date(birthdate);
    }

    public Client(String[] data) {
        this.NIF = Integer.parseInt(data[0]);
        this.name = data[1];
        this.surname = data[2];
        this.email = data[3];
        this.genre = data[4];
        this.birthdate = new Date(data[5]);
    }

    public int getNIF() {
        return NIF;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getGenre() {
        return genre;
    }

    public Date getBirthdate() {
        return birthdate;
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
