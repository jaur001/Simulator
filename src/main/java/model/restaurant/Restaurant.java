package model.restaurant;

import implementations.loaders.restaurant.tripAvisor.PriceRange;
import model.financialData.RestaurantFinancialData;
import model.provider.Provider;
import model.restaurant.worker.Worker;
import utils.MathUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// No se tiene en cuenta que el plato puede acabarse ni que el plato use productos de un proveedor.
// Simplemente se trata al proveedor como una renta mensual que tiene que pagar.

//Mirar java reflection
// Builder

public class Restaurant {
    private static final AtomicInteger count = new AtomicInteger(MathUtils.random(1000000,9999999));
    private int NIF;
    private String name;
    private String street;
    private String telephoneNumber;
    private PriceRange priceRange;
    private int numberTables;

    private List<Worker> workerList;
    private RestaurantFinancialData financialData;
    private List<Provider> providersList = new ArrayList<>();

    public Restaurant(int NIF, String name, String street, String telephoneNumber, PriceRange priceRange, int numberTables, double socialCapital) {
        this(name, telephoneNumber, street, priceRange, numberTables, socialCapital);
        this.NIF = NIF;
    }

    public Restaurant(String name, String telephoneNumber, String street, PriceRange priceRange, int numberTables, double socialCapital) {
        this.NIF = count.getAndIncrement();
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.street = street;
        this.priceRange = priceRange;
        this.numberTables = numberTables;
        this.workerList = new ArrayList<>();
        this.financialData = new RestaurantFinancialData(socialCapital);
    }


    public void addProvider(Provider provider){
        providersList.add(provider);
        financialData.addDebt(provider.getProductPrice());
    }

    public void removeProvider(Provider provider){
        providersList.remove(provider);
        financialData.removeDebt(provider.getProductPrice());
    }

    public void addWorker(Worker worker){
        workerList.add(worker);
        financialData.addDebt(worker.getSalary());
    }

    public void removeWorker(Worker worker){
        providersList.remove(worker);
        financialData.removeDebt(worker.getSalary());
    }

    public void addSale(double amount){
        financialData.addSale(amount);
    }

    public void payDebts() {
        System.out.println(this.name +" payed Debts:");
        financialData.payDebts();
    }

    public void payWorker(Worker worker){

    }

    public double getPricePlateMean(){
        return MathUtils.twoNumberMean(this.getMinPricePlate(),this.getMaxPricePlate());
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
        return priceRange.getMinPrice();
    }

    public int getMaxPricePlate() {
        return priceRange.getMaxPrice();
    }

    public int getNumberTables() {
        return numberTables;
    }

    public List<Worker> getWorkerList() {
        return workerList;
    }

    public RestaurantFinancialData getFinancialData() {
        return financialData;
    }

    public List<Provider> getProvidersList() {
        return providersList;
    }


    public String printPriceRange() {
        return getMinPricePlate() + " - " + getMaxPricePlate();
    }
}
