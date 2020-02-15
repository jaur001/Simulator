package view.loaders.routine;

import model.client.Client;
import model.restaurant.Restaurant;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.List;
import java.util.Map;

public interface RoutineLoader {
    List<Client> load(List<Restaurant> restaurantList, List<Client> clientList);
}
