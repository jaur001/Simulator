package model.restaurant;

import model.client.Client;
import model.provider.Provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Menu menu;

    private int minPricePlate;
    private int maxPricePlate;

    private int numberTables;
    private int busyTables = 0;

    private int numberWorkers;
    private RestaurantFinancialData data;
    private List<Provider> providersList = new ArrayList<Provider>();
    private Map<Client, Bill> billList = new HashMap<Client, Bill>();
    private List<PlateOrder> orders = new ArrayList<PlateOrder>();



    public Restaurant(String name, String street, String telephoneNumber, int numberTables, int numberWorkers, double socialCapital) {
        this.name = name;
        this.street = street;
        this.telephoneNumber = telephoneNumber;
        this.numberTables = numberTables;
        this.numberWorkers = numberWorkers;
        this.data = new RestaurantFinancialData(socialCapital);
    }

    public Restaurant(String name, String street, String telephoneNumber, Menu menu, int numberTables, int numberWorkers,double socialCapital) {
        this.name = name;
        this.street = street;
        this.telephoneNumber = telephoneNumber;
        this.menu = menu;
        this.numberTables = numberTables;
        this.numberWorkers = numberWorkers;
        this.data = new RestaurantFinancialData(socialCapital);
    }

    public Restaurant(String name, String street, String telephoneNumber, Menu menu, int minPricePlate, int maxPricePlate, int numberTables, int numberWorkers,double socialCapital) {
        this.name = name;
        this.street = street;
        this.telephoneNumber = telephoneNumber;
        this.menu = menu;
        this.minPricePlate = minPricePlate;
        this.maxPricePlate = maxPricePlate;
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

    public Menu getMenu() {
        return menu;
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

    public boolean newClient(Client client){
        if(numberTables > busyTables){
            busyTables += (client.getInvitedPeople()%4==0)? client.getInvitedPeople()/4: client.getInvitedPeople()/4 + 1;
            billList.put(client,new Bill());
            client.setRestaurant(this);
            return true;
        }
        return false;
    }

    public void payBill(Client client){
        busyTables -= (client.getInvitedPeople()%4==0)? client.getInvitedPeople()/4: client.getInvitedPeople()/4+1;
        this.data.addClientSale(billList.get(client).getFinalPrice());
        billList.remove(client);
        client.setRestaurant(null);
    }

    public void addOrder(Plate plate, Client client){
        if(menu.getPlateList().contains(plate)) {
            orders.add(new PlateOrder(plate, client));
            System.out.println("Plate ordered");
        } else {
            System.out.println("We do not have that plate");
        }
    }

    public void completeOrders(){
        for(PlateOrder i : orders){
            billList.get(i.getClient()).addPlateToBill(i.getPlate());
        }
        System.out.println("Orders completed");
        orders = new ArrayList<PlateOrder>();
    }

    public boolean hasMenu(){
        return menu!=null;
    }

    @Override
    public String toString() {
        return name;
    }
}
