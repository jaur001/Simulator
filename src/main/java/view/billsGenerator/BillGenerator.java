package view.billsGenerator;

import model.restaurant.Eating;

public interface BillGenerator {

    void generateBill(Eating eating, String url);
}
