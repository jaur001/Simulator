package View;

import model.restaurant.Restaurant;

import javax.swing.*;
import java.util.List;

public class RestaurantDisplay extends JPanel {

    private JComboBox restaurantSelector = new JComboBox(){
        @Override
        public String toString() {
            return this.getSelectedItem().toString();
        }
    };
    private JTextArea nameText = new JTextArea("Name");
    private JTextArea name = new JTextArea();
    private JTextArea streetText = new JTextArea("Street");
    private JTextArea street = new JTextArea();
    private JTextArea telephoneText = new JTextArea("Telephone number");
    private JTextArea telephone = new JTextArea();

    public RestaurantDisplay(List<Restaurant> restaurantList) {
        super();
        for (Restaurant i : restaurantList){
            restaurantSelector.addItem(i);
        }
        name.setText(restaurantSelector.getSelectedItem().toString());
        //street.setText((Restaurant)(restaurantSelector.getSelectedItem()));
        add(restaurantSelector);
        add(name);

    }
}
