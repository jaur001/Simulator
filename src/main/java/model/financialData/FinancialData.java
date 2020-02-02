package model.financialData;

public class FinancialData {
    /*
    beneficios, total del activo, total del pasivo, patrimonio neto, capital social, tesoreria, compras, ventas
     */
    protected double totalActive;
    protected double totalPassive;

    protected double netWorth;
    protected double treasury;
    protected double socialCapital;

    protected double purchases;
    protected double sales;


    public FinancialData(double socialCapital) {
        this.socialCapital = socialCapital;
        this.treasury = socialCapital;
        this.netWorth = socialCapital;
        this.sales = 0;
        this.purchases = 0;
        this.totalPassive = 0;
        this.totalActive = 0;
    }

    public double getBenefits() {
        return getTotalActive()- getTotalPassive();
    }


    public double getTotalActive() {
        return totalActive;
    }

    public void setTotalActive(double totalActive) {
        this.totalActive += totalActive;
    }

    public double getTotalPassive() {
        return totalPassive;
    }

    public void setTotalPassive(double totalPassive) {
        this.totalPassive += totalPassive;
    }

    public double getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(double netWorth) {
        this.netWorth += netWorth;
    }

    public double getSocialCapital() {
        return socialCapital;
    }

    public void setSocialCapital(double socialCapital) {
        this.socialCapital += socialCapital;
    }

    public double getTreasury() {
        return treasury;
    }

    public void setTreasury(double treasury) {
        this.treasury += treasury;
    }

    public double getPurchases() {
        return purchases;
    }

    public void setPurchases(double purchases) {
        this.purchases += purchases;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales += sales;
    }

    public void setWorkerSalary(double amount,int workersNumber){
        totalPassive += amount*workersNumber;
    }

    public void setRent(double amount){
        totalPassive += amount;
    }
}
