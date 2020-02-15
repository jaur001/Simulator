package implementations.loaders.client;

import model.client.Client;
import view.loaders.client.ClientLoader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ClientLoaderCSV implements ClientLoader {

    public List<Client> load(String url,int rowLength) {
        try {
            return new ClientReaderCSV(new BufferedReader(new FileReader(url))).read(rowLength);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}