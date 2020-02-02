package view.loaders.client;

import model.client.Client;
import java.util.List;

public interface ClientReader {

    List<Client> read(int rowNumber);
}
