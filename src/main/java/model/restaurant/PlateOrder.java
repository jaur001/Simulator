package model.restaurant;

import model.client.Client;
import model.restaurant.Plate;

public class PlateOrder {
    private Plate plate;
    private Client client;

    public PlateOrder(Plate plate, Client client) {
        this.plate = plate;
        this.client = client;
    }

    public Plate getPlate() {
        return plate;
    }

    public Client getClient() {
        return client;
    }

}
