package model.restaurant;

import model.client.Client;
import model.provider.Provider;

import java.util.*;

// No se tiene en cuenta que el plato puede acabarse ni que el plato use productos de un proveedor.
// Simplemente se trata al proveedor como una renta mensual que tiene que pagar.

//Mirar java reflection
// Builder

public class Restaurant {
    private static int count = 0;
    private int NIF = ++count;
    private String name;
    private String street;
    private String telephoneNumber;

    private int minPricePlate;
    private int maxPricePlate;

    private int numberTables;
    private int busyTables = 0;

    private int numberWorkers;
    private RestaurantFinancialData data;
    private List<Provider> providersList = new ArrayList<Provider>();
    private List<Eating> eatingList = new ArrayList<Eating>();
    private List<PlateOrder> orders = new ArrayList<PlateOrder>();




    public Restaurant(String name, String street, String telephoneNumber, int numberTables, int numberWorkers, double socialCapital) {
        this.name = name;
        this.street = street;
        this.telephoneNumber = telephoneNumber;
        this.numberTables = numberTables;
        this.numberWorkers = numberWorkers;
        this.data = new RestaurantFinancialData(socialCapital);
    }

    public Restaurant(String name, String street, String telephoneNumber, int minPricePlate, int maxPricePlate, int numberTables, int numberWorkers, double socialCapital) {
        this.name = name;
        this.street = street;
        this.telephoneNumber = telephoneNumber;
        this.minPricePlate = minPricePlate;
        this.maxPricePlate = maxPricePlate;
        this.numberTables = numberTables;
        this.numberWorkers = numberWorkers;
        this.data = new RestaurantFinancialData(socialCapital);
    }


    public int getNIF() {
        return NIF;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public int getMinPricePlate() {
        return minPricePlate;
    }

    public int getMaxPricePlate() {
        return maxPricePlate;
    }

    public int getNumberTables() {
        return numberTables;
    }

    public int getNumberWorkers() {
        return numberWorkers;
    }

    public RestaurantFinancialData getData() {
        return data;
    }

    public List<Provider> getProvidersList() {
        return providersList;
    }

    public int getBusyTables() {
        return busyTables;
    }

    public void addProvider(Provider provider){
        providersList.add(provider);
        addProviderMonthPay();
    }

    private void addProviderMonthPay() {
        data.addDebt(providersList.get(providersList.size()-1).getProductPrice());
    }

    public boolean newClient(Client client, int invitedPeople){
        if(numberTables > busyTables){
            busyTables += getNecessaryTables(invitedPeople);
            eatingList.add(new Eating(this,client,new Date(),new Bill(),invitedPeople));
            client.setRestaurant(this);
            return true;
        }
        return false;
    }

    public void payBill(Client client){
        try{
            Eating eatingToPay = searchClient(client);
            busyTables -= getNecessaryTables(eatingToPay.getInvitedPeople());
            this.data.addClientSale(eatingToPay.getBill().getFinalPrice());
            eatingList.remove(eatingToPay);
            client.setRestaurant(null);
        } catch (NullPointerException e){
            System.out.println("ERROR: Client " + client + " not found in any eating of the list of the restaurant!");
        }
    }

    private int getNecessaryTables(int invitedPeople){
        return (invitedPeople%4==0)? invitedPeople/4: invitedPeople/4 + 1;
    }

    private Eating searchClient(Client client){
       for (Eating i : eatingList){
           if(i.getClient().getNIF() == client.getNIF()){
               return i;
           }
       }
       return null;
    }

    public void addOrder(Plate plate, Client client){
        orders.add(new PlateOrder(plate, client));
        System.out.println("Plate ordered");
    }

    public void completeOrders(){
        for(PlateOrder i : orders){
            try{
                searchClient(i.getClient()).getBill().addPlateToBill(i.getPlate());
                System.out.println("Orders completed");
                orders = new ArrayList<PlateOrder>();
            } catch (NullPointerException e){
                System.out.println("ERROR: Client " + i.getClient() + " not found in any eating of the list of the restaurant!");
            }
        }
    }


    @Override
    public String toString() {
        return minPricePlate + " - " + maxPricePlate;
    }
}
