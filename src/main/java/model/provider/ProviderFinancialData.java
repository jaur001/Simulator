package model.provider;

import model.interfacesAndExtends.FinancialData;

public class ProviderFinancialData extends FinancialData {



    public ProviderFinancialData(double socialCapital) {
        super(socialCapital);
    }


    public void addMonthClient(double amount){
        sales += amount;
        treasury += amount;
        netWorth += amount;
        totalActive += amount;
    }

    public void addNewProduct(double productPrice) {
        purchases+=productPrice;
        totalPassive+=productPrice;
    }
}