package view.loaders.client;

import model.client.Client;

import java.util.List;

public interface ClientLoader {
    List<Client> load(String url, int rowLength);
}
