package model.restaurant;

import java.util.ArrayList;
import java.util.List;

public class Bill {
    private double finalPrice = 0;
    private List<Plate> plateList = new ArrayList<Plate>();

    public Bill() {
    }

    public Bill(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void addPlateToBill(Plate plate){
        finalPrice+=plate.getCost();
        plateList.add(plate);
    }
}
