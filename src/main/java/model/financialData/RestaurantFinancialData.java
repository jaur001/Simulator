package model.financialData;

import model.financialData.FinancialData;

public class RestaurantFinancialData extends FinancialData {



    public RestaurantFinancialData(double socialCapital) {
        super(socialCapital);
    }


    public void addClientSale(double amount){
        sales += amount;
        treasury += amount;
        netWorth += amount;
        totalActive += amount;
    }

    public void addDebt(double productPrice) {
        purchases+=productPrice;
        totalPassive+=productPrice;
    }
}
