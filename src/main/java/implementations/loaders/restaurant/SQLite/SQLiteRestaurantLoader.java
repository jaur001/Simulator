package implementations.loaders.restaurant.SQLite;

import implementations.loaders.restaurant.tripAvisor.PriceRange;
import model.restaurant.Restaurant;
import view.loaders.restaurant.RestaurantLoader;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteRestaurantLoader implements RestaurantLoader {

    private static Connection connection = null;
    private static String SQLiteUrl = "jdbc:sqlite:Simulator.db";
    private static int COLUMN_NUMBER = 7;
    private static double intialSocialCapital = 10000;

    private int count = 0;



    public static String getSQLiteUrl() {
        return SQLiteUrl;
    }

    public static void setSQLiteUrl(String SQLiteUrl) {
        SQLiteRestaurantLoader.SQLiteUrl = SQLiteUrl;
    }

    @Override
    public List<Restaurant> load(int count){
        this.count = count;
        connect();
        return loadRestaurants();
    }

    private static void connect(){
        try {
            if (connection == null){
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(SQLiteUrl);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<Restaurant> loadRestaurants() {
        String insert = "select * from Restaurant";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Restaurant> restaurantList = getRestaurants(resultSet);
            preparedStatement.close();
            disconnect();
            return restaurantList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<Restaurant> getRestaurants(ResultSet resultSet) {
        try {
            return iterate(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private List<Restaurant> iterate(ResultSet resultSet) throws SQLException {
        List<Restaurant> restaurantList = new ArrayList<>();
        int pos = 1;
        while (resultSet.next() && pos++ <= count){
            restaurantList.add(getRestaurant(resultSet));
        }
        return restaurantList;
    }

    private Restaurant getRestaurant(ResultSet resultSet) {
        try {
            return new Restaurant(resultSet.getInt(1),resultSet.getString(2)
                    ,resultSet.getString(3),resultSet.getString(4),
                    new PriceRange(resultSet.getInt(5),resultSet.getInt(6))
                    ,resultSet.getInt(7),intialSocialCapital);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
