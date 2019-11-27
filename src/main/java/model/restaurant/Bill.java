package model.restaurant;

import java.util.ArrayList;
import java.util.List;

public class Bill {
    private double finalPrice = 0;
    List<Plate> plateList = new ArrayList<Plate>();

    public double getFinalPrice() {
        return finalPrice;
    }

    public void addPlateToBill(Plate plate){
        finalPrice+=plate.getCost();
        plateList.add(plate);
        System.out.println(finalPrice);
    }
}
