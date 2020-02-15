package implementations.loaders.routine;

import model.client.Client;
import model.restaurant.Restaurant;
import utils.BillsUtils;
import view.loaders.routine.AmountGenerator;

public class DistributionAmountGenerator implements AmountGenerator {
    @Override
    public double generate(Restaurant restaurant, Client client) {
        return BillsUtils.getPriceApproximation(restaurant,(int) BillsUtils.getPlateNumberSample(), client.howManyCommensals());
    }
}
