package view.loaders.routine;

import model.client.Client;
import model.restaurant.Restaurant;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.List;
import java.util.Map;

public interface RoutinesLoader {
    List<Client> load(NormalDistribution salaryDistribution,
                      List<Restaurant> restaurantList,
                      List<Client> clientList,
                      Map<Integer,Integer> salaryGroups,
                      int restaurantLength);
}
