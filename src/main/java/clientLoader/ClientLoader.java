package clientLoader;

import model.client.Client;

import java.util.List;

public interface ClientLoader {
    List<Client> load(String url, int rowNumber);
}
