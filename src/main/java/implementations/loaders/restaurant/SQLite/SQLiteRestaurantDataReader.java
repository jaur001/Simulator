package implementations.loaders.restaurant.SQLite;

import implementations.loaders.restaurant.tripAvisor.PriceRange;
import model.restaurant.Restaurant;
import utils.RestaurantUtils;
import view.loaders.restaurant.RestaurantDataReader;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteRestaurantDataReader implements RestaurantDataReader {

    public Restaurant readData(Object doc) {
        ResultSet resultSet = (ResultSet) doc;
        try {
            return new Restaurant(resultSet.getInt(1),resultSet.getString(2)
                    ,resultSet.getString(3),resultSet.getString(4),
                    new PriceRange(resultSet.getInt(5),resultSet.getInt(6))
                    ,resultSet.getInt(7), RestaurantUtils.intialSocialCapital);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
