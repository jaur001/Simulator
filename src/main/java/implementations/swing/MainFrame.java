package implementations.swing;

import model.restaurant.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    List<Restaurant> restaurantList;

    public MainFrame(String title, List<Restaurant> restaurantList) throws HeadlessException {
        super(title);
        this.restaurantList = restaurantList;
        setVisible(true);
        setSize(1000,500);
        add(new RestaurantDisplay(restaurantList));
    }
}
