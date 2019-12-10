package model.client;

import model.restaurant.Plate;
import model.restaurant.Restaurant;

import java.util.Date;

public class Client {
    private int NIF;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private Date birthdate;
    private String job;
    private String country;
    private String telephoneNumber;
    private String cardNumber;
    private double salary;
    private RoutineList routineList;

    private Restaurant restaurant = null;


    public Client(int NIF, String firstName, String lastName, String email, String gender, String birthdate) {
        this.NIF = NIF;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthdate = new Date(birthdate);
    }

    public Client(String[] data) {
        this.NIF = Integer.parseInt(data[0]);
        this.firstName = data[1];
        this.lastName = data[2];
        this.birthdate = new Date(data[3]);
        this.gender = data[4];
        this.job = data[5];
        this.country = data[6];
        this.telephoneNumber = data[7];
        this.email = data[8];
        this.cardNumber = data[9];
    }

    public RoutineList getRoutineList() {
        return routineList;
    }

    public void setRoutineList(RoutineList routineList) {
        this.routineList = routineList;
        this.salary = routineList.getSalary();
    }

    public int getNIF() {
        return NIF;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getJob() {
        return job;
    }

    public String getCountry() {
        return country;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isClient(Restaurant restaurant){
        return this.restaurant.getName().equals(restaurant.getName());
    }

    public boolean chooseRestaurantToEat(Restaurant restaurant){
        int invitedPeople = 4;
        return restaurant.newClient(this, invitedPeople);
    }

    public void order(Plate plate){
        restaurant.addOrder(plate,this);
    }

    public void pay(){
        restaurant.payBill(this);
    }
}
