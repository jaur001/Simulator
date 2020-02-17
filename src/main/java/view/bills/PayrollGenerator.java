package view.bills;

import model.restaurant.Restaurant;
import model.restaurant.worker.Payroll;
import model.restaurant.worker.Worker;

public interface PayrollGenerator {
    void generatePayroll(Payroll payroll);
}
