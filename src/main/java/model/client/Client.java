package model.client;

import model.client.routine.Routine;
import model.client.routine.RoutineList;
import model.restaurant.Restaurant;
import utils.BillsUtils;

import java.util.Date;
import java.util.List;

public class Client {
    private int NIF;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private Date birthDate;
    private String job;
    private String country;
    private String telephoneNumber;
    private String cardNumber;

    private RoutineList routineList;
    private Restaurant restaurant = null;
    private int commensalNumber;


    public Client(int NIF, String firstName, String lastName, String email, String gender, String birthDate) {
        this.NIF = NIF;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthDate = new Date(birthDate);
    }

    public Client(String[] data) {
        this.NIF = Integer.parseInt(data[0]);
        this.firstName = data[1];
        this.lastName = data[2];
        this.birthDate = new Date(data[3]);
        this.gender = data[4];
        this.job = data[5];
        this.country = data[6];
        this.telephoneNumber = data[7];
        this.email = data[8];
        this.cardNumber = data[9];
    }

    public void pay(double amount){
        this.getRoutineList().decreaseBudget(amount);
    }




    public List<Routine> getClientRoutines(){
        return this.getRoutineList().getClientRoutines();
    }

    public double getSalary(){
        return routineList.getSalary();
    }

    public RoutineList getRoutineList() {
        return routineList;
    }

    public void setRoutineList(RoutineList routineList) {
        this.routineList = routineList;
    }

    public int howManyCommensals() {
        commensalNumber = BillsUtils.getNumberPeopleSample();
        return commensalNumber;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getCommensalNumber() {
        return commensalNumber;
    }

    public void printRoutines(){
        System.out.print("Client: "+ this.getLastName() + " ->    ");
        this.routineList.printCount();
    }
}
